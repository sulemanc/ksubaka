package com.sulemanchaudhry.ksubaka.imdb;

import com.sulemanchaudhry.ksubaka.model.MovieDetails;
import com.sulemanchaudhry.ksubaka.movies.imdb.IMDBMovieData;
import com.sulemanchaudhry.ksubaka.movies.omdb.OMDBMovieData;
import com.sulemanchaudhry.ksubaka.reader.IJsonReader;
import com.sulemanchaudhry.ksubaka.reader.JsonReader;
import com.sulemanchaudhry.ksubaka.reader.JsonReaderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

/**
 * Created by suleman on 06/09/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class OMDBLoaderTest {


    private OMDBMovieData omdbLoader;

    @Before
    public void doBeforeEachTestCase() {
        omdbLoader = new OMDBMovieData();
    }

    @Test
    public void testDownloadedData() throws Exception {
        IJsonReader jsonReader = new JsonReader() {
            @Override
            public String readJsonResponse(String queryString) throws JsonReaderException {
                try {
                    String filename = null;
                    if (queryString.startsWith("http://omdbapi.com/?s=")) {
                        filename = "indiana_jones_searchquery_omdb.json";
                    } else if (queryString.startsWith("http://omdbapi.com/?t=")) {
                        filename = "indiana_jones_titlequery_omdb.json";
                    }
                    if (filename==null) {
                        throw new JsonReaderException("Query String "+queryString+" not found");
                    }
                    InputStream is = this.getClass().getClassLoader().getResourceAsStream(filename);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    return sb.toString();
                } catch (IOException e) {
                    throw  new JsonReaderException(e);
                }
            }
        };
        omdbLoader.setJsonReader(jsonReader);
        List<MovieDetails> movieDetails = omdbLoader.getMovieDetails("indiana jones");
        Collections.sort(movieDetails);
        Assert.assertEquals(movieDetails.size(), 7);
    }
}