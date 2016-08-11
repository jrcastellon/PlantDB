package com.jorgecastellonjr.plantdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.view.Menu.FIRST;

/**
 * Created by jorgecastellonjr. on 8/3/16.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "plantDB";
    private static final String TABLE_NAME = "plant";
    private static final int DATABASE_VERSION = 1;
    private static final String ID = " id";
    private static final String NAME = " name";
    private static final String TYPE = " type";
    private static final String DATE = " date";
    private static final String LAT = " latitude";
    private static final String LNG = " longitude";
    private static final String ICON = " icon";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " VARCHAR(255), "
            + TYPE + " VARCHAR(255), "
            + DATE + " VARCHAR(255),"
            + LAT + " REAL, "
            + LNG + " REAL,"
            + ICON + " INTEGER"
            + ");";
    private static final String DROP_TABLE = "CROP TABLE " + TABLE_NAME + " IF EXISTS";
    private Context context;

    public MyDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this. context = context;
    }

    @Override
    public void onCreate (SQLiteDatabase db){
        Log.i(MainActivity.TAG, "creating the database now");
        try{
            db.execSQL(CREATE_TABLE);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        try{
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addPlant(Plant plant){
        ContentValues values = new ContentValues();
        values.put(NAME, plant.name);
        values.put(TYPE, plant.type);
        values.put(DATE, plant.date);
        values.put(LAT, plant.lat);
        values.put(LNG, plant.lng);
        values.put(ICON, plant.icon);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
        Log.i(MainActivity.TAG, plant.name + " is added");
    }

    public ArrayList<Plant> getPlants(){
        ArrayList<Plant> people = new ArrayList<>();
        String query = "Select * FROM " + TABLE_NAME + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            Plant person = new Plant();
            person.name = cursor.getString(1);
            person.type = cursor.getString(2);
            person.date = cursor.getString(3);
            person.lat = Double.parseDouble(cursor.getString(4));
            person.lng = Double.parseDouble(cursor.getString(5));
            person.icon = Integer.parseInt(cursor.getString(6));

            people.add(person);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return people;
    }

    public boolean removePlant(Plant plant){
        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_NAME, ID + "=" + plant._id, null);

        if (rows > 0)
            result = true;

        db.close();
        return result;
    }
}