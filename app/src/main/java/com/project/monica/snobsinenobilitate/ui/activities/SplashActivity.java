package com.project.monica.snobsinenobilitate.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * SnobSineNobilitate requirement - AWARE that this is an ANTI PATTERN.
 */
public class SplashActivity extends Activity {

    private static final int MSG_DELAY = 2000;
    private static final int LAUNCH_INTENT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == LAUNCH_INTENT)
                {
                    Intent intent = new Intent(SplashActivity.this, NavDrawerActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        h.sendEmptyMessageDelayed(LAUNCH_INTENT, MSG_DELAY);
    }

}
