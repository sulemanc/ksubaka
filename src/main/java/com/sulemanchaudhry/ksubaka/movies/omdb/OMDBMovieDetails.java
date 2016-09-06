package com.sulemanchaudhry.ksubaka.movies.omdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sulemanchaudhry.ksubaka.movies.imdb.IMDBTitlePopular;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OMDBMovieDetails {

    @JsonProperty("Search")
    private List<OMDBMovieSearchResults> searchResults;

    public List<OMDBMovieSearchResults> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<OMDBMovieSearchResults> searchResults) {
        this.searchResults = searchResults;
    }

    @Override
    public String toString() {
        return "OMDBMovieDetails{" +
                "searchResults=" + searchResults +
                '}';
    }
}
