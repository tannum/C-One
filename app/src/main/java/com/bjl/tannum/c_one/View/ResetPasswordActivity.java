package com.bjl.tannum.c_one.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bjl.tannum.c_one.R;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnResetPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        btnResetPwd = (Button)findViewById(R.id.btnResetPassword);
        btnResetPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == btnResetPwd){
            //Mask: todo anything
        }

    }
}
