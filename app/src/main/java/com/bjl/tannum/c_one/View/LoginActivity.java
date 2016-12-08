package com.bjl.tannum.c_one.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bjl.tannum.c_one.Database.DatabaseHelper;
import com.bjl.tannum.c_one.Model.DataInfo.ProductInfo;
import com.bjl.tannum.c_one.Model.Library.GPSTracker;
import com.bjl.tannum.c_one.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnForgot;
    Button btnLogin;
    Button btnSignup;
    EditText editTextUserName;
    EditText editTextPassword;
    SharedPreferences.Editor sharedPreferences;

    private DatabaseHelper mDBHelper;
    private List<ProductInfo> mProductList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Mask: Init SharePreferences
        sharedPreferences = getSharedPreferences("USERINFO",MODE_PRIVATE).edit();


        //Mask: Init Component
        btnForgot = (Button) findViewById(R.id.btnForgotPwd);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnSignup = (Button)findViewById(R.id.btnSignUp);
        editTextUserName = (EditText)findViewById(R.id.editTextUserName);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        btnForgot.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);



        //Mask: Database setting
        mDBHelper = new DatabaseHelper(this);
        //Check exists database
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(false == database.exists()){
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(this)){
                Toast.makeText(this,"Copy database success",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Copy database error",Toast.LENGTH_SHORT).show();
            }

            //Get product list in db when db exists
            mProductList = mDBHelper.getListProduct();
        }
        //Get product list in db when db exists
      //  mProductList = mDBHelper.getListProduct();
       // int jj = 1;

    }

    private boolean copyDatabase(Context context) {
        try {
            Log.d("debug", "Copy Database start");
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            Log.d("debug", "Target location = " + outFileName);
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.d("debug", "copy data base success");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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


    private boolean LoginHandler(){

        boolean result;
        int userId;
        String userName;
        String password;

        userName = editTextUserName.getText().toString();
        password = editTextPassword.getText().toString();

        //Mask: Network Authentication
        //...


        //Mask: Manual fill result
        userId =1;
        result  = true;

        if(result == true){
            //Mask: commit shared user data
            sharedPreferences.putString("user_id",String.valueOf(userId));
            sharedPreferences.putString("user_name",userName);
            sharedPreferences.commit();

        }

        return result;

    }
}
