package com.example.proyecto;


import android.graphics.Bitmap;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;
import java.util.List;

public class Mapav2 {

    private GoogleMap mMap;
    private FragmentActivity act;

    public Mapav2(FragmentActivity act) {

        this.act = act;
    }

    public void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) act.getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setAllGesturesEnabled(true);

            // Check if we were successful in obtaining the map.
            if (mMap != null) {

            }
        }

        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(false);


    }

    public void setCenter(Location location) {
        try {
            CameraPosition cameraPosition = new CameraPosition.Builder().target(
                    new LatLng(location.getLatitude(), location.getLongitude())).zoom(16).build();

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            LatLng position_actual = new LatLng(location.getLatitude(), location.getLongitude());
            Marker hamburg = mMap.addMarker(new MarkerOptions().position(position_actual)
                    .title("Actual"));

        } catch (Exception e) {
        }
    }




    public void addMarker(Double lat , Double lon, String titulo){
        LatLng position_actual = new LatLng(lat, lon);
        Marker hamburg = mMap.addMarker(new MarkerOptions().position(position_actual)
                .title(""+titulo));
    }


    public void ChangePos() {

    }

    List<Imagen> fotos;
    public void addMarkerFoto(List<Imagen> fotos){
       this.fotos = fotos;




        for( int i =0; i<fotos.size() ; i++){

            Imagen aux = fotos.get(i);

        LatLng position_actual = new LatLng(aux.getLat(),aux.getLon());
            Marker myMarker = mMap.addMarker(new MarkerOptions()
                .position(position_actual)
                .title(aux.id +" " +aux.titulo)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        }

    }


         /*
        @Override
        public boolean onMarkerClick(final Marker marker) {

            if (marker.equals(myMarker))
            {

            }
            return true;
        }       */




}
