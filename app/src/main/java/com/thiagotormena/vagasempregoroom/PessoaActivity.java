package com.thiagotormena.vagasempregoroom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

public class PessoaActivity extends AppCompatActivity {
    DB db;
    private EditText edtNome, edtCpf, edtEmail, edtTelefone;
    private Button btnSalvarPessoa, btnExcluirPessoa;
    private int dbPessoaId;
    private Pessoa dbPessoa;
    private Spinner spinnerVagas;
    private List<Vaga> vagas;
    ArrayAdapter<Vaga> vagasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);

        db = DB.getDatabase(getApplicationContext());
        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtCpf = findViewById(R.id.edtCpf);
        edtTelefone = findViewById(R.id.edtTelefone);
        btnExcluirPessoa = findViewById(R.id.btnExcluirPessoa);
        spinnerVagas = findViewById(R.id.spinnerVagas);
        dbPessoaId = getIntent().getIntExtra("ch_pessoaSelecionada", -1);
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(dbPessoaId >= 0){
            preenchePessoa();
        } else {
            btnExcluirPessoa.setVisibility(View.GONE);
        }
        preencheVagas();
    }
    private void preenchePessoa(){
        dbPessoa = db.pessoaModel().get(dbPessoaId);
        edtNome.setText(dbPessoa.getNome());
        edtCpf.setText(dbPessoa.getCpf());
        edtEmail.setText(dbPessoa.getEmail());
        edtTelefone.setText(dbPessoa.getTelefone());
    }
    private void preencheVagas(){
        vagas = db.vagaModel().getAll();
        vagasAdapter = new ArrayAdapter<Vaga>(this,
                android.R.layout.simple_expandable_list_item_1, vagas);
        spinnerVagas.setAdapter(vagasAdapter);
        if(dbPessoa != null){
            spinnerVagas.setSelection(dbPessoa.getVagaId() -1);
        }
    }
    public void salvarPessoa(View view){
        String nome = edtNome.getText().toString();
        String cpf = edtCpf.getText().toString();
        String email = edtEmail.getText().toString();
        String telefone = edtTelefone.getText().toString();
        String novaVaga = "";
        if(spinnerVagas.getSelectedItem() != null){
            novaVaga = spinnerVagas.getSelectedItem().toString();
        }
        if(nome.equals("")){
            Toast.makeText(this, "Nome é obrigatório",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if(novaVaga.equals("")){
            Toast.makeText(this, "Escolha uma vaga",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Pessoa novaPessoa = new Pessoa();
        novaPessoa.setNome(nome);
        novaPessoa.setCpf(cpf);
        novaPessoa.setEmail(email);
        novaPessoa.setTelefone(telefone);
        novaPessoa.setVagaId(vagas.get(spinnerVagas.getSelectedItemPosition()).vagaId);

        if(dbPessoa != null){
            novaPessoa.setVagaId(dbPessoaId);
            db.pessoaModel().updateNome(novaPessoa.getNome(), dbPessoaId);
            db.pessoaModel().updateCpf(novaPessoa.getCpf(), dbPessoaId);
            db.pessoaModel().updateEmail(novaPessoa.getEmail(), dbPessoaId);
            db.pessoaModel().updateTelefone(novaPessoa.getTelefone(), dbPessoaId);
            Toast.makeText(this,"Pessoa atualizada com sucesso",
                    Toast.LENGTH_SHORT);
        } else {
            db.pessoaModel().insertAll(novaPessoa);
            Toast.makeText(this,"Pessoa cadastrada com sucesso",
                    Toast.LENGTH_SHORT);
        }
        finish();
    }
    public void excluirPessoa(View view){
        new AlertDialog.Builder(this)
                .setTitle("Exclusão de Pessoa")
                .setMessage("Deseja excluir essa pessoa?")
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
            db.pessoaModel().delete(dbPessoa);
            Toast.makeText(this,"Pessoa excluída com sucesso",
                    Toast.LENGTH_SHORT).show();
        } catch (SQLiteConstraintException e){
            Toast.makeText(this,"Não foi possível excluir a pessoa",
                    Toast.LENGTH_SHORT).show();
        }
        finish();
    }
    public void voltarPA(View view){ finish(); }
}