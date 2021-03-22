package com.android.clothes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class AdapterN_Info implements GoogleMap.InfoWindowAdapter {

    private Context context;
    private final View view;

    public AdapterN_Info(Context context) {
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.n__info, null);
    }

    private void rendowText(Marker marker, View view) {
        String title = marker.getTitle();
        TextView n__info_title = view.findViewById(R.id.n__info_title);
        if (!title.equals("")) {
            n__info_title.setText(title);
        }
        String snippet = marker.getSnippet();
        TextView n__info_snippet = view.findViewById(R.id.n__info_snippet);
        if (!snippet.equals("")) {
            n__info_snippet.setText(snippet);
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        rendowText(marker, view);
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        rendowText(marker, view);
        return view;
    }
}
