package com.example.marik.pinafly;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

//---add this---
import com.estimote.sdk.Beacon;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.BeaconManager.MonitoringListener;
import com.estimote.sdk.Region;
import com.example.marik.pinafly.R;

import static com.estimote.sdk.BeaconManager.MonitoringListener;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import static com.example.marik.pinafly.Monitor.isAppInForeground;

public class Ranging extends Monitor {

    private static final String TAG= "EstimoteiBeacon";
    private static final int NOTIFICATION_ID = 123;

    private BeaconManager beaconManager;
    private NotificationManager notificationManager;
    private Region region;


    private static final String
            ESTIMOTE_PROXIMITY_UUID =
            "B9407F30-F5F8-466E-AFF9-25556B57FE6D";

    private static final Region ALL_ESTIMOTE_BEACONS =
            new Region("regionId", ESTIMOTE_PROXIMITY_UUID,                    null, null);


    EditText txtUUID1, txtUUID2;
    EditText txtMajor1, txtMajor2;
    EditText txtMinor1, txtMinor2;
    EditText txtRssi1, txtRssi2;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beacon_info);

        // ---get references to all the views in the
        // activity---
        txtUUID1 = (EditText)
                findViewById(R.id.txtUUID1);

        txtUUID2 = (EditText)
                findViewById(R.id.txtUUID2);

        txtMajor1 = (EditText)
                findViewById(R.id.txtMajor1);

        txtMajor2 = (EditText)
                findViewById(R.id.txtMajor2);

        txtMinor1 = (EditText)
                findViewById(R.id.txtMinor1);

        txtMinor2 = (EditText)
                findViewById(R.id.txtMinor2);

        txtRssi1 = (EditText)
                findViewById(R.id.txtRssi1);

        txtRssi2 = (EditText)
                findViewById(R.id.txtRssi2);

        beaconManager = new BeaconManager(this);
        notificationManager = (NotificationManager)
                getSystemService(
                        Context.NOTIFICATION_SERVICE);

        //---by default you scan 5s and then wait 25s
        // for this demo, you will scan more
        // frequently---
        beaconManager.setBackgroundScanPeriod(
                TimeUnit.SECONDS.toMillis(1), 0);

        beaconManager.setMonitoringListener(new MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> beacons) {
                if (isAppInForeground(
                        getApplicationContext())) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Entered region",
                            Toast.LENGTH_LONG).show();

                    //---range for beacons---
                    try {
                        beaconManager.startRanging(
                                ALL_ESTIMOTE_BEACONS);
                    } catch (RemoteException e) {
                        Log.e(TAG,
                                "Cannot start ranging", e);
                    }
                } else {
                    postNotification("Entered region");
                }
            }

            @Override
            public void onExitedRegion(Region region) {
                if (isAppInForeground(
                        getApplicationContext())) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Exited region",
                            Toast.LENGTH_LONG).show();
                } else {
                    postNotification("Exited region");
                }





                //---stop ranging for beacons---
                try {
                    beaconManager.stopRanging(
                            ALL_ESTIMOTE_BEACONS);
                } catch (RemoteException e) {
                    Log.e(TAG,
                            e.getLocalizedMessage());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //---clear the second group of
                        // EditText fields---
                        txtUUID2.setText("");
                        txtMajor2.setText("");
                        txtMinor2.setText("");
                        txtRssi2.setText("");

                        //---clear the first group of
                        // EditText fields---
                        txtUUID1.setText("");
                        txtMajor1.setText("");
                        txtMinor1.setText("");
                        txtRssi1.setText("");
                    }
                });
            }
        });

        //---called when beacons are found---
        beaconManager.setRangingListener(new
                                                 BeaconManager.RangingListener() {
                                                     @Override public void onBeaconsDiscovered(
                                                             Region region, final List<Beacon> beacons)
                                                     {
                                                         Log.d(TAG, "Ranged beacons: " +
                                                                 beacons);

                                                         runOnUiThread(new Runnable() {
                                                             @Override
                                                             public void run() {
                                                                 if (beacons.size() > 0) {
                                                                     Beacon iBeacon1 = null,
                                                                             iBeacon2 = null;
                                                                     //---get the first
                                                                     // iBeacon---
                                                                     iBeacon1 = beacons.get(0);

                                                                     if (beacons.size()>1) {
                                                                         //---get the second
                                                                         // iBeacon---
                                                                         iBeacon2 =
                                                                                 beacons.get(1);
                                                                     }
                                                                     //---first iBeacon---
                                                                     txtUUID1.setText(
                                                                             iBeacon1.
                                                                                     getProximityUUID().
                                                                                     toString());
                                                                     txtMajor1.setText(
                                                                             String.valueOf(
                                                                                     iBeacon1.getMajor()));
                                                                     txtMinor1.setText(
                                                                             String.valueOf(
                                                                                     iBeacon1.getMinor()));
                                                                     txtRssi1.setText(
                                                                             String.valueOf(
                                                                                     iBeacon1.getRssi()));

                                                                     if (iBeacon2!=null) {
                                                                         //---second iBeacon-
                                                                         txtUUID2.setText(
                                                                                 iBeacon2.
                                                                                         getProximityUUID().
                                                                                         toString());
                                                                         txtMajor2.setText(
                                                                                 String.valueOf(
                                                                                         iBeacon2.
                                                                                                 getMajor()));
                                                                         txtMinor2.setText(
                                                                                 String.valueOf(
                                                                                         iBeacon2.
                                                                                                 getMinor()));
                                                                         txtRssi2.setText(
                                                                                 String.valueOf(
                                                                                         iBeacon2.
                                                                                                 getRssi()));
                                                                     } else {
                                                                         //---clear the second
                                                                         // group of EditText
                                                                         // fields---
                                                                         txtUUID2.setText("");
                                                                         txtMajor2.setText("");
                                                                         txtMinor2.setText("");
                                                                         txtRssi2.setText("");
                                                                     }
                                                                 } else {
                                                                     //---clear the first group
                                                                     // of EditText fields---
                                                                     txtUUID1.setText("");
                                                                     txtMajor1.setText("");
                                                                     txtMinor1.setText("");
                                                                     txtRssi1.setText("");
                                                                 }
                                                             }
                                                         });
                                                     }
                                                 });
    }

    //---stop ranging for beacons when activity is killed---
    @Override
    protected void onStop() {
        super.onStop();
        try {
            beaconManager.stopRanging(
                    ALL_ESTIMOTE_BEACONS);
        } catch (RemoteException e) {
            Log.e(TAG, "Cannot stop", e);
        }
    }

}