package com.example.cadastro1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;


import java.lang.reflect.Array;
import java.util.ArrayList;


public class CadastroDAO {

    private static final String TABELA = "cadastros";
    private static final String TABELA1 = "hospitais";

    public SQLiteDatabase db;

    private static CadastroDAO instancia = new CadastroDAO();


    public static CadastroDAO getInstance(Context contexto) {
        if((instancia.db == null) || (!instancia.db.isOpen())) {
            instancia.db = new DB(contexto).getWritableDatabase();
        }
        return instancia;
    }


    public long excluir(Cadastro1 c) {

        long retornoDB;
        String[] args = new String[]{c.getCpf()};
        retornoDB = db.delete(TABELA, "cpf=?", args);
        return retornoDB;
    }

   public ArrayList<Cadastro1> listar() {
        String[] coluns = {"cpf", "nome", "rg", "endereco", "numero", "cep", "bairro", "cidade", "uf", "ddd", "telefone", "email", "senha"};
        Cursor c = db.query(TABELA, coluns, null, null, null, null, null, null);
        ArrayList<Cadastro1> ListCadastro = new ArrayList<Cadastro1>();
        while (c.moveToNext()){
            Cadastro1 ca = new Cadastro1();

            ca.setCpf(c.getString(0));
            ca.setNome(c.getString(1));
            ca.setRg(c.getString(2));
            ca.setEnd(c.getString(3));
            ca.setNumero1(c.getInt(4));
            ca.setCep(c.getInt(5));
            ca.setBairro(c.getString(6));
            ca.setCidade(c.getString(7));
            ca.setUf(c.getString(8));
            ca.setDdd(c.getInt(9));
            ca.setTelefone(c.getInt(10));
            ca.setEmail(c.getString(11));
            ca.setSenha(c.getString(12));

            ListCadastro.add(ca);

        }
        return ListCadastro;
    }

    public Cadastro1 validarLogin(String email, String senha) {

        String[] selectionArgs = new String[]{email, senha};
        Cursor cursor = db.rawQuery("SELECT * FROM cadastros WHERE email=? AND senha=?", selectionArgs);
        Cadastro1 acesso = null;
        while (cursor.moveToNext()) {
            acesso = new Cadastro1();
            acesso.setNome(cursor.getString(cursor.getColumnIndex("email")));
            acesso.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
        }cursor.close();
        return acesso;
    }



    public long salvar(Cadastro1 c) {
        ContentValues values = new ContentValues();
        long retornoDB;

        values.put("cpf",c.getCpf());
            values.put("nome", c.getNome());
            values.put("rg", c.getRg());
            values.put("endereco", c.getEnd());
            values.put("numero", c.getNumero1());
            values.put("cep", c.getCep());
            values.put("bairro", c.getBairro());
            values.put("cidade", c.getCidade());
            values.put("uf", c.getUf());
            values.put("ddd", c.getDdd());
            values.put("telefone", c.getTelefone());
            values.put("email", c.getEmail());
            values.put("senha", c.getSenha());

            retornoDB = db.insert(TABELA, null, values);
        return retornoDB;
    }

    public long alterar(Cadastro1 c) {
        ContentValues values = new ContentValues();
        long retornoDB;

        values.put("cpf",c.getCpf());
        values.put("nome", c.getNome());
        values.put("rg", c.getRg());
        values.put("endereco", c.getEnd());
        values.put("numero", c.getNumero1());
        values.put("cep", c.getCep());
        values.put("bairro", c.getBairro());
        values.put("cidade", c.getCidade());
        values.put("uf", c.getUf());
        values.put("ddd", c.getDdd());
        values.put("telefone", c.getTelefone());
        values.put("email", c.getEmail());
        values.put("senha", c.getSenha());

        String[] args = new String[]{c.getCpf()};
        retornoDB = db.update(TABELA, values, "cpf=?", args);
        return retornoDB;
    }

    public long salvarhosp(Cadastrohosp h) {
        ContentValues values = new ContentValues();
        long retornoDB1;

        values.put("cnpj", h.getCnpj());
        values.put("nomehosp", h.getNomehosp());
        values.put("emailhosp", h.getEmailhosp());


        retornoDB1 = db.insert(TABELA1, null, values);
        return retornoDB1;
    }

    public ArrayList<Cadastrohosp> listarhosp() {
        String[] coluns = {"cnpj", "nomehosp", "emailhosp"};
        Cursor c = db.query(TABELA1, coluns, null, null, null, null, null, null);
        ArrayList<Cadastrohosp> ListCadastrohosp = new ArrayList<Cadastrohosp>();
        while (c.moveToNext()){
            Cadastrohosp ch = new Cadastrohosp();

            ch.setCnpj(c.getString(0));
            ch.setNomehosp(c.getString(1));
            ch.setEmailhosp(c.getString(2));


            ListCadastrohosp.add(ch);

        }
        return ListCadastrohosp;
    }

    public ArrayList<Cadastrohosp> spnHosp() {
        String[] coluns = { "nomehosp"};
        Cursor c = db.query(TABELA1, coluns, null, null, null, null, null, null);
        ArrayList<Cadastrohosp> ListCadastrohosp = new ArrayList<Cadastrohosp>();
        while (c.moveToNext()){
            Cadastrohosp ch = new Cadastrohosp();

            ch.setNomehosp(c.getString(0));



            ListCadastrohosp.add(ch);

        }
        return ListCadastrohosp;
    }



}
