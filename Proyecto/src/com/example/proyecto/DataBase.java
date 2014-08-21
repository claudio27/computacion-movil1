package com.example.proyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_fotos";
    private Context context;

    public DataBase (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static final String TABLE = "fotos";
    public static final String ID_FOTO = "id_foto";
    public static final String TITULO = "titulo";
    public static final String LAT = "lat";
    public static final String LON = "lon";
    public static final String BASE64 = "base64";


    public void insertImage(String titulo , String lat , String lon , String base64){
          try{

              SQLiteDatabase db = this.getReadableDatabase();
              ContentValues values = new ContentValues();
              values.put(TITULO, titulo);
              values.put(LAT, lat);
              values.put(LON, lon);
              values.put(BASE64, base64);
              db.insert(TABLE, null, values);
              db.close();
              Log.v("GOV", "insertImage exitodo");

          }catch (Exception e){   Log.e("GOV", "insertImage :(" + e); }
    }

    public List<Imagen> getAllImage(){
        try {
            List<Imagen> imagenes = new ArrayList<Imagen>();
            String query = "SELECT * FROM " + TABLE ;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);

            Imagen aux;

            if (cursor.moveToFirst()) {
                do {
                    aux = new Imagen();

                    aux.id = cursor.getInt(0);
                    aux.titulo = cursor.getString(1);
                    aux.lat = cursor.getString(2);
                    aux.lon = cursor.getString(3);
                    aux.base64 = cursor.getString(4);
                    imagenes.add(aux);
                } while (cursor.moveToNext());
            }

            db.close();
            return imagenes;
        } catch (Exception e) {
            Log.e("GOV", "getAllMensajes " + e);
            return null;
        }


    }



    public void fist(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE + "("
                    + ID_FOTO + " INTEGER PRIMARY KEY AUTOINCREMENT," + TITULO + " TEXT," + LAT + " TEXT," + LON + " TEXT,"
                    + BASE64 + " TEXT" + ");";

            db.execSQL(CREATE_CONTACTS_TABLE);
            db.close();
            Log.v("GOV", "tabla creada");
        } catch (Exception e) {   Log.e("GOV", "error table: "+e);
        }

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
