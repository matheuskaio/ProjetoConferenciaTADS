package model;

import java.io.Serializable;

public abstract class Funcionario implements Serializable{

    private String nome,cpf,senha,foto;
    private char perfil;


    public Funcionario(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
    }

    public Funcionario(String nome, String cpf, String senha, char perfil) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Funcionario(String nome, String cpf, String senha, String foto, char perfil) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.foto = foto;
        this.perfil = perfil;
    }

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

    public char getPerfil() {
        return perfil;
    }

    public void setPerfil(char perfil) {
        this.perfil = perfil;
    }
}
