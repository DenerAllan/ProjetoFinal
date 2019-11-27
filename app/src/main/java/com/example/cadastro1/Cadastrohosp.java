package com.example.cadastro1;

import androidx.annotation.NonNull;

public class Cadastrohosp {

    String cnpj;
    String nomehosp;
    String emailhosp;

    public Cadastrohosp() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomehosp() {
        return nomehosp;
    }

    public void setNomehosp(String nomehosp) {
        this.nomehosp = nomehosp;
    }

    public String getEmailhosp() {
        return emailhosp;
    }

    public void setEmailhosp(String emailhosp) {
        this.emailhosp = emailhosp;
    }


    @NonNull
    @Override
    public String toString() {
        String texto = nomehosp.toString();
        return texto ;
    }
}
