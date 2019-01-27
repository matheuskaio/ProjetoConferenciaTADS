package model;

import android.util.Log;

import com.google.gson.Gson;

import request.RequestFuncionario;

public class Gerente extends Funcionario {

    public Gerente(String cpf, String senha) {
        super(cpf, senha);
    }


    public Gerente(String nome, String cpf, String senha, Perfil perfil) {
        super(nome, cpf, senha, perfil);
    }

    public Gerente(String nome, String cpf, String senha, String foto, Perfil perfil) {
        super(nome, cpf, senha, foto, perfil);
    }

    public void gerarRelatorio(){

    }
    public void cadastrarFuncionario(Funcionario funcionario){
        new RequestFuncionario().insert(new Gson().toJson(funcionario));
    }

    @Override
    public Gerente autenticado() {
        Gerente gerente;
        String str =new RequestFuncionario().get(this.getCpf());
        if(str.length()<25){
            gerente = null;
        }else{
            Log.e("Retorno",str);
            gerente = new Gson().fromJson(str,Gerente.class);
            if(gerente.getSenha().equals(this.getSenha())){
                return gerente;
            }
            gerente = this;
        }
        return gerente;
    }
}
