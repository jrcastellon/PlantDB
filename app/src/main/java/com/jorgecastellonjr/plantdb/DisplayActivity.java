package com.jorgecastellonjr.plantdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtType;
    TextView txtDate;
    TextView txtLat;
    TextView txtLng;
    ImageView icon;

    ArrayList<Integer> img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        txtName = (TextView) findViewById(R.id.txt_name);
        txtType = (TextView) findViewById(R.id.txt_type);
        txtDate = (TextView) findViewById(R.id.txt_date);
        txtLat = (TextView) findViewById(R.id.txt_lat);
        txtLng = (TextView) findViewById(R.id.txt_lng);
        icon = (ImageView) findViewById(R.id.img_icon);

        Intent intent = getIntent();
        Plant plant;

        plant = intent.getParcelableExtra("Plant");

        txtName.setText(plant.name);
        txtType.setText(plant.type);
        txtDate.setText(plant.date);
        txtLat.setText(plant.lat.toString());
        txtLng.setText(plant.lng.toString());
        icon.setImageResource(plant.icon);

    }
}
