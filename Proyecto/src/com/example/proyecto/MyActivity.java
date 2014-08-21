package com.example.proyecto;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.io.ByteArrayOutputStream;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    public getPosition position;
    private int CAMERA_REQUEST = 1234;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button camera = (Button) this.findViewById(R.id.camera);
        Button Lugares = (Button) this.findViewById(R.id.lugares);
        Button Galeria = (Button) this.findViewById(R.id.galeria);

        camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);


            }
        });
        Lugares.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(MyActivity.this, Mapa.class);

                startActivity(myIntent);


            }
        });
        Galeria.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               // startActivityForResult(intent, 122);
                startActivity(intent);

            }
        });
        position = new getPosition(this, this, false);
        position.showEspera();
        position.On();

    }


    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            try{
            Bitmap photo = (Bitmap) intent.getExtras().get("data");

            DataBase dataBase = new DataBase(MyActivity.this);
            dataBase.insertImage("titulo",position.getLat(),position.getLon(),BitMapToString(photo));
            MediaStore.Images.Media.insertImage(getContentResolver(), photo, "ucentral" , "ucentral");
                Log.v("GOV", "camara Ok");
            }catch (Exception e){    Log.e("GOV", "camara" +e); }
        }
        if(requestCode == 122){
        Uri selectedImage = intent.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(
                selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();


        Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
          DialogShow asd = new DialogShow(MyActivity.this,yourSelectedImage);
        }
    }


    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

}
