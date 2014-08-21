package com.example.proyecto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.util.Base64;

public class Imagen {

    public String titulo;
    public String lat;
    public String lon;
    public String base64;
    public int id;

    public Double getLat(){
        Double aux = Double.parseDouble(lat);
        return aux;
    }
    public Double getLon(){
        Double aux = Double.parseDouble(lon);
        return aux;
    }

    public Bitmap base64Tobitmap(){

        byte[] decodedString = Base64.decode(base64, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    public Imagen(){}



}
