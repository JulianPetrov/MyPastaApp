package com.f101469.mypastaapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.f101469.mypastaapp.model.Pasta;

import java.util.List;

@Dao
public interface PastaAppDao {

  @Query("SELECT * FROM pastas")
  LiveData<List<Pasta>> getAllPastas();

  @Query("SELECT * FROM pastas WHERE idMeal = :id")
  Pasta getPastaById(long id);

  @Query("SELECT * FROM pastas WHERE name = :pastaName")
  Pasta getPastaByName(String pastaName);

  @Insert
  void insertPasta(Pasta pasta);

  @Insert
  void insertPastas(List<Pasta> pastaList);

  @Delete
  void deletePasta(Pasta pasta);

  @Update
  void updatePasta(Pasta pasta);

  @Update
  void updatePastas(List<Pasta> pastaList);

  @Query("DELETE FROM pastas")
  void deleteAllPastas();

  @Query("SELECT * FROM pastas WHERE is_favourite=1")
  LiveData<List<Pasta>> getAllFavouritePastas();
}
