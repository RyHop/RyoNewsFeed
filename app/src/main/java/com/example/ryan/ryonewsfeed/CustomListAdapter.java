package com.example.ryan.ryonewsfeed;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<NewsArticle> {
    public CustomListAdapter(@NonNull Context context, @NonNull List<NewsArticle> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listLayout = convertView;
        if (listLayout == null) {
            listLayout = LayoutInflater.from(getContext()).inflate(
                    R.layout.listview_item_layout, parent, false);
        }
        // Get the Word object located at this position in the list
        NewsArticle currentArticle = getItem(position);


        //Getting Textview for the headline
        TextView headlineTextView = listLayout.findViewById(R.id.headlineTextView);
        headlineTextView.setTextSize(20f);
        headlineTextView.setText(currentArticle.getaTitle());
        headlineTextView.setTypeface(null, Typeface.BOLD);


        //Getting Second Textview for author name and date
        TextView authorAndDate = listLayout.findViewById(R.id.authorAndDate);
        authorAndDate.setTextSize(15f);
        String authorAndDateString = getContext().getString(R.string.authorStringResource) + " " + currentArticle.getaAuthor() + " " + getContext().getString(R.string.dateStringResource) + " " + currentArticle.getaDate();
        authorAndDate.setText(authorAndDateString);

        //Setting the image backgournd as pink for now

        ImageView imageOfArticle = listLayout.findViewById(R.id.newsArticleImageView);
        imageOfArticle.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));


        return listLayout;
    }

    @Nullable
    @Override
    public NewsArticle getItem(int position) {


        return super.getItem(position);
    }
}
