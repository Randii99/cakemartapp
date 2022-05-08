package com.example.mad_submission_2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBhandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Database.db";

    public DBhandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AddOff.Offers.TABLE_NAME + " (" +
                    AddOff.Offers._ID + " INTEGER PRIMARY KEY," +
                    AddOff.Offers.COLUMN_1 + " TEXT," +
                    AddOff.Offers.COLUMN_2 + " TEXT," +
                    AddOff.Offers.COLUMN_3 + " TEXT," +
                    AddOff.Offers.COLUMN_4 + " TEXT," +
                    AddOff.Offers.COLUMN_5 + " TEXT," +
                    AddOff.Offers.COLUMN_6 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AddOff.Offers.TABLE_NAME;


    //Add Function
    public long addInfo(String offerName, String startAndEndDate, String discount, String previousPrice, String currentPrice, String offerType) {

        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(AddOff.Offers.COLUMN_1, offerName);
        values.put(AddOff.Offers.COLUMN_2, startAndEndDate);
        values.put(AddOff.Offers.COLUMN_3, discount);
        values.put(AddOff.Offers.COLUMN_4, previousPrice);
        values.put(AddOff.Offers.COLUMN_5, currentPrice);
        values.put(AddOff.Offers.COLUMN_6, offerType);


// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(AddOff.Offers.TABLE_NAME, null, values);
        return newRowId;

    }

//Update Function

    public boolean updateInfo(String offerName,String startAndEndDate,String discount,String previousPrice,String currentPrice, String offerType){

        SQLiteDatabase db = getWritableDatabase();

// New value for one column

        ContentValues values = new ContentValues();
        values.put(AddOff.Offers.COLUMN_1, offerName);
        values.put(AddOff.Offers.COLUMN_2, startAndEndDate);
        values.put(AddOff.Offers.COLUMN_3,discount);
        values.put(AddOff.Offers.COLUMN_4, previousPrice);
        values.put(AddOff.Offers.COLUMN_5, currentPrice);
        values.put(AddOff.Offers.COLUMN_6, offerType);


// Which row to update, based on the title
        String selection = AddOff.Offers.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { offerName };

        int count = db.update(
                AddOff.Offers.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        if(count>=1){

            return true;

        }else{

            return false;

        }

    }

    //Delete

    public void deleteInfo(String offerName){
        SQLiteDatabase db = getWritableDatabase();
        // Define 'where' part of query.
        String selection = AddOff.Offers.COLUMN_1 + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = { offerName };
// Issue SQL statement.
        int deletedRows = db.delete(AddOff.Offers.TABLE_NAME, selection, selectionArgs);


    }


    //Read all

    public List readAllInfo(){

        String offerName="Birthday Offer";
        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                AddOff.Offers.COLUMN_1,
                AddOff.Offers.COLUMN_2,
                AddOff.Offers.COLUMN_3,
                AddOff.Offers.COLUMN_4,
                AddOff.Offers.COLUMN_5,
                AddOff.Offers.COLUMN_6,





        };

// Filter results WHERE "title" = 'My Title'
        String selection = AddOff.Offers.COLUMN_1 + " = ?";
        String[] selectionArgs = { offerName };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                AddOff.Offers.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                AddOff.Offers.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );


        List usernames = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(
                    cursor.getColumnIndexOrThrow(AddOff.Offers.COLUMN_1));
            usernames.add(user);
        }
        cursor.close();
        return usernames;

    }



//Override

    public List readAllInfo(String offerName){


        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                AddOff.Offers.COLUMN_1,
                AddOff.Offers.COLUMN_2,
                AddOff.Offers.COLUMN_3,
                AddOff.Offers.COLUMN_4,
                AddOff.Offers.COLUMN_5,
                AddOff.Offers.COLUMN_6,





        };

// Filter results WHERE "title" = 'My Title'
        String selection =  AddOff.Offers.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { offerName };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                AddOff.Offers.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                AddOff.Offers.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );


        List userinfo = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow( AddOff.Offers.COLUMN_1));
            String startAndEndDate = cursor.getString(cursor.getColumnIndexOrThrow( AddOff.Offers.COLUMN_2));
            String discount = cursor.getString(cursor.getColumnIndexOrThrow( AddOff.Offers.COLUMN_3));
            String previousPrice = cursor.getString(cursor.getColumnIndexOrThrow( AddOff.Offers.COLUMN_4));
            String currentPrice= cursor.getString(cursor.getColumnIndexOrThrow( AddOff.Offers.COLUMN_5));
            String  offerType= cursor.getString(cursor.getColumnIndexOrThrow( AddOff.Offers.COLUMN_6));

            userinfo.add(user);//0
            userinfo.add(startAndEndDate);//1
            userinfo.add(discount);//2
            userinfo.add(previousPrice);//3
            userinfo.add(currentPrice);//4
            userinfo.add( offerType);//5
        }
        cursor.close();
        return userinfo;

    }
}


