package com.sulemanchaudhry.ksubaka.reader;

import java.io.IOException;

/**
 * Created by suleman on 06/09/2016.
 */
public interface IJsonReader {
    String readJsonResponse(String queryString) throws JsonReaderException;
}
