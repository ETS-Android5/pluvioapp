package br.com.ismaellunkes.pluvioapp.persistencia;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import br.com.ismaellunkes.pluvioapp.model.Local;
import br.com.ismaellunkes.pluvioapp.model.Registro;

public class RegistroWithLocais {

    @Embedded public Registro registro;
    @Relation(
            entity = Registro.class,
            parentColumn = "localId",
            entityColumn = "registroId"
    )

    public List<Local> locais;

}
