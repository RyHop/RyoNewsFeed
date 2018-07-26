package com.example.ryan.ryonewsfeed;

import android.content.Context;

import java.util.List;

public class NewsArticleLoader extends  android.support.v4.content.AsyncTaskLoader<List<NewsArticle>> {
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
    }

    @Override
    public List<NewsArticle> loadInBackground() {
        return null;
    }

}
