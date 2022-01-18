package com.f101469.mypastaapp;

import android.content.Intent;
import android.os.Bundle;

import com.f101469.mypastaapp.ui.PastaListAdapter;
import com.f101469.mypastaapp.ui.PastaListViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

public class ScrollingActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    setContentView(R.layout.content_scrolling);
    setContentView(R.layout.activity_scrolling);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
    toolBarLayout.setTitle(getTitle());

    RecyclerView pastaListRecyclerView = findViewById(R.id.pasta_list);
    PastaListAdapter pastaListAdapter = new PastaListAdapter(new PastaListAdapter.PastaDiff());
    pastaListRecyclerView.setAdapter(pastaListAdapter);
    pastaListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    PastaListViewModel pastaListViewModel =
        new ViewModelProvider(this).get(PastaListViewModel.class);
    pastaListViewModel.getPastaList().observe(this, pastaListAdapter::submitList);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(
        view -> {
          Intent intent = new Intent(getBaseContext(), FavouritesActivity.class);
          startActivity(intent);
        });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_scrolling, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    return super.onOptionsItemSelected(item);
  }
}
