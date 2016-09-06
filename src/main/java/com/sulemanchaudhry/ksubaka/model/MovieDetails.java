package com.sulemanchaudhry.ksubaka.model;

/**
 * Created by suleman on 06/09/2016.
 */
public class MovieDetails implements Comparable<MovieDetails> {
    private String movieName;
    private String director;
    private String year;


    public MovieDetails(String movieName, String director, String year) {
        this.movieName = movieName;
        this.director = director;
        this.year = year;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getDirector() {
        return director;
    }

    public String getYear() {
        return year;
    }


    @Override
    public String toString() {
        return "MovieDetails{" +
                "movieName='" + movieName + '\'' +
                ", director='" + director + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public int compareTo(MovieDetails movieDetails) {
        if (movieDetails!=null) {
            return movieDetails.getMovieName().compareTo(getMovieName());
        }
        return 0;
    }
}
