package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CoronaAdapter extends ArrayAdapter<CoronaDetails> {

    private int count =0;
    public CoronaAdapter(Context context, ArrayList<CoronaDetails> words){
        super(context,0,words);

    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        CoronaDetails currentAndroidWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView countryTextView = (TextView) listItemView.findViewById(R.id.country);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        countryTextView.setText(currentAndroidWord.getCountry());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView numberTextView = (TextView) listItemView.findViewById(R.id.cases);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        numberTextView.setText(Long.toString(currentAndroidWord.getCases()));

        return listItemView;

    }
}
