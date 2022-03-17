package br.com.ismaellunkes.pluvioapp.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"registroId", "localId"})
public class RegistroLocal {
    public long registroId;
    public long localId;
}
