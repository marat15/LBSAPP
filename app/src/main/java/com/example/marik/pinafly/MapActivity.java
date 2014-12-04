package com.example.marik.pinafly;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
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
        //setUpMap();
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
                LatLng latLng = mMap.getCameraPosition().target;
                Toast.makeText(getApplicationContext(), "Location: " + latLng, Toast.LENGTH_SHORT).show();

                //Location locationA = new Location("point A");

                //locationA.setLatitude(40.768);
                //locationA.setLongitude(-73.964);

                //Location locationB = new Location("point B");

                //locationB.setLatitude(40.760);
                //locationB.setLongitude(-73.600);

                //float distance = locationA.distanceTo(locationB);

            }
        });

        /*Markers with lat/long coordinates*/
        final LatLng FirstMarker = new LatLng(40.768, -73.964);
        final LatLng SecondMarker = new LatLng(40.760, -73.600);
        final LatLng ThirdMarker = new LatLng(40.763, -73.66);
        Marker MA1 = mMap.addMarker(new MarkerOptions().position(FirstMarker).title("Beacon1 - Kathryns Apartment"));
        Marker MA2 = mMap.addMarker(new MarkerOptions().position(SecondMarker).title("Beacon2 - Liz's Apartment"));
        Marker MA3 = mMap.addMarker(new MarkerOptions().position(ThirdMarker).title("Beacon3 - Empty Apartment"));
    }


//
private void setUpMap(){
    if (mMap != null) {
        return;
    }
    mMap=((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment)).getMap();
    if (mMap == null) {
        return;
    }
}
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

