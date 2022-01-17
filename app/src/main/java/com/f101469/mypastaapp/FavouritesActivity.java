package com.f101469.mypastaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.CollapsingToolbarLayout;

public class FavouritesActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_favourites);
    ActionBar supportActionBar = getSupportActionBar();
    if (supportActionBar != null) supportActionBar.setDisplayHomeAsUpEnabled(true);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        NavUtils.navigateUpFromSameTask(this);
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
}
