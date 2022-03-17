package br.com.ismaellunkes.pluvioapp.persistencia;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import br.com.ismaellunkes.pluvioapp.model.Registro;

@Dao
public interface RegistroDao {

    @Insert
    long insert(Registro registro);

    @Delete
    void delete(Registro registro);

    @Update
    void update(Registro registro);

    @Query("SELECT * FROM registro WHERE id = :id")
    Registro findById(long id);

    @Query("SELECT * FROM registro")
    List<Registro> findAll();

}
