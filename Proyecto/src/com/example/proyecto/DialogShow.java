package com.example.proyecto;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.view.Window;
import android.widget.ImageView;


public class DialogShow {

    Dialog view;
    private Activity activity;
    public  DialogShow(Activity activity, Bitmap img){
        this.activity = activity;
        view = new Dialog(activity);
        view.requestWindowFeature(Window.FEATURE_NO_TITLE);
        view.setContentView(R.layout.dialog_foto);

        ImageView foto = (ImageView) view.findViewById(R.id.imageView);
        foto.setImageBitmap(img);

        view.show();


    }

    public void builder(){
        try{



        }   catch (Exception e){}
    }
}
