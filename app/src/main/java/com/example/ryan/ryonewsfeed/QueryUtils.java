package com.example.ryan.ryonewsfeed;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    //Got Base code from the Quake App through Udacity, then I put my own spin on it.
    private String LOG_TAG = "QueryUtils";


    // Perform HTTP request to the URL and receive a JSON response back
    public List<NewsArticle> getDataFromInternet(URL url) {
        Log.v(LOG_TAG, "Started the getDataFromInternet Method");
        String jsonResponse = "";
        try {
            Log.v(LOG_TAG, "Making Http Request");
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            // TODO Handle the IOException
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object

        Log.v(LOG_TAG, "Extractring the jsonObject");
        return extractFeatureFromJson(jsonResponse);


    }

    /**
     * Return an {@link ArrayList<NewsArticle> object by parsing out information
     */
    private List<NewsArticle> extractFeatureFromJson(String newsArticleJSON) {
        List<NewsArticle> theArticles = new ArrayList<>();
        try {
            Log.v(LOG_TAG, "Starting Extracting JSON!");

            JSONObject baseJsonResponse = new JSONObject(newsArticleJSON);
            JSONObject responseObject = baseJsonResponse.getJSONObject("response");
            JSONArray resultsArray = responseObject.getJSONArray("results");
            Log.v(LOG_TAG, "Made it to the news Articles DATA");

            for (int j = 0; j < resultsArray.length(); j++) {
                JSONObject aNewsArtile = resultsArray.getJSONObject(j);
                String Date = aNewsArtile.getString("webPublicationDate");
                String Title = aNewsArtile.getString("webTitle");
                String URL = aNewsArtile.getString("webUrl");

                //Getting the author which is webTitle under the jags jsonArray
                JSONArray tagsArray = aNewsArtile.getJSONArray("tags");
                JSONObject theOneTagsObject = tagsArray.getJSONObject(0);
                String Author = theOneTagsObject.getString("webTitle");

                //Put the article in the array, URL, Title, Author, Date
                theArticles.add(new NewsArticle(URL, Title, Author, Date));

            }
            Log.v(LOG_TAG, "Done getting the data about the articles");


        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the JSON results", e);
        }
        return theArticles;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            // TODO: Handle the exception
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        if (jsonResponse == "" || jsonResponse == null) {
            Log.v(LOG_TAG, "NO JSON");
        }
        Log.v(LOG_TAG, "JSON is something I guess");
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}
