package com.sulemanchaudhry.ksubaka.movies;

/**
 * Created by suleman on 06/09/2016.
 */
public class MovieDataException extends Exception {
    public MovieDataException(Throwable cause) {
        super(cause);
    }

    public MovieDataException(String message) {
        super(message);
    }
}
