package com.sulemanchaudhry.ksubaka.movies.imdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sulemanchaudhry.ksubaka.model.MovieDetails;
import com.sulemanchaudhry.ksubaka.movies.IMovieData;
import com.sulemanchaudhry.ksubaka.movies.MovieDataException;
import com.sulemanchaudhry.ksubaka.movies.imdb.IMDBMovieDetails;
import com.sulemanchaudhry.ksubaka.movies.imdb.IMDBTitlePopular;
import com.sulemanchaudhry.ksubaka.reader.IJsonReader;
import com.sulemanchaudhry.ksubaka.reader.JsonReader;
import com.sulemanchaudhry.ksubaka.reader.JsonReaderException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by suleman on 06/09/2016.
 */
public class IMDBMovieData implements IMovieData {


    private IJsonReader jsonReader;

    public IMDBMovieData() {
        jsonReader = new JsonReader();
    }

    public void setJsonReader(IJsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    private static final String QUERY = "http://www.imdb.com/xml/find?json=1&nr=1&tt=on&q={movie}";
    private static final Pattern DIRECTOR_NAME = Pattern.compile("<a href='.*'>(.*)</a>");

    public List<MovieDetails> getMovieDetails(String searchQuery) throws MovieDataException {
        List<MovieDetails> movieDetailList = new ArrayList<>();
        try {
            String json = readJsonResponse(searchQuery);
            ObjectMapper objectMapper = new ObjectMapper();

            IMDBMovieDetails details = objectMapper.readValue(json, IMDBMovieDetails.class);
            if (details!=null && details.getTitlePopular()!=null) {
                for (IMDBTitlePopular titlePopular : details.getTitlePopular()) {
                    String description = titlePopular.getDescription();
                    String director = "UNKNOWN";
                    if (description!=null) {
                        if (description.toLowerCase().contains("tv series")) {
                            continue;
                        }
                        String year  = description.substring(0, 4);
                        Matcher directorMatcher = DIRECTOR_NAME.matcher(description);
                        if (directorMatcher.find()) {
                            director = directorMatcher.group(1);
                        }
                        MovieDetails movieDetails = new MovieDetails(titlePopular.getTitle(), director, year);
                        movieDetailList.add(movieDetails);
                    }

                }
            }
        } catch (IOException | JsonReaderException e) {
            throw  new MovieDataException(e);
        }
        return movieDetailList;
    }

    private String readJsonResponse(String searchQuery) throws JsonReaderException {
        return jsonReader.readJsonResponse(QUERY.replace("{movie}", searchQuery));
    }

    public static void main(String[] args) throws Exception{
        IMDBMovieData imdbMovieData = new IMDBMovieData();
        List<MovieDetails> movieDetails = imdbMovieData.getMovieDetails("indiana jones");
        for (MovieDetails movieDetail : movieDetails) {
            System.out.println("MovieDetails:"+ movieDetail);
        }
    }

}
