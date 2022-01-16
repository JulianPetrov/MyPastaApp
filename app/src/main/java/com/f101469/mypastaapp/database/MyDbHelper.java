/*
package com.f101469.mypastaapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.f101469.mypastaapp.api.PastaApi;
import com.f101469.mypastaapp.model.Pasta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyDbHelper extends SQLiteOpenHelper {

  private Context context;
  private static final String DATABASE_NAME = "PastaApp.db";
  private static final int DATABASE_VERSION = 1;

  private static final String TABLE_NAME = "pastas";
  private static final String COLUMN_ID = "_id";
  private static final String COLUMN_NAME = "name";
  private static final String COLUMN_PRICE = "price";
  private static final String COLUMN_THUMBNAIL_LOCATION = "thumbnail_location";

  private MutableLiveData<ArrayList<Pasta>> pastaList;

  public MyDbHelper(@Nullable Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    this.context = context;
  }

  @Override
  public void onCreate(SQLiteDatabase db) {

    Call<List<Pasta>> pastaListApiCall = PastaApi.getApiInstance().getService().fetchAllPastas();
    pastaListApiCall.enqueue(
        new Callback<List<Pasta>>() {
          @Override
          public void onResponse(Call<List<Pasta>> call, Response<List<Pasta>> response) {
            List<Pasta> fetchedPastaList = response.body();
            ArrayList<Pasta> pastaWithDetailsList =
                getDetailsOfPastasWithExtraApiCall(fetchedPastaList);
            pastaList.setValue(pastaWithDetailsList);
          }

          @Override
          public void onFailure(Call<List<Pasta>> call, Throwable t) {
            call.cancel();
          }
        });

    String query =
        "CREATE TABLE "
            + TABLE_NAME
            + " ("
            + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME
            + " TEXT, "
            + "ingredient1"
            + " TEXT, "
            + "ingredient2"
            + " TEXT, "
            + "ingredient3"
            + " TEXT, "
            + "ingredient4"
            + " TEXT, "
            + "ingredient5"
            + " TEXT, "
            + "ingredient6"
            + " TEXT, "
            + "ingredient7"
            + " TEXT, "
            + "ingredient8"
            + " TEXT, "
            + "ingredient9"
            + " TEXT, "
            + "ingredient10"
            + " TEXT, "
            + COLUMN_PRICE
            + " TEXT, "
            + COLUMN_THUMBNAIL_LOCATION
            + " TEXT)";
    db.execSQL(query);
  }

  private ArrayList<Pasta> getDetailsOfPastasWithExtraApiCall(List<Pasta> fetchedPastaList) {
    ArrayList<Pasta> pastaWithDetailsList = new ArrayList<>();
    if (fetchedPastaList != null) {
      for (Pasta pasta : fetchedPastaList) {
        Call<Pasta> pastaDetailsApiCall =
            PastaApi.getApiInstance().getService().fetchPastaDetailsById(pasta.getIdMeal());
        pastaDetailsApiCall.enqueue(
            new Callback<Pasta>() {
              @Override
              public void onResponse(Call<Pasta> call, Response<Pasta> response) {
                Pasta pastaWithDetails = response.body();
                pastaWithDetailsList.add(pastaWithDetails);
              }

              @Override
              public void onFailure(Call<Pasta> call, Throwable t) {
                call.cancel();
              }
            });
      }
    }
    return pastaWithDetailsList;
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
      onCreate(db);
  }
}
*/
