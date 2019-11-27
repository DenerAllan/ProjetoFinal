package com.example.cadastro1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button entrar;
    EditText login;
    EditText senha;
    Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.edtlogin);
        senha = findViewById(R.id.edtsenha);
        cadastrar = findViewById(R.id.btnCadastrar);
        cadastrar.setOnClickListener(this);
        entrar = findViewById(R.id.btnacessar);
        entrar.setOnClickListener(this);




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

        String nome1 = login.getText().toString().trim();
        String senha1 = senha.getText().toString().trim();
        Cadastro1 c = CadastroDAO.getInstance(this).validarLogin(nome1, senha1);


        switch(v.getId()) {
            case R.id.btnacessar: {
                if (c != null){
                    finish();
                    Intent i = new Intent(this, TelaInicial1.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Acesso Negado", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.btnCadastrar: {

                Intent j = new Intent(this, Cadastro.class);
                startActivity(j);
                break;
            }
        }
    }
}
