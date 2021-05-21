package com.thiagotormena.vagasempregoroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class VagaList extends AppCompatActivity {
    DB db;
    List<Vaga> vagas;
    ListView listViewVagas;
    Intent edtIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaga_list);

        db = DB.getDatabase(getApplicationContext());
        listViewVagas = findViewById(R.id.listVagas);
    }
    @Override
    public void onResume(){
        super.onResume();
        edtIntent = new Intent(this, VagaActivity.class);
        preencheVagas();
    }
    public void preencheVagas(){
        vagas = db.vagaModel().getAll();
        ArrayAdapter<Vaga> vagaArrayAdapter = new ArrayAdapter<Vaga>(this,
                android.R.layout.simple_expandable_list_item_1, vagas);
        listViewVagas.setAdapter(vagaArrayAdapter);
        listViewVagas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Vaga vagaSelecionada = vagas.get(position);
                edtIntent.putExtra("ch_vagaSelecionada",
                        vagaSelecionada.vagaId);
                startActivity(edtIntent);
            }
        });
    }
    public void cadastrarVaga(View view) { startActivity(edtIntent); }
    public void voltar(View view){
        finish();
    }
}