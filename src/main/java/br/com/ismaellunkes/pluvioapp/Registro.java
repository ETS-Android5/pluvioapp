package br.com.ismaellunkes.pluvioapp;

import java.util.List;

public class Registro {

    private String dataHoraRegistro;
    private String precipitacao;
    private List <String> locais;
    private boolean isLigouIrrigacao;
    private String responsavel;

    public String getDataHoraRegistro() {
        return dataHoraRegistro;
    }

    public void setDataHoraRegistro(String dataHoraRegistro) {
        this.dataHoraRegistro = dataHoraRegistro;
    }

    public String getPrecipitacao() {
        return precipitacao;
    }

    public void setPrecipitacao(String precipitacao) {
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
        return "Registro{" +
                "dataHoraRegistro='" + dataHoraRegistro + '\'' +
                ", precipitacao=" + precipitacao +
                ", locais=" + locais +
                ", isLigouIrrigacao=" + isLigouIrrigacao +
                ", responsavel='" + responsavel + '\'' +
                '}';
    }
}
