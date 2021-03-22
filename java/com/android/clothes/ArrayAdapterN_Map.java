package com.android.clothes;

import android.content.Context;
import android.graphics.Typeface;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataBufferUtils;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.AutocompletePredictionBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ArrayAdapterN_Map extends ArrayAdapter<AutocompletePrediction> implements Filterable {

    private static final CharacterStyle STYLE_BOLD = new StyleSpan(Typeface.BOLD);
    private ArrayList<AutocompletePrediction> autocompletePredictionArrayList;
    private GoogleApiClient googleApiClient;
    private LatLngBounds latLngBounds;
    private AutocompleteFilter autocompleteFilter;

    public ArrayAdapterN_Map(Context context, GoogleApiClient googleApiClient, LatLngBounds latLngBounds, AutocompleteFilter autocompleteFilter) {
        super(context, android.R.layout.simple_expandable_list_item_2, android.R.id.text1);
        this.googleApiClient = googleApiClient;
        this.latLngBounds = latLngBounds;
        this.autocompleteFilter = autocompleteFilter;
    }

    public void setLatLngBounds(LatLngBounds latLngBounds) {
        this.latLngBounds = latLngBounds;
    }

    @Override
    public int getCount() {
        return autocompletePredictionArrayList.size();
    }

    @Override
    public AutocompletePrediction getItem(int position) {
        return autocompletePredictionArrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        AutocompletePrediction autocompletePrediction = getItem(position);
        TextView textView1 = view.findViewById(android.R.id.text1);
        TextView textView2 = view.findViewById(android.R.id.text2);
        textView1.setText(autocompletePrediction.getPrimaryText(STYLE_BOLD));
        textView2.setText(autocompletePrediction.getSecondaryText(STYLE_BOLD));
        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                ArrayList<AutocompletePrediction> filterDataArrayList = new ArrayList<>();
                if (constraint != null) {
                    filterDataArrayList = getAutocomplete(constraint);
                }
                filterResults.values = filterDataArrayList;
                if (filterDataArrayList != null) {
                    filterResults.count = filterDataArrayList.size();
                } else {
                    filterResults.count = 0;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    autocompletePredictionArrayList = (ArrayList<AutocompletePrediction>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }

            @Override
            public CharSequence convertResultToString(Object resultValue) {
                if (resultValue instanceof AutocompletePrediction) {
                    return ((AutocompletePrediction) resultValue).getFullText(null);
                } else {
                    return super.convertResultToString(resultValue);
                }
            }
        };
    }

    private ArrayList<AutocompletePrediction> getAutocomplete(CharSequence constraint) {
        if (googleApiClient.isConnected()) {
            PendingResult<AutocompletePredictionBuffer> autocompletePredictionBufferPendingResult = Places.GeoDataApi.getAutocompletePredictions(googleApiClient, constraint.toString(), latLngBounds, autocompleteFilter);
            AutocompletePredictionBuffer autocompletePredictionBuffer = autocompletePredictionBufferPendingResult.await(60, TimeUnit.SECONDS);
            final Status status = autocompletePredictionBuffer.getStatus();
            if (!status.isSuccess()) {
                Toast.makeText(getContext(), "Error contacting API: " + status.toString(), Toast.LENGTH_SHORT).show();
                return null;
            }
            return DataBufferUtils.freezeAndClose(autocompletePredictionBuffer);
        }
        return null;
    }
}
