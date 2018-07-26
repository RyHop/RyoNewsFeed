package com.example.ryan.ryonewsfeed;

import android.content.Context;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class NewsArticleLoader extends android.support.v4.content.AsyncTaskLoader<List<NewsArticle>> {
    private String articleURL;
    private String LOG_TAG = "NewArticleLoader";
    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public NewsArticleLoader(Context context, String URL) {
        super(context);
        Log.v(LOG_TAG, "NewsArticleLoader is initialized");
        this.articleURL = URL;

    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
    }

    @Override
    public List<NewsArticle> loadInBackground() {
        Log.v(LOG_TAG, "NewsArticle is calling loadInBackground");
        URL url = createUrl(articleURL);
        Log.v(LOG_TAG, "Created URL");

        return null;
    }


    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }

}
