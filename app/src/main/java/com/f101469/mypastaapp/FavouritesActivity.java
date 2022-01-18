package com.f101469.mypastaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.f101469.mypastaapp.ui.PastaListAdapter;
import com.f101469.mypastaapp.ui.PastaListViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class FavouritesActivity extends AppCompatActivity {

  private PastaListViewModel pastaFavouritesViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_favourites);
    ActionBar supportActionBar = getSupportActionBar();
    if (supportActionBar != null) supportActionBar.setDisplayHomeAsUpEnabled(true);

    setContentView(R.layout.content_scrolling);
    setContentView(R.layout.activity_scrolling);

    RecyclerView pastaListRecyclerView = findViewById(R.id.pasta_list);
    PastaListAdapter pastaListAdapter = new PastaListAdapter(new PastaListAdapter.PastaDiff());
    pastaListRecyclerView.setAdapter(pastaListAdapter);
    pastaListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    pastaFavouritesViewModel = new ViewModelProvider(this).get(PastaListViewModel.class);
    pastaFavouritesViewModel.getPastaFavouritesList().observe(this, pastaListAdapter::submitList);
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
