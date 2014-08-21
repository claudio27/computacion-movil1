package com.example.proyecto;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;


public class getPosition {

    public Context context;
    public boolean error = false;
    public boolean end = false;
    public boolean on = false;
    public
    LocationManager lm;
    LocationListener ll;

    public void off() {
        try {

            End();
            pri = true;
            on = false;
        } catch (Exception e) {
            Log.e("GOV", "off :( " + e);
        }
    }

    public void On() {
        lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        ll = new mylocationlistener();

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
        on = true;
    }

    public getPosition(Context context) {

        this.context = context;
        try {


        } catch (Exception e) {
            error = true;
        }
    }

    public void demo() {
        Location location = new Location("demo");
        location.setLongitude(-70.568923);
        location.setLatitude(-33.407693);


        pri = false;
        Mylocation = location;
        pri();
    }



    private Mapav2 map;

    public getPosition(Activity activity, Context context, boolean demo, Mapav2 map) {
        this.show = true;

        this.context = context;
        this.map = map;
        try {
            if (demo) {
                Location location = new Location("demo");
                location.setLongitude(-70.568923);
                location.setLatitude(-33.407693);


                pri = false;
                Mylocation = location;
                pri();

            }


        } catch (Exception e) {
            error = true;
        }

    }
    public boolean notMap = false;
    public getPosition(Activity activity, Context context, boolean demo) {
        this.show = true;

        this.context = context;
        this.map = map;
        notMap = true;
        try {
            if (demo) {
                Location location = new Location("demo");
                location.setLongitude(-70.568923);
                location.setLatitude(-33.407693);


                pri = false;
                Mylocation = location;
                pri();

            }


        } catch (Exception e) {
            error = true;
        }

    }


    public void End() {
        this.end = true;
        try {
            lm.removeUpdates(ll);
        } catch (Exception e) {
        }
    }

    Dialog espera;
    private boolean showespera = false;

    public void showEspera() {
        try {
            showespera = true;
            espera = new Dialog(context);
            espera.requestWindowFeature(Window.FEATURE_NO_TITLE);
            espera.setContentView(R.layout.popup_gps);
            espera.setCancelable(false);
            espera.show();

        } catch (Exception e) {
            Log.e("GOV", "Show Espera :( " + e);
        }
    }

    public void pri() {
        try {
            if (!end) {
                if (showespera)
                    espera.dismiss();
                //   if (show)
                   // img.setImageResource(R.drawable.gpson);

            }
        } catch (Exception e) {
        }

        try {
            if(!notMap)
            map.setCenter(Mylocation);

        } catch (Exception e) {
        }

    }

    public boolean isEnable() {
        return !pri;
    }

    public Location getLocation() {
        return Mylocation;
    }

    public Double getVel() {
        try {
            Double speed = Mylocation.getSpeed() * 3.6;
            return speed;
        } catch (Exception e) {
            return 0.0;
        }
    }

    public boolean show = false;

    private Handler handlerAct = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
          //      if (correcto)
                  //  txt.setText("" + 1);
            //    else
                  //  txt.setText("" + 0);

            } catch (Exception e) {
                Log.v("GOV", "handlerMapa :( " + e);
            }
        }
    };

    public float mdistancia(Location locationA, Location locationB) {

        return locationA.distanceTo(locationB);

    }

    public long getSeconds(Location locationA, Location locationB) {

        long a = locationA.getTime();
        long b = locationB.getTime();

        return (a - b) / 1000;

    }


    public boolean isCorrect(Location location) {
        // Log.v("GOV", "is Correct :"+(getSeconds(Mylocation,location)*35) +" V/S" + mdistancia(Mylocation,location));

        if ((getSeconds(Mylocation, location) * 40) <= mdistancia(Mylocation, location)) {
            correcto = true;
            return true;
        } else {
            try {
                lm.sendExtraCommand(LocationManager.GPS_PROVIDER, "delete_aiding_data", null);

            } catch (Exception e) {
            }
            correcto = false;
            return false;

        }
    }


    public String getLat(){
        return ""+ Mylocation.getLatitude();
    }

    public String getLon(){
        return ""+Mylocation.getLongitude();
    }


    public boolean correcto = false;
    public Location Mylocation;
    public boolean pri = true;
    private int Ierror = 0;

    public class mylocationlistener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null && !end) {
                try {


                    if (((int) location.getAccuracy()) < 50) {
                        Ierror = (int) location.getAccuracy();


                        if (pri) {
                            pri = false;
                            Mylocation = location;
                            pri();
                        } else {

                            if (isCorrect(location)) {

                                Mylocation = location;
                            }

                            handlerAct.sendEmptyMessage(0);

                        }
                    }
                } catch (Exception e) {
                }
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    }
}

