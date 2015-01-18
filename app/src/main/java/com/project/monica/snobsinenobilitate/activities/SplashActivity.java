package com.project.monica.snobsinenobilitate.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start immediately the main activity. This activity is used to present a
        // splash meanwhile
        // zygote is creating the process to start the app
        Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 0)
                {
                    Intent intent = new Intent(SplashActivity.this, NavDrawerActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        h.sendEmptyMessageDelayed(0, 3000);
    }



}
