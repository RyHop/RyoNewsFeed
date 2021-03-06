package com.example.ryan.ryonewsfeed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class BusinessFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<NewsArticle>> {
    private String LOG_TAG = "BusinessFragment";
    View rootView;

    private String businessURL = "https://content.guardianapis.com/search?q=business&section=business&show-tags=contributor&show-fields=starRating,headline,thumbnail,short-url&order-by=newest&api-key=43746d72-76d6-4600-b587-8ff703eb6eb7";
    private static final String REQUEST_URL = "https://content.guardianapis.com/search";

    public BusinessFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(LOG_TAG, "Business Fragment Created");

        // Seeing if there is internet connectivity..code from official Android WEbsite about connecting to the Internet
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            Toast.makeText(getContext(), getContext().getString(R.string.InternetErrorMessage), Toast.LENGTH_LONG).show();

        } else {
            Log.v(LOG_TAG, "It has internet, About to execute Loader");
            getActivity().getSupportLoaderManager().initLoader(0, null, this).forceLoad();

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_default_list_layout, container, false);


        // Inflate the layout for this fragment
        return rootView;
    }


    @NonNull
    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.v(LOG_TAG, "Calling the NewsArticle Loader Now");
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());

        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );


        String pageSize = sharedPrefs.getString(getString(R.string.settings_min_page_key), getString(R.string.settings_min_page_default));
        int pageNumber = Integer.parseInt(pageSize);

        if (!(pageNumber > 0 && pageNumber < 51)) {
            // Page number is out of range.
            Toast.makeText(getContext(), "The default page size is set to 10 since the setting 'Page Size' is out of range.", Toast.LENGTH_LONG).show();
            pageNumber = 10;


        }

        Log.v(LOG_TAG, pageSize + " This is the page size");



        // Parsing the basic String URL
        Uri uriBase = Uri.parse(REQUEST_URL);

        // buildUpon prepares the baseUri that we just parsed so we can add query parameters to it
        Uri.Builder uriBuilder = uriBase.buildUpon();

        // Append query parameter and its value. For example, the `format=geojson`
        // uriBuilder.appendQueryParameter("format", "geojson");
        //Example: /search?q=business&section=business&show-tags=contributor&show-fields=starRating,headline,thumbnail,short-url&order-by=newest&api-key=43746d72-76d6-4600-b587-8ff703eb6eb7
        //Base = https://content.guardianapis.com/search?
        uriBuilder.appendQueryParameter("q", "business");
        uriBuilder.appendQueryParameter("section", "business");
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        uriBuilder.appendQueryParameter("show-fields", "starRating,headline,thumbnail,short-url");
        uriBuilder.appendQueryParameter("order-by", orderBy);
        uriBuilder.appendQueryParameter("page-size", pageSize);
        uriBuilder.appendQueryParameter("api-key", "43746d72-76d6-4600-b587-8ff703eb6eb7");
        return new NewsArticleLoader(getContext(), uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<NewsArticle>> loader, List<NewsArticle> data) {
        // When we have the list data from website
        Log.v(LOG_TAG, "Loader is finished");
        RelativeLayout relativeLayout = rootView.findViewById(R.id.businessRLayout);
        ListView businessListView = rootView.findViewById(R.id.default_listView);
        TextView dataMessage = rootView.findViewById(R.id.noDataDisplayedTextView);
        Log.d(LOG_TAG, data.toString());

        // Checking if the data null, then display the current meessage and log it.
        if (data.isEmpty()) {
            Log.v(LOG_TAG, "There is no data :(, inform the user");
            businessListView.setVisibility(View.INVISIBLE);
            dataMessage.setVisibility(View.VISIBLE);
            dataMessage.setText(getContext().getString(R.string.noDataString));
            dataMessage.isShown();
        } else {
            //Show the data and not the message since we have data to be displayed.
            dataMessage.setVisibility(View.INVISIBLE);
            businessListView.setVisibility(View.VISIBLE);
            Log.v(LOG_TAG, "Looks like we are good");
            Log.v(LOG_TAG, " Here is the data " + data);

            CustomListAdapter theArticleAdapter = new CustomListAdapter(getContext(), data);
            businessListView.setOnItemClickListener(mMessageClickedHandler);
            businessListView.setAdapter(theArticleAdapter);

        }

    }

    // Creating an OnClickListener for my AdapterView (ListView) Found this on the Android Website https://developer.android.com/reference/android/widget/AdapterView.OnItemClickListener.
    AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            // Do something in response to the click
            NewsArticle urlOpenInBrowser = (NewsArticle) parent.getItemAtPosition(position);
            String url = urlOpenInBrowser.getaURL();
            Uri webpage = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(intent);
            }

        }

    };

    @Override
    public void onLoaderReset(@NonNull Loader<List<NewsArticle>> loader) {

    }

}
