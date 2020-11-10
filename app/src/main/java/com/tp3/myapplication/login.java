package com.tp3.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tapadoo.alerter.Alerter;



public class login extends AppCompatActivity {
EditText username;
EditText password;
Button loginbut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText) findViewById(R.id.log);
        password = (EditText) findViewById(R.id.pass);
        loginbut = (Button) findViewById(R.id.login);

        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    username.setHint("");
                else
                    username.setHint("Username");
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    password.setHint("");
                else
                    password.setHint("Password");
            }
        });
        loginbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!password.getText().toString().trim().equals("123") && !username.getText().toString().trim().equals("123")){

                    Toast.makeText(login.this," "+"wrong password",Toast.LENGTH_LONG).show();

                }else{
                    final Intent in=new Intent(login.this,MainActivity.class);
                    startActivity(in);
                };
            }
        });


    }

}
