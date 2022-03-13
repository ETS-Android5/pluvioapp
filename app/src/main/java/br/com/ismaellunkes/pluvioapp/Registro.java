package br.com.ismaellunkes.pluvioapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Registro implements Serializable {

    private String dataHoraRegistro;
    private Integer precipitacao;
    private List <String> locais;
    private boolean isLigouIrrigacao;
    private String responsavel;

    public String getDataHoraRegistro() {
        return dataHoraRegistro;
    }

    public void setDataHoraRegistro(String dataHoraRegistro) {
        this.dataHoraRegistro = dataHoraRegistro;
    }

    public Integer getPrecipitacao() {
        return precipitacao;
    }

    public void setPrecipitacao(Integer precipitacao) {
        this.precipitacao = precipitacao;
    }

    public List<String> getLocais() {
        return locais;
    }

    public void setLocais(List<String> locais) {
        this.locais = locais;
    }

    public boolean isLigouIrrigacao() {
        return isLigouIrrigacao;
    }

    public void setLigouIrrigacao(boolean ligouIrrigacao) {
        isLigouIrrigacao = ligouIrrigacao;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
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
