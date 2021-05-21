package com.thiagotormena.vagasempregoroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface VagaDAO {
    @Query("SELECT * FROM Vaga WHERE vagaId = :vagaId LIMIT 1")
    Vaga get(int vagaId);

    @Query("SELECT * FROM Vaga")
    List<Vaga> getAll();

    @Query("SELECT * FROM Vaga WHERE vagaId IN (:vagaId)")
    List<Vaga> loadAllbyIds(int[] vagaId);

    @Query("SELECT * FROM Vaga WHERE descricao LIKE :descricao LIMIT 1")
    Vaga findByDescricao(String descricao);

    @Query("UPDATE Vaga SET descricao =:descricao WHERE vagaId == :vagaId")
    void updateDescricao(String descricao, int vagaId);

    @Query("UPDATE Vaga SET horas =:horas WHERE vagaId == :vagaId")
    void updateHoras(int horas, int vagaId);

    @Query("UPDATE Vaga SET valor =:valor WHERE vagaId == :vagaId")
    void updateValor(double valor, int vagaId);

    @Insert
    void insertAll (Vaga ... vaga);

    @Update
    void update(Vaga vaga);

    @Delete
    void delete(Vaga vaga);
}
