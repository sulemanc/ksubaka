package com.sulemanchaudhry.ksubaka.movies.omdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by suleman on 06/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OMDBMovieSearchResults {
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("Type")
    private String type;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "OMDBMovieDetails{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
