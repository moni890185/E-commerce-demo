package com.project.monica.openreplyproject;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    CardView mCardViewLeft;
    CardView mCardViewRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Holder
        mCardViewLeft = (CardView) findViewById(R.id.card_view);

        mCardViewRight = (CardView)findViewById(R.id.card_view_2);

        setContentView(R.layout.activity_main);
    }

}
