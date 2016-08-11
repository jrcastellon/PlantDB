package com.jorgecastellonjr.plantdb;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jorgecastellonjr. on 8/3/16.
 */

public class Plant implements Parcelable{
    public int _id;
    public String name;
    public String type;
    public String date;
    public Double lat;
    public Double lng;
    public int icon;

    public Plant (){}

    public Plant(Parcel in){
        name = in.readString();
        type = in.readString();
        date = in.readString();
        lat = in.readDouble();
        lng = in.readDouble();
        icon = in.readInt();
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public Plant createFromParcel (Parcel in){
            return new Plant(in);
        }

        public Plant[] newArray(int size){
            return new Plant[size];
        }
    };
    public Plant (String name, String type, String date, Double lat, Double lng, int icon){
        this.name = name;
        this.type = type;
        this.date = date;
        this.lat = lat;
        this.lng = lng;
        this.icon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(date);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
        dest.writeInt(icon);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this._id = icon;
    }
}
