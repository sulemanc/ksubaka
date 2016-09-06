package com.sulemanchaudhry.ksubaka.movies.imdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by suleman on 06/09/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IMDBMovieDetails {

    @JsonProperty("title_popular")
    private List<IMDBTitlePopular> titlePopular;


    public List<IMDBTitlePopular> getTitlePopular() {
        return titlePopular;
    }

    public void setTitlePopular(List<IMDBTitlePopular> titlePopular) {
        this.titlePopular = titlePopular;
    }

    @Override
    public String toString() {
        return "IMDBMovieDetails{" +
                "titlePopular=" + titlePopular +
                '}';
    }
}
