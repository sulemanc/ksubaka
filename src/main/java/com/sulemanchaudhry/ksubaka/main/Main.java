package com.sulemanchaudhry.ksubaka.main;

import com.sulemanchaudhry.ksubaka.model.MovieDetails;
import com.sulemanchaudhry.ksubaka.movies.IMovieData;
import com.sulemanchaudhry.ksubaka.movies.MovieDataException;
import com.sulemanchaudhry.ksubaka.movies.imdb.IMDBMovieData;
import com.sulemanchaudhry.ksubaka.movies.omdb.OMDBMovieData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suleman on 06/09/2016.
 */
public class Main {

    private static Map<String, IMovieData> apiToMovieDataMap = new HashMap<>();

    static
    {
        apiToMovieDataMap.put("imdb", new IMDBMovieData());
        apiToMovieDataMap.put("omdb", new OMDBMovieData());
    }


    public static void main(String[] args) throws MovieDataException {
        String api = System.getProperty("api");
        if (api==null) {
            errorAndExit("no api provided");
        }

        IMovieData movieData = apiToMovieDataMap.get(api);
        if (movieData==null) {
            errorAndExit("no api:"+api+" not found; cannot process query");
        }
        String searchQuery = (System.getProperty("movie"));
        if (searchQuery==null) {
            errorAndExit("no search criteria provided");
        }

        List<MovieDetails> movieDetails = movieData.getMovieDetails(searchQuery);
        for (MovieDetails movieDetail : movieDetails) {
            System.out.println(movieDetail);
        }
    }

    private static void errorAndExit(String s) {
        System.err.println(s);
        System.exit(-1);
    }
}
