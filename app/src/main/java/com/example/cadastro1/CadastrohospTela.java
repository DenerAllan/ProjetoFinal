package com.example.cadastro1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrohospTela extends AppCompatActivity implements View.OnClickListener {

    Cadastrohosp cadastrohosp, altcadastrohosp;
    long retorno;
    EditText cnpj;
    EditText nomehosp;
    EditText emailhosp;
    Button gravarhosp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrohosp_tela);

        Intent i = getIntent();
        altcadastrohosp = (Cadastrohosp) i.getSerializableExtra("Hospital-Enviado");
        cadastrohosp = new Cadastrohosp();



        cnpj = findViewById(R.id.edtcnpj);
        nomehosp = findViewById(R.id.edtnomehosp);
        emailhosp = findViewById(R.id.edtemailhosp);

        gravarhosp = findViewById(R.id.btngravarhosp);
        gravarhosp.setOnClickListener(this);
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

        cadastrohosp.setCnpj(cnpj.getText().toString());
        cadastrohosp.setNomehosp(nomehosp.getText().toString());
        cadastrohosp.setEmailhosp(emailhosp.getText().toString());


        retorno = CadastroDAO.getInstance(this).salvarhosp(cadastrohosp);
        if (retorno == -1){
            alert("Erro em cadastrar");
        }else{
            alert("Hospital cadastrado");
        }
        finish();
        Intent i = new Intent(this, TelaInicial1.class);
        startActivity(i);

    }
    private void alert (String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
