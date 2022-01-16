package com.f101469.mypastaapp.api;

import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class PastaApi {
  private static final PastaApi API_INSTANCE = new PastaApi();
  private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

  private final PastaApiService service;

  private PastaApi() {
    Moshi moshi = new Moshi.Builder().build();
    Retrofit retrofit =
        new Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build();
    service = retrofit.create(PastaApiService.class);
  }

  public static PastaApi getApiInstance() {
    return API_INSTANCE;
  }

  public PastaApiService getService() {
    return service;
  }
}
