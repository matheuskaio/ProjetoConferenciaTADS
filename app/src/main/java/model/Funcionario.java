package model;

import android.util.Log;

import com.google.gson.Gson;

import java.io.Serializable;

import request.RequestFuncionario;

public abstract class Funcionario implements Serializable{

    private String nome,cpf,senha,foto;
    private Perfil perfil;


    public Funcionario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Funcionario(String nome, String cpf, String senha, Perfil perfil) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Funcionario(String nome, String cpf, String senha, String foto, Perfil perfil) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.foto = foto;
        this.perfil = perfil;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
