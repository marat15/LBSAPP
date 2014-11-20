package com.example.marik.pinafly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private EditText username = null;
    private EditText password = null;
    private TextView attempts;
    private Button login;
    private Button register;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editText1);
        password = (EditText) findViewById(R.id.editText2);
        attempts = (TextView) findViewById(R.id.textView5);
        attempts.setText(Integer.toString(counter));
        login = (Button) findViewById(R.id.button1);


    }

    public void login(View view) {
        if (username.getText().toString().equals("admin") &&
                password.getText().toString().equals("admin")) {
            Toast.makeText(getApplicationContext(), "Redirecting...",
                    Toast.LENGTH_SHORT).show();
            Intent activity2;
            activity2 = new Intent(this, Activity2.class);
            startActivity(activity2);
        } else {
            Toast.makeText(getApplicationContext(), "Wrong Credentials",
                    Toast.LENGTH_SHORT).show();
            //attempts.setBackgroundColor(Color.RED);
            //counter--;
            //attempts.setText(Integer.toString(counter));
            //if(counter==0){
            //    login.setEnabled(false);
        }

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
