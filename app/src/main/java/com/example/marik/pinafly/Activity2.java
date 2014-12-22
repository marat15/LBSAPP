package com.example.marik.pinafly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;



public class Activity2 extends Activity {

    public Button DB;
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



    public void Database1(View view) {
        Intent mainDB = new Intent(this, DBMainActivity.class);
        startActivity(mainDB);
    }

   // public void Monitor(View view) {
   //     Intent allDemos = new Intent(this, Monitor.class);
   //     startActivity(allDemos);
   // }

    public void Monitor (View view) {
        Intent monitor = new Intent(this, Monitor.class);
        startActivity(monitor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }


}
