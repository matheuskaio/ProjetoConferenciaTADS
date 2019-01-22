package model;

import android.util.Log;

import com.google.gson.Gson;

import java.io.Serializable;

import request.RequestFuncionario;

public abstract class Funcionario implements Serializable{

    private String nome,cpf,senha,observarcao,foto,tipo;

    public Funcionario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }


    public abstract String autenticado();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getObservarcao() {
        return observarcao;
    }

    public void setObservarcao(String observarcao) {
        this.observarcao = observarcao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
