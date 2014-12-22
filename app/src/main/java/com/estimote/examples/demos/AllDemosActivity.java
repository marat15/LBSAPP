package com.estimote.examples.demos;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.marik.pinafly.R;

/**
 * Shows all available demos.
 *
 * @author wiktor@estimote.com (Wiktor Gworek)
 */
public class AllDemosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.all_demos);

        findViewById(R.id.distance_demo_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllDemosActivity.this, com.estimote.examples.demos.ListBeaconsActivity.class);
                intent.putExtra(com.estimote.examples.demos.ListBeaconsActivity.EXTRAS_TARGET_ACTIVITY, com.estimote.examples.demos.DistanceBeaconActivity.class.getName());
                startActivity(intent);
            }
        });
        findViewById(R.id.notify_demo_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllDemosActivity.this, com.estimote.examples.demos.ListBeaconsActivity.class);
                intent.putExtra(com.estimote.examples.demos.ListBeaconsActivity.EXTRAS_TARGET_ACTIVITY, com.estimote.examples.demos.NotifyDemoActivity.class.getName());
                startActivity(intent);
            }
        });
        findViewById(R.id.characteristics_demo_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllDemosActivity.this, com.estimote.examples.demos.ListBeaconsActivity.class);
                intent.putExtra(com.estimote.examples.demos.ListBeaconsActivity.EXTRAS_TARGET_ACTIVITY, com.estimote.examples.demos.CharacteristicsDemoActivity.class.getName());
                startActivity(intent);
            }
        });
    }

    public void onClick (View view){
        setContentView(R.layout.all_demos);
    }
}
