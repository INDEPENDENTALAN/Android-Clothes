package com.android.clothes;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import clothes.clothes.N_Map.N_Info;

public class N_MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener {

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(N_MapActivity.this, "Map is Ready", Toast.LENGTH_SHORT).show();
        google_Map = googleMap;
        if (Location_Permission_Granted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(N_MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(N_MapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            google_Map.setMyLocationEnabled(true);
            google_Map.getUiSettings().setMyLocationButtonEnabled(false);
            init();
        }
    }

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private boolean Location_Permission_Granted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private GoogleMap google_Map;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final float DEFAULT_ZOOM = 15f;
    private AutoCompleteTextView n__map_search;
    private ImageView n__map_location;
    private ArrayAdapterN_Map arrayAdapterN_map;
    private GoogleApiClient googleApiClient;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(new LatLng(-40, -168), new LatLng(71, 136));
    private N_Info n_info;
    private Marker marker;
    private ImageView n__map_info;
    private ImageView n__map_add;
    private static final int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n__map);
        n__map_search = findViewById(R.id.n__map_search);
        n__map_location = findViewById(R.id.n__map_location);
        n__map_info = findViewById(R.id.n__map_info);
        n__map_add = findViewById(R.id.n__map_add);
        getLocationPermission();
        init();
    }

    private void init() {
        googleApiClient = new GoogleApiClient.Builder(N_MapActivity.this).addApi(Places.GEO_DATA_API).addApi(Places.PLACE_DETECTION_API).enableAutoManage(N_MapActivity.this, N_MapActivity.this).build();
        n__map_search.setOnItemClickListener(adapterView_onItemClickListener);
        arrayAdapterN_map = new ArrayAdapterN_Map(N_MapActivity.this, googleApiClient, LAT_LNG_BOUNDS, null);
        n__map_search.setAdapter(arrayAdapterN_map);
        n__map_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN || event.getAction() == KeyEvent.KEYCODE_ENTER) {
                    getLocate();
                }
                return false;
            }
        });
        n__map_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDeviceLocation();
            }
        });
        n__map_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (marker.isInfoWindowShown()) {
                        marker.hideInfoWindow();
                    } else {
                        marker.showInfoWindow();
                    }
                } catch (NullPointerException npe) {

                }
            }
        });
        n__map_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder placePicker_intentBuilder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(placePicker_intentBuilder.build(N_MapActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException gpsre) {

                } catch (GooglePlayServicesNotAvailableException gpsnae) {

                }
            }
        });
        hideInput();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(N_MapActivity.this, data);
                PendingResult<PlaceBuffer> placeBufferPendingResult = Places.GeoDataApi.getPlaceById(googleApiClient, place.getId());
                placeBufferPendingResult.setResultCallback(updatePlaceDetailsCallback);
            }
        }
    }

    private void getLocate() {
        String search = n__map_search.getText().toString();
        Geocoder geocoder = new Geocoder(N_MapActivity.this);
        List<Address> addressList = new ArrayList<>();
        try {
            addressList = geocoder.getFromLocationName(search, 1);
        } catch (IOException ioe) {

        }
        if (addressList.size() > 0) {
            Address address = addressList.get(0);
            Toast.makeText(N_MapActivity.this, address.toString(), Toast.LENGTH_SHORT).show();
            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM, address.getAddressLine(0));
        }
    }

    private void getDeviceLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(N_MapActivity.this);
        try {
            if (Location_Permission_Granted) {
                Task location = fusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(Task task) {
                        if (task.isSuccessful()) {
                            Location current_location = (Location) task.getResult();
                            moveCamera(new LatLng(current_location.getLatitude(), current_location.getLongitude()), DEFAULT_ZOOM, "My Location");
                        } else {
                            Toast.makeText(N_MapActivity.this, "Unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException se) {

        }
    }

    private void moveCamera(LatLng latLng, float zoom, N_Info n_info) {
        google_Map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        google_Map.clear();
        google_Map.setInfoWindowAdapter(new AdapterN_Info(N_MapActivity.this));
        if (n_info != null) {
            try {
                String snippet = "Address: " + n_info.getAddress() + "\n" + "Phone Number: " + n_info.getPhoneNumber() + "\n" + "Website: " + n_info.getWebsiteUri() + "\n" + "Price Rating: " + n_info.getRating() + "\n";
                MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(n_info.getName()).snippet(snippet);
                marker = google_Map.addMarker(markerOptions);
            } catch (NullPointerException npe) {

            }
        } else {
            google_Map.addMarker(new MarkerOptions().position(latLng));
        }
        hideInput();
    }

    private void moveCamera(LatLng latLng, float zoom, String title) {
        google_Map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        if (!title.equals("My Location")) {
            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(title);
            google_Map.addMarker(markerOptions);
        }
        hideInput();
    }

    private void initMap() {
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.n__map_map);
        supportMapFragment.getMapAsync(N_MapActivity.this);
    }

    private void getLocationPermission() {
        String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(N_MapActivity.this, FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(N_MapActivity.this, COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Location_Permission_Granted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(N_MapActivity.this, permission, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(N_MapActivity.this, permission, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Location_Permission_Granted = false;
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            Location_Permission_Granted = false;
                            return;
                        }
                    }
                    Location_Permission_Granted = true;
                    initMap();
                }
            }
        }
    }

    private void hideInput() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private AdapterView.OnItemClickListener adapterView_onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            hideInput();
            final AutocompletePrediction autocompletePrediction = arrayAdapterN_map.getItem(position);
            final String place_id = autocompletePrediction.getPlaceId();
            PendingResult<PlaceBuffer> placeBufferPendingResult = Places.GeoDataApi.getPlaceById(googleApiClient, place_id);
            placeBufferPendingResult.setResultCallback(updatePlaceDetailsCallback);
        }
    };
    private ResultCallback<PlaceBuffer> updatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(PlaceBuffer places) {
            if (!places.getStatus().isSuccess()) {
                places.release();
                return;
            }
            final Place place = places.get(0);
            n_info = new N_Info();
            try {
                n_info.setName(place.getName().toString());
                n_info.setAddress(place.getAddress().toString());
                n_info.setPhoneNumber(place.getPhoneNumber().toString());
                n_info.setId(place.getId());
                n_info.setWebsiteUri(place.getWebsiteUri());
                n_info.setLatLng(place.getLatLng());
                n_info.setRating(place.getRating());
                n_info.setAttributions(place.getAttributions().toString());
            } catch (NullPointerException npe) {

            }
            moveCamera(new LatLng(place.getViewport().getCenter().latitude, place.getViewport().getCenter().longitude), DEFAULT_ZOOM, n_info);
            places.release();
        }
    };
}
