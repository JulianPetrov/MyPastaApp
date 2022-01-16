package com.f101469.mypastaapp.api;

import com.f101469.mypastaapp.model.Pasta;
import com.f101469.mypastaapp.model.PastaApiResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PastaApiService {

    @GET("filter.php?c=Pasta")
    Call<PastaApiResponseDTO> fetchAllPastas();

    @GET("lookup.php?")
    Call<PastaApiResponseDTO> fetchPastaDetailsById(@Query(value = "i") Long id);
}
