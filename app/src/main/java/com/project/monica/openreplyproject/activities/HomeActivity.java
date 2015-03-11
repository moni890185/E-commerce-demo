package com.project.monica.openreplyproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.project.monica.openreplyproject.R;

public class HomeActivity extends FragmentActivity implements View.OnClickListener {
  private Button mFirstConcept;
  private Button mSecondConcept;
  private Button mThirdConcept;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    mFirstConcept = (Button) findViewById(R.id.home_first_concept_id);
    mSecondConcept = (Button) findViewById(R.id.home_second_concept_id);
    mThirdConcept = (Button) findViewById(R.id.home_third_concept_id);
    mFirstConcept.setOnClickListener(this);
    mSecondConcept.setOnClickListener(this);
    mThirdConcept.setOnClickListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_home, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public void onClick(View v) {
    if (v.getId() == R.id.home_first_concept_id) {
      launchActivity(ProductListActivity.class);
    } else if (v.getId() == R.id.home_second_concept_id) {
      launchActivity(ProductListVarSizeActivity.class);
    } else if (v.getId() == R.id.home_third_concept_id) {
      launchActivity(ProductListSwipeActivity.class);
    }
  }

  private void launchActivity(Class c) {
    Intent intent = new Intent(this, c);
    startActivity(intent);
  }
}
