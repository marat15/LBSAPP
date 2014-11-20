package com.example.marik.pinafly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class Activity2 extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher);


    }

    public void bluetooth(View view) {
        Intent intent = new Intent(this, BluetoothActivity.class);
        startActivity(intent);
    }

    public void map(View view) {
        Intent intent1 = new Intent(this, MapActivity.class);
        startActivity(intent1);
    }

    public void camera(View view) {
        Intent camera = new Intent(this, ImagePickActivity.class);
        startActivity(camera);

    }

    //public void NavigationFragmentDrawer(View view) {
    //  Intent navigationDrawerFragment = new Intent(this, .class);
    //startActivity(navigationDrawerFragment);

    //}

    public void AllDemos(View view) {
        Intent allDemos = new Intent(this, AllDemosActivity.class);
        startActivity(allDemos);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }


}
