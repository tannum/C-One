package com.bjl.tannum.c_one.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bjl.tannum.c_one.Model.Library.GPSTracker;
import com.bjl.tannum.c_one.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnForgot;
    Button btnLogin;
    Button btnSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnForgot = (Button) findViewById(R.id.btnForgotPwd);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnSignup = (Button)findViewById(R.id.btnSignUp);
        btnForgot.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);

        GPSTracker gps;
        //Mask: initial gps and check location service
        gps = new GPSTracker(this);
        if(gps.canGetLocation() == false){
            gps.showSettingsAlert();
        }
        else
        {
            double lad = gps.getLatitude();
            double lon = gps.getLongitude();
            double aa = 10.000;
            Toast.makeText(getApplicationContext(),"support gps = " + String.valueOf(aa) + " " + String.valueOf(lon)  ,Toast.LENGTH_LONG).show();


        }



    }


    @Override
    public void onClick(View view) {
        if(view == btnForgot){
            Log.d("debug","btn forgot was clicked");
            Intent intent = new Intent(LoginActivity.this,ResetPasswordActivity.class);
            startActivity(intent);
        }
        else if(view == btnLogin){
            Log.d("debug","btn login was clicked");
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else if(view == btnSignup){
            Log.d("debug","btn signup was clicked");
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }


    }
}
