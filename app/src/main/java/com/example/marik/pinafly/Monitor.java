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

public class Monitor extends Activity {

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
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);


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

        beaconManager.setMonitoringListener(new
                                                    MonitoringListener() {
                                                        @Override
                                                        public void onEnteredRegion(Region region, List<Beacon>beacons)
                                                        {
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

    protected void postNotification(String msg) {
        Intent notifyIntent = new
                Intent(Monitor.this,
                Monitor.class);

        notifyIntent.setFlags(
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent =
                PendingIntent.getActivities(
                        Monitor.this, 0, new Intent[] {
                                notifyIntent },
                        PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new
                Notification.Builder(Monitor.this)
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