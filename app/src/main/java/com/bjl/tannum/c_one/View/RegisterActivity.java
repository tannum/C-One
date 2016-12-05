package com.bjl.tannum.c_one.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.bjl.tannum.c_one.Lib.CameraApi;
import com.bjl.tannum.c_one.R;
import com.mikhaellopez.circularimageview.CircularImageView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{


    CircularImageView registerImg;
    Button regiterButton;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerImg = (CircularImageView)findViewById(R.id.register_pic);
        regiterButton = (Button)findViewById(R.id.btnRegister);
        registerImg.setOnClickListener(this);
        regiterButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v == registerImg){
            Log.d("debug","register image was click");
            takePicture();
        }
        else if(v == regiterButton){
            Log.d("debug","reginter button was click");
        }
    }


    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            registerImg.setImageBitmap(imageBitmap);
        }
        else{
            //Mask: Todo nothing
        }
    }
}
