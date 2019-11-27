package com.example.cadastro1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Cadastro extends AppCompatActivity implements View.OnClickListener {

    Cadastro1 cadastro, altcadastro;
    long retorno;
    EditText nome;
    EditText rg;
    EditText cpf;
    Spinner hospitais;
    EditText end;
    EditText numero1;
    EditText cep;
    EditText bairro;
    EditText ddd;
    EditText telefone;
    EditText cidade;
    EditText uf;
    EditText email;
    EditText senha;
    Button gravar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Intent i = getIntent();
        altcadastro = (Cadastro1) i.getSerializableExtra("Pessoa-Enviada");
        cadastro = new Cadastro1();

        hospitais = findViewById(R.id.spnHosp);
        spinner();
        nome = findViewById(R.id.edtNomeComp);
        cpf = findViewById(R.id.edtCPF);
        rg = findViewById(R.id.edtRg);
        end = findViewById(R.id.edtEnd);
        numero1 = findViewById(R.id.edtNumero);
        cep = findViewById(R.id.edtCEP);
        bairro = findViewById(R.id.edtBairro);
        ddd = findViewById(R.id.edtDDD);
        telefone = findViewById(R.id.edtTelefone);
        cidade = findViewById(R.id.edtCidade);
        uf = findViewById(R.id.edtUf);
        email = findViewById(R.id.edtEmail);
        senha = findViewById(R.id.edtSenha1);
        gravar = findViewById(R.id.btnGravar);


        if(altcadastro != null){
            gravar.setText("Alterar");
            cpf.setText(altcadastro.getCpf());
            nome.setText(altcadastro.getNome());
            rg.setText(altcadastro.getRg());
            end.setText(altcadastro.getEnd());
            numero1.setText(altcadastro.getNumero1()+ " ");
            cep.setText(altcadastro.getCep()+ " ");
            bairro.setText(altcadastro.getBairro());
            ddd.setText(altcadastro.getDdd()+ " ");
            telefone.setText(altcadastro.getTelefone()+ " ");
            cidade.setText(altcadastro.getCidade());
            uf.setText(altcadastro.getUf());
            email.setText(altcadastro.getEmail());
            senha.setText(altcadastro.getSenha());


        }else{
            gravar.setText("Gravar");
        }

        gravar.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menulogin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.menufechar: {
                finish();
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        cadastro.setCpf(cpf.getText().toString().trim());
        cadastro.setNome(nome.getText().toString().trim());
        cadastro.setRg(rg.getText().toString().trim());
        cadastro.setEnd(end.getText().toString().trim());
        cadastro.setNumero1(Integer.parseInt(numero1.getText().toString().trim()));
        cadastro.setCep(Integer.parseInt(cep.getText().toString().trim()));
        cadastro.setBairro(bairro.getText().toString().trim());
        cadastro.setCidade(cidade.getText().toString().trim());
        cadastro.setUf(uf.getText().toString().trim());
        cadastro.setDdd(Integer.parseInt(ddd.getText().toString().trim()));
        cadastro.setTelefone(Integer.parseInt(telefone.getText().toString().trim()));
        cadastro.setEmail(email.getText().toString().trim());
        cadastro.setSenha(senha.getText().toString().trim());

        alert("TESTEeeee");
        if(gravar.getText().toString().equals("Gravar")) {
            retorno = CadastroDAO.getInstance(this).salvar(cadastro);
            if (retorno == -1) {
                alert("Erro em cadastrar");
            } else {
                alert("Cadastrado");
            }
            finish();
            Intent i = new Intent(this, TelaInicial1.class);
            startActivity(i);

        }else{

            retorno = CadastroDAO.getInstance(this).alterar(cadastro);

            if (retorno == -1) {
                alert("Erro em cadastrar");
            } else {
                alert("Cadastro alterado com sucesso");
            }
            finish();
        }
    }
    private void alert (String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    public void spinner(){

        ArrayList<Cadastrohosp> hosp = CadastroDAO.getInstance(this).listarhosp();
        ArrayAdapter a = new ArrayAdapter(this, android.R.layout.simple_list_item_1, hosp);
        hospitais.setAdapter(a);
    }
    }
