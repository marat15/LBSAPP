package com.example.marik.pinafly;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class EstimoteReceiver extends BroadcastReceiver {
    private Intent estimoteServiceIntent;

    // Method called when bluetooth is turned on or off.
    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
            final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                    BluetoothAdapter.ERROR);
            switch (state) {
                case BluetoothAdapter.STATE_TURNING_OFF:
                    // When bluetooth is turning off, lets stop estimotes ranging
                    if (estimoteServiceIntent != null) {
                        context.stopService(estimoteServiceIntent);
                        estimoteServiceIntent = null;
                    }
                    break;
                case BluetoothAdapter.STATE_ON:
                    // When bluethooth is turned on, let's start estimotes monitoring
                    if (estimoteServiceIntent == null) {
                        estimoteServiceIntent = new Intent(context,
                                EstimoteManager.class);
                        context.startService(estimoteServiceIntent);
                    }
                    break;
            }
        }
    }
}