package br.com.ismaellunkes.pluvioapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Local {

    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    public String local;

    public Local() { }

    public Local(long id, @NonNull String local) {
        this.id = id;
        this.local = local;
    }

    @Override
    public String toString() {
        return "Local{" +
                "local='" + local + '\'' +
                '}';
    }
}
