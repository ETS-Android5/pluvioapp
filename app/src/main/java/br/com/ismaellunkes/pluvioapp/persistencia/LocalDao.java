package br.com.ismaellunkes.pluvioapp.persistencia;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import br.com.ismaellunkes.pluvioapp.model.Local;

@Dao
public interface LocalDao {

    @Insert
    long insert(Local local);

    @Delete
    void delete(Local local);

    @Update
    void update(Local local);

    @Query("SELECT * FROM local WHERE id = :id")
    Local findById(long id);

    @Query("SELECT * FROM local")
    List<Local> findAll();

    @Transaction
    @Query("SELECT * FROM local WHERE id ")
    public List<RegistroWithLocais> getPlaylistsWithSongs();

}
