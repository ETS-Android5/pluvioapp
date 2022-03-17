package br.com.ismaellunkes.pluvioapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Registro implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public long id;
    @NonNull
    public String dataHoraRegistro;
    @NonNull
    public Integer precipitacao;
    @NonNull
    public boolean isLigouIrrigacao;
    @NonNull
    public String responsavel;
    @NonNull
    public String locais;

    public Registro() { }

    public Registro(long id, String dataHoraRegistro, Integer precipitacao, boolean isLigouIrrigacao, String responsavel, String locais) {
        this.id = id;
        this.dataHoraRegistro = dataHoraRegistro;
        this.precipitacao = precipitacao;
        this.isLigouIrrigacao = isLigouIrrigacao;
        this.responsavel = responsavel;
        this.locais = locais;
    }

    @Override
    public String toString() {
        return  "Hora: " + dataHoraRegistro +
                "  |  Precip. (MM): " + precipitacao +
                "\nLocais: " + locais +
                "  |  Ligou Irrig.: " + (isLigouIrrigacao ? "SIM" : "NÃO") +
                "\nResponsável: " + responsavel;
    }

    public String getRegistroResumido(){
        return  "Hora: " + dataHoraRegistro +
                "\nPrecip. (MM): " + precipitacao;
    }
}
