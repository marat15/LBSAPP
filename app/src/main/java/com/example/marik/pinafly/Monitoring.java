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
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.BeaconManager.MonitoringListener;
import com.estimote.sdk.Region;

public class Monitoring extends Activity {

    private static final String
            ESTIMOTE_PROXIMITY_UUID =
            "B9407F30-F5F8-466E-AFF9-25556B57FE6D";

    private static final Region ALL_ESTIMOTE_BEACONS =
            new Region("regionId", ESTIMOTE_PROXIMITY_UUID,
                    null, null);

    protected static final String TAG =
            "EstimoteiBeacon";

    private static final int NOTIFICATION_ID = 123;

    EditText txtUUID1, txtUUID2;
    EditText txtMajor1, txtMajor2;
    EditText txtMinor1, txtMinor2;
    EditText txtRssi1, txtRssi2;

    BeaconManager beaconManager;
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
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
                    Toast.makeText(
                            getApplicationContext(),
                            "Entered region",
                            Toast.LENGTH_LONG).show();
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
                                                        }
                                                    });
    }

    @Override
    protected void onStart() {
        super.onStart();

        notificationManager.cancel(NOTIFICATION_ID);
        beaconManager.connect(new
                                      BeaconManager.ServiceReadyCallback() {
                                          @Override
                                          public void onServiceReady() {
                                              try {
                                                  beaconManager.startMonitoring(
                                                          ALL_ESTIMOTE_BEACONS);
                                              } catch (RemoteException e) {
                                                  Log.d(TAG,
                                                          "Error while starting monitoring");
                                              }
                                          }
                                      });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        notificationManager.cancel(NOTIFICATION_ID);
        beaconManager.disconnect();
    }

    private void postNotification(String msg) {
        Intent notifyIntent;
        notifyIntent = new
                Intent(Monitoring.this,
                Monitoring.class);

        notifyIntent.setFlags(
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent =
                PendingIntent.getActivities(
                        Monitoring.this, 0, new Intent[] {
                                notifyIntent },
                        PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new
                Notification.Builder(Monitoring.this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Monitoring Region")
                .setContentText(msg)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |=
                Notification.DEFAULT_SOUND;
        notification.defaults |=
                Notification.DEFAULT_LIGHTS;
        notificationManager.notify(NOTIFICATION_ID,
                notification);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the
        // action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //---helper method to determine if the app is in
    // the foreground---
    public static boolean isAppInForeground(
            Context context) {
        List<RunningTaskInfo> task = ((ActivityManager)
                context.getSystemService(
                        Context.ACTIVITY_SERVICE))
                .getRunningTasks(1);
        if (task.isEmpty()) {
            return false;
        }
        return task
                .get(0)
                .topActivity
                .getPackageName()
                .equalsIgnoreCase(
                        context.getPackageName());
    }
}