package com.thiagotormena.vagasempregoroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VagaActivity extends AppCompatActivity {
    DB db;
    private EditText edtDescricao, edtHoras, edtValor;
    private Button btnSalvarVaga, btnExcluirVaga;
    private int dbVagaId;
    private Vaga dbVaga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaga);

        db = DB.getDatabase(getApplicationContext());
        edtDescricao = findViewById(R.id.edtNome);
        edtHoras = findViewById(R.id.edtHoras);
        edtValor = findViewById(R.id.edtValor);
        btnSalvarVaga = findViewById(R.id.btnSalvarVaga);
        btnExcluirVaga = findViewById(R.id.btnExcluirVaga);
        dbVagaId = getIntent().getIntExtra("ch_vagaSelecionada",
                -1);
    }

    protected  void onResume(){
        super.onResume();
        if(dbVagaId >= 0){
            getDBVaga();
        } else {
            btnExcluirVaga.setVisibility(View.GONE);
        }
    }
    private void getDBVaga(){
        dbVaga = db.vagaModel().get(dbVagaId);
        edtDescricao.setText(dbVaga.getDescricao());
        edtHoras.setText(Integer.toString(dbVaga.getHoras()));
        edtValor.setText(Double.toString(dbVaga.getValor()));
    }
    public void salvarVaga(View view){
        String descricaoVaga = edtDescricao.getText().toString();
        int horasVaga = Integer.parseInt(edtHoras.getText().toString());
        double valorVaga = Double.parseDouble(edtValor.getText().toString());

        if(descricaoVaga.equals("")){
            Toast.makeText(this,"É necessário descrever a vaga", Toast.LENGTH_SHORT);
            return;
        }
        Vaga thisVaga = new Vaga();
        thisVaga.setDescricao(descricaoVaga);
        thisVaga.setHoras(horasVaga);
        thisVaga.setValor(valorVaga);
        if(dbVaga != null){
            thisVaga.setVagaId(dbVagaId);
            db.vagaModel().update(thisVaga);
            Toast.makeText(this,"Vaga atualizada com sucesso",
                    Toast.LENGTH_SHORT).show();
        } else {
            db.vagaModel().insertAll(thisVaga);
            Toast.makeText(this,"Vaga cadastrada com sucesso",
                    Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    public void excluirVaga(View view){
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Vaga")
                .setMessage("Deseja excluir essa vaga?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        excluir();
                    }
                })
                .setNegativeButton("não", null)
                .show();
    }
    public void excluir(){
        try {
            db.vagaModel().delete(dbVaga);
            Toast.makeText(this,"Vaga excluída com sucesso",
                    Toast.LENGTH_SHORT).show();
        } catch (SQLiteConstraintException e){
            Toast.makeText(this,"Não foi possível excluir a vaga",
                    Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    public void voltar(View view){
        finish();
    }
}