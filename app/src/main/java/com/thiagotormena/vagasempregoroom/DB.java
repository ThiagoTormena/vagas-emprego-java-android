package com.thiagotormena.vagasempregoroom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Pessoa.class, Vaga.class}, version = 1)
public abstract class DB extends RoomDatabase {
    private static DB INSTANCE;

    public static DB getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DB.class,
                    "EmpregoOnline").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public abstract VagaDAO vagaModel();

    public abstract PessoaDAO pessoaModel();
}
