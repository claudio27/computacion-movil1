package com.example.proyecto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;


public class Splash extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        DataBase dataBase =new DataBase(Splash.this);
        dataBase.fist();

        Thread tiempo = new Thread() {
            public void run() {
                try {

                    sleep(2000);
                    handlerBD.sendEmptyMessage(0);
                    handlerGonext.sendEmptyMessage(0);



                } catch (Exception e) {
                    Log.v("GOV", "Update :( " + e);
                }
            }

        };
        tiempo.start();


    }

    private Handler handlerBD = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                 DataBase dataBase = new DataBase(Splash.this);
                 dataBase.fist();


            } catch (Exception e) {
                Log.v("GOV", "handlerMapa :( " + e);
            }
        }
    };

    private Handler handlerGonext = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                Intent myIntent = new Intent(Splash.this, MyActivity.class);
                startActivity(myIntent);
                finish();

            } catch (Exception e) {
                Log.v("GOV", "handlerMapa :( " + e);
            }
        }
    };


}
