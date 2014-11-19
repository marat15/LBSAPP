package com.example.marik.pinafly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends android.support.v4.app.FragmentActivity {

    private GoogleMap mMap;
    MarkerOptions markerOptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment)).getMap();
        mMap.setMyLocationEnabled(true);
        mMap.getMyLocation();
        /*Map Types*/
       // mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
       // mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition args) {
                //TODO Auto-generated method stub
                LatLng latLng=mMap.getCameraPosition().target;
                Toast.makeText(getApplicationContext(), "Location: " + latLng, Toast.LENGTH_SHORT).show();

            }});

        /*Markers with lat/long coordinates*/
        final LatLng FirstMarker = new LatLng(74 , 40.34);
        final LatLng SecondMarker = new LatLng(55, 66);
        final LatLng ThirdMarker = new LatLng(66, 55);
        Marker MA1 = mMap.addMarker(new MarkerOptions().position(FirstMarker).title("Kathryns Apartment"));
        Marker MA2 = mMap.addMarker(new MarkerOptions().position(SecondMarker).title("Kathryns Apartment"));
        Marker MA3 = mMap.addMarker(new MarkerOptions().position(ThirdMarker).title("Kathryns Apartment"));}

//

//
    public void camera1(View view) {
        Intent camera1 = new Intent(this, ImagePickActivity.class);
        startActivity(camera1);

    }
    @Override
    protected void onStart() {
        super.onStart();  // Always call the superclass method first



    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first

    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

    }

    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first


    }


    @Override
    protected void onRestart() {
        super.onRestart();  // Always call the superclass method first

        // Activity being restarted from stopped state
    }

    @Override
    public void onDestroy() {
        super.onDestroy();  // Always call the superclass
    }
}

////////Kathryn and Liz

