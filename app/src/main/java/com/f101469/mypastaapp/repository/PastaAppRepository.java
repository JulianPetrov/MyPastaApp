package com.f101469.mypastaapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.f101469.mypastaapp.database.PastaAppDB;
import com.f101469.mypastaapp.database.PastaAppDao;
import com.f101469.mypastaapp.model.Pasta;

import java.util.List;

public class PastaAppRepository {

  private final PastaAppDao pastaAppDao;
  public LiveData<List<Pasta>> allPastas;

  public PastaAppRepository(Application application) {
    PastaAppDB pastaAppDB = PastaAppDB.getDatabase(application);
    pastaAppDao = pastaAppDB.pastaAppDao();
    allPastas = pastaAppDao.getAllPastas();
  }

  public LiveData<List<Pasta>> getAllPastas() {
    return allPastas;
  }

  public Pasta getPastaByName(String pastaName){
      return pastaAppDao.getPastaByName(pastaName);
  }

  public void insert(Pasta pasta) {
    PastaAppDB.DB_WRITE_EXECUTOR.execute(
        () -> {
          pastaAppDao.insertPasta(pasta);
        });
  }

  public void insertPastas(List<Pasta> pastaList) {
    PastaAppDB.DB_WRITE_EXECUTOR.execute(
        () -> {
          pastaAppDao.insertPastas(pastaList);
        });
  }

  public void update(Pasta pasta) {
    PastaAppDB.DB_WRITE_EXECUTOR.execute(
        () -> {
          pastaAppDao.updatePasta(pasta);
        });
  }

  public void updatePastas(List<Pasta> pastaList) {
    PastaAppDB.DB_WRITE_EXECUTOR.execute(
        () -> {
          pastaAppDao.updatePastas(pastaList);
        });
  }

  public void delete(Pasta pasta) {
    PastaAppDB.DB_WRITE_EXECUTOR.execute(
        () -> {
          pastaAppDao.deletePasta(pasta);
        });
  }
}
