package com.example.marik.pinafly;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends Activity {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private String provider;

    Intent myIntent = new Intent(this, ImagePickActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getGPSInfo();
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment)).getMap();
        mMap.setMyLocationEnabled(true);
        mMap.getMyLocation();


       // Button camera1  = (Button) findViewById(R.id.camera);
        //camera1.setOnClickListener(new View.OnClickListener() {
          //  public void onClick(View v) {

            //    startActivity(myIntent);
            //}
        //});




        /*Map Types*/
       // mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
       // mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        /*Markers with lat/long coordinates*/
        final LatLng FirstMarker = new LatLng(74 , 40.34);
        final LatLng SecondMarker = new LatLng(55, 66);
        final LatLng ThirdMarker = new LatLng(66, 55);
        Marker MA1 = mMap.addMarker(new MarkerOptions().position(FirstMarker).title("Kathryns Apartment"));
        Marker MA2 = mMap.addMarker(new MarkerOptions().position(SecondMarker).title("Kathryns Apartment"));
        Marker MA3 = mMap.addMarker(new MarkerOptions().position(ThirdMarker).title("Kathryns Apartment"));

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
      /*  boolean enabledGPS = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean enabledWiFi = service
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!enabledGPS) {
            Toast.makeText(this, "GPS signal not found", Toast.LENGTH_LONG).show();
            Intent turnOn = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(turnOn);
        }*/

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        /*if (location != null) {
            Toast.makeText(this, "Selected Provider " + provider,
                    Toast.LENGTH_SHORT).show();
            onLocationChanged(location);
        } else {

            //do something
        }*/

            }

    /*public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        Toast.makeText(this, "Location " + lat + "," + lng,
                Toast.LENGTH_LONG).show();
        LatLng coordinate = new LatLng(lat, lng);
        Toast.makeText(this, "Location " + coordinate.latitude + "," + coordinate.longitude,
                Toast.LENGTH_LONG).show();
        Marker startPerc = mMap.addMarker(new MarkerOptions()
                .position(coordinate)
                //.title("!")
                //.snippet("Inizio del percorso")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin)));
    }*/


    public void  startActivityForResult (Intent intent, int requestCode) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
   private void getGPSInfo() {
        Criteria criteria = new Criteria();
        String provider;
        Location location;
        LocationManager locationmanager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);

        if (locationmanager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
          provider = locationmanager.getBestProvider(criteria, false);
        location = locationmanager.getLastKnownLocation(provider);

      //  } else {
        //  showGPSDisabledAlertToUser();
        }
    }

//    private void showGPSDisabledAlertToUser() {
  //      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MapActivity.this);
    //    alertDialogBuilder
      //          .setMessage(
        //                "GPS is disabled. Would you like to enable it?")
          //      .setCancelable(false)
            //    .setPositiveButton("GPS Settings",
              //          new DialogInterface.OnClickListener() {
                //            public void onClick(DialogInterface dialog, int id) {
                  //              startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    //            Intent callGPSSettingIntent = new Intent(
                      //                  android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        //        startActivity(callGPSSettingIntent);

                          //  }
                        //});

        //alertDialogBuilder.setNegativeButton("Cancel",
          //      new DialogInterface.OnClickListener() {
            //        public void onClick(DialogInterface dialog, int id) {

              //          dialog.cancel();
                //    }
                //});
        //AlertDialog alert = alertDialogBuilder.create();
        //alert.show();
    //}// ;



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

////////Kathryn and Liz
}
