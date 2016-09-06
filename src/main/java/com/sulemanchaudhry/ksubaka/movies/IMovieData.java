package com.sulemanchaudhry.ksubaka.movies;

import com.sulemanchaudhry.ksubaka.model.MovieDetails;

import java.util.List;

/**
 * Created by suleman on 06/09/2016.
 */
public interface IMovieData {
    List<MovieDetails> getMovieDetails(String searchString) throws MovieDataException;
}
