package com.jorgecastellonjr.plantdb;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    MyDatabaseHelper myDH;
    public static final String TAG = "MYTAG";
    ArrayList<Plant> plants;
    public static int icon = 0;
    ArrayList<Integer> img;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "TEST");
        myDH = new MyDatabaseHelper(this);
        SQLiteDatabase db = myDH.getWritableDatabase();

        dummyData();
        plants = new ArrayList<>();

        final ArrayList<Plant> plants;
        plants = myDH.getPlants();

        logData();
        this.deleteDatabase("plantDB");
        populateData();

        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new CustomArrayAdapter(this, R.layout.layout_row, plants);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int currentPlant, long id) {
                //Toast toast = Toast.makeText(getApplicationContext(), "Name = " + array.get(currentMovie), Toast.LENGTH_SHORT);
                //toast.show();

                Intent intent = new Intent(getApplicationContext(),DisplayActivity.class);
                intent.putExtra("Plant",plants.get(currentPlant));
                startActivity(intent);

            }
        });

//        myDH.removePlant(plants.get(1));
    }

    private void logData() {
        Log.i(TAG, "There are " + plants.size() + " plants in the list.");

        for (int i = 0; i < plants.size(); i++) {
            Plant plant = plants.get(i);
            String name = plant.name;
            String type = plant.type;
            String date = plant.date;
            Double lat = plant.lat;
            Double lng = plant.lng;

            Log.i(TAG, "Plant " + i + ": " + name + " is  a " + type + " type and was received on "
                    + date + " and located " + lat + " Latitude and " + lng + " longitude.");
        }
    }

    public void dummyData() {
        myDH.addPlant(new Plant("Oak", "Tree", "August 2016", 24.1234, 42.1231, R.drawable.tree));
        myDH.addPlant(new Plant("Palm", "Tree", "August 2016", 24.1234, 42.1231, R.drawable.tree));
        myDH.addPlant(new Plant("Redwood", "Tree", "August 2016", 24.1234, 42.1231, R.drawable.tree));
        myDH.addPlant(new Plant("Sequoia", "Tree", "August 2016", 24.1234, 42.1231, R.drawable.tree));
        myDH.addPlant(new Plant("Willow", "Tree", "August 2016", 24.1234, 42.1231, R.drawable.tree));
        myDH.addPlant(new Plant("Sunflower", "Flower", "August 2016", 24.1234, 42.1231, R.drawable.flower));
        myDH.addPlant(new Plant("Rose", "Flower", "August 2016", 24.1234, 42.1231, R.drawable.flower));
        myDH.addPlant(new Plant("Lavender", "Flower", "August 2016", 24.1234, 42.1231, R.drawable.flower));
        myDH.addPlant(new Plant("Magnolia", "Flower", "August 2016", 24.1234, 42.1231, R.drawable.flower));
        myDH.addPlant(new Plant("Wolfsbane", "Flower", "August 2016", 24.1234, 42.1231, R.drawable.flower));
        myDH.addPlant(new Plant("Corn", "Crops", "December 2015", 24.1545, 42.198763, R.drawable.crops));
        myDH.addPlant(new Plant("Lettece", "Crops", "December 2015", 24.1545, 42.198763, R.drawable.crops));
        myDH.addPlant(new Plant("Radish", "Crops", "December 2015", 24.1545, 42.198763, R.drawable.crops));
        myDH.addPlant(new Plant("Watermelon", "Crops", "December 2015", 24.1545, 42.198763, R.drawable.crops));
        myDH.addPlant(new Plant("Onion", "Crops", "December 2015", 24.1545, 42.198763, R.drawable.crops));
    }

    public void populateData(){
        for (Plant plant: plants){
            plants.add(plant);
        }
    }
}
