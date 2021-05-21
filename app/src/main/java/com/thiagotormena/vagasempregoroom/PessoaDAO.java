package com.thiagotormena.vagasempregoroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PessoaDAO {
    @Query("SELECT * FROM Pessoa WHERE pessoaId = :pessoaId LIMIT 1")
    Pessoa get(int pessoaId);

    @Query("SELECT * FROM Pessoa")
    List<Pessoa> getAll();

    @Query("SELECT * FROM Pessoa WHERE pessoaId IN (:pessoaId)")
    List<Pessoa> loadAllbyIds(int[] pessoaId);

    @Query("SELECT * FROM Pessoa WHERE nome LIKE :nome LIMIT 1")
    Pessoa findByNome(String nome);

    @Query("UPDATE Pessoa SET nome =:nome WHERE pessoaId == :pessoaId")
    void updateNome(String nome, int pessoaId);

    @Query("UPDATE Pessoa SET cpf =:cpf WHERE pessoaId == :pessoaId")
    void updateCpf(String cpf, int pessoaId);

    @Query("UPDATE Pessoa SET email =:email WHERE pessoaId == :pessoaId")
    void updateEmail(String email, int pessoaId);

    @Query("UPDATE Pessoa SET telefone =:telefone WHERE pessoaId == :pessoaId")
    void updateTelefone(String telefone, int pessoaId);

    @Insert
    void insertAll (Pessoa ... pessoa);

    @Update
    void update(Pessoa pessoa);

    @Delete
    void delete(Pessoa pessoa);
}
