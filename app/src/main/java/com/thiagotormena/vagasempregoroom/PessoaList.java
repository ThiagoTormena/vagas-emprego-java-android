package com.thiagotormena.vagasempregoroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class PessoaList extends AppCompatActivity {
    DB db;
    List<Pessoa> pessoas;
    ListView listViewPessoas;
    Intent edtIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_list);

        db = DB.getDatabase(getApplicationContext());
        listViewPessoas = findViewById(R.id.listPessoas);
    }

    @Override
    protected void onResume() {
        super.onResume();
        edtIntent = new Intent(this, PessoaActivity.class);
        preenchePessoas();
    }
    private void preenchePessoas(){
        pessoas = db.pessoaModel().getAll();
        ArrayAdapter<Pessoa> pessoaArrayAdapter = new ArrayAdapter<Pessoa>
                (this, android.R.layout.simple_expandable_list_item_1, pessoas);
        listViewPessoas.setAdapter(pessoaArrayAdapter);
        listViewPessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pessoa pessoaSelecionada = pessoas.get(position);
                edtIntent.putExtra("ch_pessoaSelecionada", pessoaSelecionada.pessoaId);
                startActivity(edtIntent);
            }
        });
    }
    public void cadastrarPessoa(View view) { startActivity(edtIntent); }
    public void voltar(View view){ finish(); }
}