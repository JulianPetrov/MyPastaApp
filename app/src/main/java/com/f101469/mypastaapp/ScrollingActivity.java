package com.f101469.mypastaapp;

import static com.f101469.mypastaapp.ui.PastaListAdapter.generateRandomBigDecimalFromInRange;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.f101469.mypastaapp.api.PastaApi;
import com.f101469.mypastaapp.model.Pasta;
import com.f101469.mypastaapp.model.PastaApiResponseDTO;
import com.f101469.mypastaapp.repository.PastaAppRepository;
import com.f101469.mypastaapp.ui.PastaListAdapter;
import com.f101469.mypastaapp.ui.PastaListViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Transaction;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.f101469.mypastaapp.databinding.ActivityScrollingBinding;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ScrollingActivity extends AppCompatActivity {

  public static final int NEW_PASTA_ACTIVITY_REQUEST_CODE = 1;
  private ActivityScrollingBinding binding;
  private ProgressBar progressBar;
  private PastaListViewModel pastaListViewModel;
  private ImageButton addToFavouritesButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    // deleteDatabaseFile("pasta_app_db");

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

    pastaListViewModel = new ViewModelProvider(this).get(PastaListViewModel.class);
    pastaListViewModel.getPastaList().observe(this, pastaListAdapter::submitList);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), FavouritesActivity.class);
                startActivity(intent);
              }
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
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    /*if (id == R.id.action_settings) {
      return true;
    }*/
    return super.onOptionsItemSelected(item);
  }

  public void deleteDatabaseFile(String databaseName) {
    File databases = new File(getApplicationInfo().dataDir + "/databases");
    File db = new File(databases, databaseName);

    if (db.delete()) Log.d("DBProgress", "Database deleted successfully");
    else Log.d("DBProgress", "Failed to delete database.");
  }


}
