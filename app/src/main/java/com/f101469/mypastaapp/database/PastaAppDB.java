package com.f101469.mypastaapp.database;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.f101469.mypastaapp.api.PastaApi;
import com.f101469.mypastaapp.model.Pasta;
import com.f101469.mypastaapp.model.PastaApiResponseDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Response;

@Database(
    entities = {Pasta.class},
    version = 1,
    exportSchema = false)
public abstract class PastaAppDB extends RoomDatabase {

  public abstract PastaAppDao pastaAppDao();

  private ArrayList<Pasta> pastaList = new ArrayList<>();

  private static volatile PastaAppDB DB_INSTANCE;
  private static final int THREAD_COUNT = 4;
  public static final ExecutorService DB_WRITE_EXECUTOR =
      Executors.newFixedThreadPool(THREAD_COUNT);

  private static ArrayList<Pasta> pastasList = new ArrayList<>();

  public static PastaAppDB getDatabase(final Context context) {
    if (DB_INSTANCE == null) {
      synchronized (PastaAppDB.class) {
        if (DB_INSTANCE == null) {
          DB_INSTANCE =
              Room.databaseBuilder(
                      context.getApplicationContext(), PastaAppDB.class, "pasta_app_db")
                  .addCallback(roomDatabaseCallback)
                  .allowMainThreadQueries()
                  .build();
        }
      }
    }
    return DB_INSTANCE;
  }

  private static PastaAppDB.Callback roomDatabaseCallback =
      new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
          super.onCreate(db);
          fetchPastas();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
          super.onOpen(db);
        }
      };

  public static void fetchPastas() {
    Call<PastaApiResponseDTO> pastaListApiCall =
        PastaApi.getApiInstance().getService().fetchAllPastas();
    pastaListApiCall.enqueue(
        new retrofit2.Callback<PastaApiResponseDTO>() {
          @Override
          public void onResponse(
                  @NonNull Call<PastaApiResponseDTO> call, @NonNull Response<PastaApiResponseDTO> response) {
            if (!response.isSuccessful()) {
              Log.d("Status code", "Code: " + response.code());
              return;
            }
            if (response.body() != null) {
              PastaApiResponseDTO responseBody = response.body();
              List<Pasta> fetchedPastaList = responseBody.getMeals();
              fetchedPastaList = getDetailsOfPastasWithExtraApiCall(fetchedPastaList);
              Log.d("FetchedMainPastas", fetchedPastaList.toString());
            }
          }

          @Override
          public void onFailure(Call<PastaApiResponseDTO> call, Throwable t) {
            Log.d("ApiException", t.getMessage());
            call.cancel();
          }
        });
  }

  private static ArrayList<Pasta> getDetailsOfPastasWithExtraApiCall(List<Pasta> fetchedPastaList) {
    ArrayList<Pasta> pastaWithDetailsList = new ArrayList<>();
    if (fetchedPastaList != null) {
      for (Pasta pasta : fetchedPastaList) {
        Call<PastaApiResponseDTO> pastaDetailsApiCall =
            PastaApi.getApiInstance().getService().fetchPastaDetailsById(pasta.getIdMeal());
        pastaDetailsApiCall.enqueue(
            new retrofit2.Callback<PastaApiResponseDTO>() {
              @Override
              public void onResponse(
                  @NonNull Call<PastaApiResponseDTO> call,
                  @NonNull Response<PastaApiResponseDTO> response) {
                if (!response.isSuccessful()) {
                  Log.d("Status code", "Code: " + response.code());
                  return;
                }
                if (response.body() != null) {
                  PastaApiResponseDTO responseBody = response.body();
                  Pasta pastaWithDetails = responseBody.getMeals().get(0);

                  if (pastaWithDetails != null) {
                    Log.d("PastaDetailsResponse", pastaWithDetails.toString());

                    pastaWithDetails.setBasePrice(
                        generateRandomBigDecimalFromInRange(new BigDecimal(6), new BigDecimal(12))
                            .doubleValue());
                    pastaWithDetails.setFavourite(false);
                    pastaWithDetailsList.add(pastaWithDetails);
                    DB_INSTANCE.pastaAppDao().insertPasta(pastaWithDetails);
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
  }

  public static BigDecimal generateRandomBigDecimalFromInRange(BigDecimal min, BigDecimal max) {
    BigDecimal randomBigDecimal =
        min.add(BigDecimal.valueOf(Math.random()).multiply(max.subtract(min)));
    return randomBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
  }
}
