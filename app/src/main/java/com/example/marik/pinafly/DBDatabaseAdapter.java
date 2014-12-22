package com.example.marik.pinafly;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;

public class DBDatabaseAdapter  {

    DBHelper helper;
    public DBDatabaseAdapter(Context context) {

        helper = new DBHelper(context);
    }
    public long insertData(String date,String beacon,String coordinates,String description,String image)

    {

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.DATE, date);
        contentValues.put(DBHelper.BEACON, beacon);
        contentValues.put(DBHelper.COORDINATES, coordinates);
        contentValues.put(DBHelper.DESCRIPTION, description);
        contentValues.put(DBHelper.IMAGE, image);
        long id = db.insert(DBHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getAllData()
    {
        SQLiteDatabase db =helper.getReadableDatabase();

        //select _id, Name, Password from DBdata table
        String[] columns={DBHelper.UID, DBHelper.DATE, DBHelper.BEACON,DBHelper.COORDINATES, DBHelper.DESCRIPTION, DBHelper.IMAGE};
        Cursor cursor = db.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer=new StringBuffer();
        while(cursor.moveToNext())
        {
            //int index1 = cursor.getColumnIndex(DBHelper.UID);
            //int cid = cursor.getInt(index1);

            int cid = cursor.getInt(0);
            String date = cursor.getString(1);
            String beacon = cursor.getString(2);
            String coordinates = cursor.getString(3);
            String description = cursor.getString(4);
            String image = cursor.getString(5);
            buffer.append(cid+" "+date+" "+beacon+" "+coordinates+" "+description+" "+image+"\n");
        }
        return buffer.toString();
    }

    public String getData(String beacon)
    {
        SQLiteDatabase db =helper.getReadableDatabase();

        String[] columns={ DBHelper.DATE, DBHelper.BEACON,DBHelper.COORDINATES, DBHelper.DESCRIPTION, DBHelper.IMAGE};
        Cursor cursor = db.query(DBHelper.TABLE_NAME, columns, DBHelper.BEACON+" = '"+ beacon +"'", null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext())
        {

            int index1=cursor.getColumnIndex(DBHelper.DATE);
            int index2=cursor.getColumnIndex(DBHelper.BEACON);
            int index3=cursor.getColumnIndex(DBHelper.COORDINATES);
            int index4=cursor.getColumnIndex(DBHelper.DESCRIPTION);
            int index5=cursor.getColumnIndex(DBHelper.IMAGE);

            String date = cursor.getString(index1);
            String specialbeacon = cursor.getString(index2);
            String coordinates = cursor.getString(index3);
            String description = cursor.getString(index4);
            String image = cursor.getString(index5);
            buffer.append(date + " " + beacon + " " + coordinates + " " + description + " " + image + "\n");
        }
        return buffer.toString();
    }

 static   class DBHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME="DB1";
        private static final String TABLE_NAME = "DBdata";
        private static final int DATABASE_VERSION = 16; //anytime you change database, change the version number
        private static final String UID = "_id";
        private static final String DATE = "Date";
        private static final String BEACON = "Beacon";
        private static final String COORDINATES = "Coordinates";
        private static final String DESCRIPTION = "Description";
        private static final String IMAGE = "Image";


        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME
                + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DATE + " VARCHAR(255), " + BEACON
                + " VARCHAR(255), " + COORDINATES + " VARCHAR(255), " + DESCRIPTION + " VARCHAR(255), " + IMAGE + " BLOB);";
     private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;



        public DBHelper(Context context){
            super (context, DATABASE_NAME, null, DATABASE_VERSION); //context, name of database, custom cursor, version number
            this.context=context;

            Message.message(context, "Constructor called");
        }

    @Override
        public void onCreate(SQLiteDatabase db) {
            //CREATE TABLE VEGETABLE (_id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR((255));
            //String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR((255));";

        try {
                db.execSQL(CREATE_TABLE);
                Message.message(context, "OnCreate called");
            }catch (SQLException e) {
                Message.message(context, ""+e);
            }
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

            try {
               Message.message(context, "on Upgrade Called");//creates new database db
                db.execSQL(DROP_TABLE); //deletes the table with name DROP_TABLE
                onCreate(db);

            }catch (SQLException e){
                Message.message(context, ""+e);
            }
        }
    }
    }


