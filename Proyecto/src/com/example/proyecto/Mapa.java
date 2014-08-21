package com.example.proyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import java.util.List;


public class Mapa extends FragmentActivity implements TextToSpeech.OnInitListener,GoogleMap.OnMarkerClickListener {

    public Mapav2 mapa;
    getPosition position;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa);
        GoogleMap mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getMap();
        mapa = new Mapav2(this);
        mapa.setUpMapIfNeeded();
        mMap.setOnMarkerClickListener(this);
        position = new getPosition(this, this, false, mapa);
        position.On();
        position.showEspera();
        getAllImage();


    }

    public List<Imagen> fotos;

    public void getAllImage(){
        try{

            DataBase dataBase = new DataBase(Mapa.this);
           fotos = dataBase.getAllImage();
            mapa.addMarkerFoto(fotos);




        }catch (Exception e){}
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

       try{
           String [] data = marker.getTitle().split(" ");

           int id = Integer.parseInt(data[0]);

           boolean found = false;
           Imagen aux = null;
           for(int i =0; i<fotos.size(); i++){

               aux = fotos.get(i);
               if(id == aux.id){
                   found = true;
                   break;
               }

           }
             if(found && aux != null)  {
           DialogShow dialogShow = new DialogShow(Mapa.this,aux.base64Tobitmap());
             }



       }   catch (Exception e){}

        return true;
    }

    @Override
    public void onInit(int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
