package com.f101469.mypastaapp;

import static com.f101469.mypastaapp.ui.PastaListAdapter.generateRandomBigDecimalFromInRange;

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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
   // deleteDatabaseFile("pasta_app_db");

    setContentView(R.layout.activity_scrolling);
    progressBar = findViewById(R.id.progress_bar);

    setContentView(R.layout.content_scrolling);

    RecyclerView pastaListRecyclerView = findViewById(R.id.pasta_list);
    PastaListAdapter pastaListAdapter = new PastaListAdapter(new PastaListAdapter.PastaDiff());
    pastaListRecyclerView.setAdapter(pastaListAdapter);
    pastaListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    pastaListViewModel = new ViewModelProvider(this).get(PastaListViewModel.class);
    pastaListViewModel.getPastaList().observe(this, pastaListAdapter::submitList);

    binding = ActivityScrollingBinding.inflate(getLayoutInflater());
    //setContentView(binding.getRoot());
    Toolbar toolbar = binding.toolbar;
    setSupportActionBar(toolbar);
    CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
    toolBarLayout.setTitle(getTitle());

    FloatingActionButton fab = binding.fab;
    fab.setOnClickListener(
            new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
              }
            });





  }

  /*private void fetchPastas(ArrayList<Pasta> pastaFetchedList) {
    progressBar.setVisibility(View.VISIBLE);
    Call<PastaApiResponseDTO> pastaListApiCall =
        PastaApi.getApiInstance().getService().fetchAllPastas();
    pastaListApiCall.enqueue(
        new retrofit2.Callback<PastaApiResponseDTO>() {
          @Override
          public void onResponse(
              Call<PastaApiResponseDTO> call, Response<PastaApiResponseDTO> response) {
            if (!response.isSuccessful()) {
              Log.d("Status code", "Code: " + response.code());
              return;
            }
            if (response.body() != null) {
              PastaApiResponseDTO responseBody = response.body();
              List<Pasta> fetchedPastaList = responseBody.getMeals();
              //fetchedPastaList = getDetailsOfPastasWithExtraApiCall(fetchedPastaList);
              pastaFetchedList.addAll(fetchedPastaList);
              pastaList.addAll(fetchedPastaList);
              pastaAppRepository.insertPastas(pastaList);
              pastaListAdapter.notifyDataSetChanged();
              progressBar.setVisibility(View.GONE);
              Log.d("FetchedMainPastas", fetchedPastaList.toString());
            }
          }

          @Override
          public void onFailure(Call<PastaApiResponseDTO> call, Throwable t) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(ScrollingActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT)
                .show();
            Log.d("ApiException", t.getMessage());
            call.cancel();
          }
        });
  }

  private ArrayList<Pasta> getDetailsOfPastasWithExtraApiCall(List<Pasta> fetchedPastaList) {
    ArrayList<Pasta> pastaWithDetailsList = new ArrayList<>();
    if (fetchedPastaList != null) {
      for (Pasta pasta : fetchedPastaList) {
        Call<PastaApiResponseDTO> pastaDetailsApiCall =
                PastaApi.getApiInstance().getService().fetchPastaDetailsById(pasta.getIdMeal());
        pastaDetailsApiCall.enqueue(
            new retrofit2.Callback<PastaApiResponseDTO>() {
              @Override
              public void onResponse(
                  Call<PastaApiResponseDTO> call, Response<PastaApiResponseDTO> response) {
                if (!response.isSuccessful()) {
                  Log.d("Status code", "Code: " + response.code());
                  return;
                }
                if (response.body() != null) {
                  PastaApiResponseDTO responseBody = response.body();
                  Pasta pastaWithDetails = responseBody.getMeals().get(0);
                  Log.d("PastaDetailsResponse", pastaWithDetails.toString());

                  if (pastaWithDetails != null) {
                    pastaWithDetails.setBasePrice(
                            generateRandomBigDecimalFromInRange(new BigDecimal(6), new BigDecimal(12))
                                    .doubleValue());
                    pastaWithDetails.setFavourite(false);
                    pastaWithDetailsList.add(pastaWithDetails);
                    pastaAppRepository.update(pastaWithDetails);
                    pastaListAdapter.notifyDataSetChanged();

                  }
                }
              }

              @Override
              public void onFailure(Call<PastaApiResponseDTO> call, Throwable t) {
                Log.d("ApiException", t.getMessage());
                call.cancel();
              }
            });
      }
    }
    return pastaWithDetailsList;
  }*/

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
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public void deleteDatabaseFile(String databaseName) {
    File databases = new File(getApplicationInfo().dataDir + "/databases");
    File db = new File(databases, databaseName);

    if (db.delete()) Log.d("DBProgress", "Database deleted successfully");
    else Log.d("DBProgress", "Failed to delete database.");
  }

}
