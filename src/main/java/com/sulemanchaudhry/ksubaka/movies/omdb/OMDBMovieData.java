package com.sulemanchaudhry.ksubaka.movies.omdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sulemanchaudhry.ksubaka.model.MovieDetails;
import com.sulemanchaudhry.ksubaka.movies.IMovieData;
import com.sulemanchaudhry.ksubaka.movies.MovieDataException;
import com.sulemanchaudhry.ksubaka.reader.IJsonReader;
import com.sulemanchaudhry.ksubaka.reader.JsonReader;
import com.sulemanchaudhry.ksubaka.reader.JsonReaderException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by suleman on 06/09/2016.
 */
public class OMDBMovieData implements IMovieData {


    private IJsonReader jsonReader;

    public OMDBMovieData() {
        jsonReader = new JsonReader();
    }

    public void setJsonReader(IJsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }


    private static final String MOVIE_QUERY = "http://omdbapi.com/?s={movie}&r=json";
    private static final String TITLE_QUERY = "http://omdbapi.com/?t={title}&r=json";

    public List<MovieDetails> getMovieDetails(String searchQuery) throws MovieDataException {
        List<MovieDetails> movieDetailList = new ArrayList<>();
        try {
            String json = readMovieQueryJsonResponse(searchQuery);
            ObjectMapper objectMapper = new ObjectMapper();
            OMDBMovieDetails details = objectMapper.readValue(json, OMDBMovieDetails.class);
            if (details!=null && details.getSearchResults()!=null) {
                for (OMDBMovieSearchResults searchResults : details.getSearchResults()) {
                    if (!searchResults.getType().equals("movie")) {
                        continue;
                    }

                    String title = searchResults.getTitle();
                    json = readTitleQueryResponse(title);
                    OMDBMovieTitleResults titleDetails = objectMapper.readValue(json, OMDBMovieTitleResults.class);
                    String director = titleDetails.getDirector();
                    MovieDetails movieDetails = new MovieDetails(title, director, searchResults.getYear());
                    movieDetailList.add(movieDetails);
                }
            }
        } catch (IOException | JsonReaderException e) {
            throw  new MovieDataException(e);
        }
        return movieDetailList;
    }

    private String readMovieQueryJsonResponse(String searchQuery) throws JsonReaderException {
        try {
            return jsonReader.readJsonResponse(MOVIE_QUERY.replace("{movie}", URLEncoder.encode(searchQuery,"UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new JsonReaderException(e);
        }
    }


    private String readTitleQueryResponse(String searchQuery) throws JsonReaderException {
        try {
            return jsonReader.readJsonResponse(TITLE_QUERY.replace("{title}", URLEncoder.encode(searchQuery,"UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new JsonReaderException(e);
        }
    }



    public static void main(String[] args) throws Exception{
        OMDBMovieData omdbMovieData = new OMDBMovieData();
        List<MovieDetails> movieDetails = omdbMovieData.getMovieDetails("indiana jones");
        for (MovieDetails movieDetail : movieDetails) {
            System.out.println("MovieDetails:"+ movieDetail);
        }
    }

}
