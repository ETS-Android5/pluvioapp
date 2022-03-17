package br.com.ismaellunkes.pluvioapp.persistencia;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.ismaellunkes.pluvioapp.model.Local;
import br.com.ismaellunkes.pluvioapp.model.Registro;
import br.com.ismaellunkes.pluvioapp.model.RegistroLocal;

@Database(entities = {Registro.class}, version = 2, exportSchema = false)
public abstract class PluvioDatabase extends RoomDatabase {

    public abstract RegistroDao registroDao();

    private static PluvioDatabase instance;

    public static PluvioDatabase getDatabase(final Context context) {

        if(instance == null) {
            synchronized (PluvioDatabase.class){
                if (instance == null) {
                    instance = Room.databaseBuilder(context,
                                                    PluvioDatabase.class,
                                                    "pluvioapp.db").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }

}
