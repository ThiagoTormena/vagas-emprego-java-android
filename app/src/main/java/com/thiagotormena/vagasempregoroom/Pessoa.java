package com.thiagotormena.vagasempregoroom;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Vaga.class, parentColumns = "vagaId", childColumns = "vagaId"))
public class Pessoa {

    @PrimaryKey(autoGenerate = true)
    int pessoaId;

    int vagaId;
    String nome;
    String cpf;
    String email;
    String telefone;

    public Pessoa(){}

    public Pessoa(int pessoaId, int vagaId, String nome, String cpf, String email, String telefone){
        this.pessoaId = pessoaId;
        this.vagaId = vagaId;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }

    public int getVagaId() {
        return vagaId;
    }

    public void setVagaId(int vagaId) {
        this.vagaId = vagaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Candidato " + this.nome + " à vaga nº " + this.vagaId;
    }
}
