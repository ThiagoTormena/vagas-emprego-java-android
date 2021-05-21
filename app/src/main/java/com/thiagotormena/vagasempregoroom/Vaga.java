package com.thiagotormena.vagasempregoroom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vaga {

    @PrimaryKey(autoGenerate = true)
    int vagaId;

    String descricao;
    int horas;
    double valor;

    public Vaga(){  }
    public Vaga(int vagaId, String descricao, int horas, double valor){
        this.vagaId = vagaId;
        this.descricao = descricao;
        this.horas = horas;
        this.valor = valor;
    }

    public int getVagaId() {
        return vagaId;
    }

    public void setVagaId(int vagaId) {
        this.vagaId = vagaId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString(){
        return this.vagaId + ": " + this.descricao;
    }
}
