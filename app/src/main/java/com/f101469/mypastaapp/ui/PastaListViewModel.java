package com.f101469.mypastaapp.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.f101469.mypastaapp.model.Pasta;
import com.f101469.mypastaapp.repository.PastaAppRepository;

import java.util.List;

public class PastaListViewModel extends AndroidViewModel {
    private PastaAppRepository pastaAppRepository;
    private final LiveData<List<Pasta>> pastaList;

    public PastaListViewModel(@NonNull Application application) {
        super(application);
        pastaAppRepository = new PastaAppRepository(application);
        pastaList = pastaAppRepository.getAllPastas();
    }

    public LiveData<List<Pasta>> getPastaList() {
        return pastaList;
    }

    public void insertPasta(Pasta pasta){
        pastaAppRepository.insert(pasta);
    }
}
