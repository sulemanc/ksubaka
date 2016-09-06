package com.sulemanchaudhry.ksubaka.reader;

import com.sulemanchaudhry.ksubaka.reader.IJsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by suleman on 06/09/2016.
 */
public class JsonReader implements IJsonReader {
    public String readJsonResponse(String queryString) throws JsonReaderException {
        try {
            URL obj = new URL(queryString);
            HttpURLConnection conn = (HttpURLConnection)obj.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode==200) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result
                return response.toString();
            } else
            {
                throw new JsonReaderException("Invalid response code received: "+responseCode);
            }
        } catch (IOException e) {
            throw  new JsonReaderException(e);
        }
    }
}
