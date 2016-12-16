package com.bjl.tannum.c_one.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import java.nio.IntBuffer;
import java.util.List;
import java.util.Random;

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


        Log.d("debug","start login activity");
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
        InitDB();


        //Get product list in db when db exists
        //mProductList = mDBHelper.getListProduct();
        //Get product list in db when db exists
      //  mProductList = mDBHelper.getListProduct();
       // int jj = 1;

    }

    private void InitDB(){

        //Mask: Check exists database
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(database.exists() == false ){

            Log.d("debug","database not exist -> get readable database and copy database");
            mDBHelper.getReadableDatabase();

            //Copy db
            if(copyDatabase(this)){
                Log.d("debug","Copy database success");
            }
            else{
                Log.d("debug","Coppy datablse error");
            }
        }
        else
        {
            Log.d("debug", "Database exist");
        }
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
            boolean result = LoginHandler();
            if(result == true){
                Log.d("debug","Login Success+++");
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else
            {
                Log.d("debug","Login Error---");
            }

        }
        else if(view == btnSignup){
            Log.d("debug","btn signup was clicked");
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    //int UserId = 0;
    private boolean LoginHandler(){

        boolean result;
        Random r = new Random();
        int UserId = r.nextInt();

        String userName;
        String password;

        Log.d("debug","LoginHandler");
        userName = editTextUserName.getText().toString();
        password = editTextPassword.getText().toString();
        result = false;
        //Mask: check user exist.
        Cursor res = mDBHelper.SqlSelect("select * from user_login where user_name = "+"'"+userName+"'");
        if(res.getCount() == 0){
            Log.d("debug","No user in database : verify user with server");
            //Mask: no user -> verify user with server
            result = true;

            //Mask: Save user to tb_login_info
            boolean result1 = mDBHelper.InsertData_tbLoginInfo(UserId++,userName,1);
            Log.d("debug","insert data = " + String.valueOf(result1));

        }
        else {
            result = true;
            Log.d("debug", "Have user in database : update status");
            //have user
            if (res.moveToNext() == true) {

                //Mask: Get data from database
                int user_active = res.getInt(2);
                UserId = res.getInt(0);
                //Mask: update data if user status = 0
                Log.d("debug", "user active = " + user_active);
                if (user_active != 1) {
                    Log.d("debug", "user not active -> update user status");
                    //Mask: Udate user status = 1
                    boolean result1 = mDBHelper.UpdateData_tbLoginInfo(UserId, userName, 1);
                    Log.d("debug", "update data = " + String.valueOf(result1));
                }
            }
        }
        return result;

    }
}
