package com.example.cadastro1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TelaInicial1 extends AppCompatActivity {

    ListView listaCadastro;
    ArrayList<Cadastro1> Listcadastros;
    ArrayAdapter<Cadastro1> adp;
    ListView listaHospitais;
    ArrayList<Cadastrohosp> ListHosp;
    ArrayAdapter<Cadastrohosp> adph;
    Cadastro1 cadastro;
    CadastroDAO DAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial1);
        listaCadastro = findViewById(R.id.ListViewCadastro);
        listaHospitais = findViewById(R.id.ListViewHosp);
        registerForContextMenu(listaCadastro);

        carregarLista();
        carregarListahosp();

        listaCadastro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cadastro1 cadastroenviado = (Cadastro1) adp.getItem(position);

                Intent i = new Intent(TelaInicial1.this, Cadastro.class);
                i.putExtra("Pessoa-Enviada", cadastroenviado);
                startActivity(i);
            }
        });

        listaCadastro.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                cadastro = adp.getItem(position);
                return false;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutelaincial, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case R.id.menuFecharApp: {
                finish();
                break;
            }

            case R.id.menuCadastrarHosp: {
                Intent h = new Intent(this, CadastrohospTela.class);
                startActivity(h);
                break;
            }

        }

        return super.onOptionsItemSelected(item);
    }
    private void carregarLista() {

        Listcadastros = CadastroDAO.getInstance(this).listar();


        if (listaCadastro != null){
            adp = new ArrayAdapter<Cadastro1>(TelaInicial1.this, android.R.layout.simple_list_item_1, Listcadastros);

            listaCadastro.setAdapter(adp);
        }

    }

    private void carregarListahosp() {

        ListHosp = CadastroDAO.getInstance(this).listarhosp();

        if (listaHospitais != null){
            adph = new ArrayAdapter<Cadastrohosp>(TelaInicial1.this, android.R.layout.simple_list_item_1, ListHosp);

            listaHospitais.setAdapter(adph);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
        carregarListahosp();

    }

    public void exclui(){
        long retornoDB;
        DAO = new CadastroDAO();
        retornoDB = CadastroDAO.getInstance(this).excluir(cadastro);

        if (retornoDB == -1) {
            alert("Erro em cadastrar");
        } else {
            alert("Excluido com sucesso");
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem delete = menu.add("Deletar Registro");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
              exclui();
                carregarLista();
                return false;
            }
        });
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    private void alert(String s){
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
