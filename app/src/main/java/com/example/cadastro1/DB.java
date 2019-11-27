package com.example.cadastro1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

    private static final String BANCO = "Conexao.db";
    private static final int VERSION = 1;
    private static final String TABELA = "cadastros";
    private static final String TABELA1 = "hospitais";

    public DB(Context context) {

        super(context, BANCO, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE  TABLE " + TABELA + "("+
                        " cpf  VARCHAR(50) not null primary key, " +
                        " nome VARCHAR(50),"+
                        " rg VARCHAR(50)," +
                        " endereco VARCHAR(50)," +
                        " numero INTEGER ," +
                        " cep INTEGER ," +
                        " bairro VARCHAR(50)," +
                        " cidade VARCHAR(50)," +
                        " uf VARCHAR(50)," +
                        " ddd INTEGER ," +
                        " telefone INTEGER ," +
                        " email VARCHAR(50)," +
                        " senha VARCHAR(50));";

        String sqlhosp =
                "CREATE  TABLE " + TABELA1 + "("+
                        " cnpj VARCHAR(50) not null primary key, " +
                        " nomehosp VARCHAR(50),"+
                        " emailhosp VARCHAR(50));";

        db.execSQL(sql);
        db.execSQL(sqlhosp);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        String sqlh = "DROP TABLE IF EXISTS " + TABELA1;

        db.execSQL(sql);
        db.execSQL(sqlh);
            onCreate(db);

    }
}
