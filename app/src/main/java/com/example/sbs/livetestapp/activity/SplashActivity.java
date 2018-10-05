package com.example.sbs.livetestapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sbs.livetestapp.R;
import com.example.sbs.livetestapp.prefrence.SaveData;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.e("11111",""+SaveData.getUserName(getApplicationContext()));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(SaveData.getUserName(getApplicationContext()).equals("")){
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        },2000);
    }
}
