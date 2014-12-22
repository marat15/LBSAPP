package com.example.marik.pinafly;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.sql.SQLException;


public class DBMainActivity extends Activity  {


    EditText date, beacon, location, description, image, beacon2;
    DBDatabaseAdapter dbHelper;

    @Override

    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.databaseuploadtable);


           //  SQLiteDatabase sqLiteDatabase= DBDatabaseAdapter.getWritableDatabase();


            date =         (EditText) findViewById(R.id.editText1);
            beacon =       (EditText) findViewById(R.id.editText2);
            location =     (EditText) findViewById(R.id.editText3);
            description =  (EditText) findViewById(R.id.editText4);
            image =     (EditText) findViewById(R.id.editText5);
            beacon2 =        (EditText) findViewById(R.id.editText6);

            dbHelper = new DBDatabaseAdapter(this);


    }
    public void doUpload (View view) {
        String datee = date.getText().toString();
        String beaconn = beacon.getText().toString();
        String locationn = location.getText().toString();
        String descriptionn = description.getText().toString();
        String imagee = image.getText().toString();



            long id = dbHelper.insertData(datee, beaconn, locationn, descriptionn, imagee);
            if (id < 0) {
                Message.message(this, "Unsuccessful");
            } else {
                Message.message(this, "Successfully Inserted a Row!!!");
            }

    }
    public void viewDetails(View view)
    {
        String data = dbHelper.getAllData();
        Message.message(this, data);
    }
    public void getDetails (View vew)
    {
        String s1 = beacon2.getText().toString();
        String s2 = dbHelper.getData(s1);
        Message.message(this, s2);
    }
}

