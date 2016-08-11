package com.jorgecastellonjr.plantdb;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jorgecastellonjr. on 8/10/16.
 */

public class CustomArrayAdapter extends ArrayAdapter<Plant> {
    Context context;
    int layoutResourceId;
    ArrayList<Plant> data = null;

    public CustomArrayAdapter(Context context, int layoutResourceId, ArrayList<Plant> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        CustomObjectHolder holder = null;
        if (row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);

            holder = new CustomObjectHolder();
            holder.textName = (TextView) row.findViewById(R.id.txtv_name);
            holder.textDate = (TextView) row.findViewById(R.id.txtv_date);
            holder.icon = (ImageView) row.findViewById(R.id.imageView);

            row.setTag(holder);
        }else{
            holder = (CustomObjectHolder) row.getTag();
        }

        Plant plant = data.get(position);
        holder.textName.setText(plant.getName());
        holder.textDate.setText(plant.getDate());
        holder.icon.setImageResource(plant.getIcon());
        return row;
    }

    static class CustomObjectHolder{
        TextView textName;
        TextView textDate;
        ImageView icon;
    }
}
