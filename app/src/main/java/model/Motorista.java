package model;

import android.util.Log;

import com.google.gson.Gson;

import request.RequestFuncionario;

public class Motorista extends Funcionario {
    public Motorista(String cpf, String senha) {
        super(cpf, senha);
    }

    public Motorista(String nome, String cpf, String senha, Perfil perfil) {
        super(nome, cpf, senha, perfil);
    }

    public Motorista(String nome, String cpf, String senha, String foto, Perfil perfil) {
        super(nome, cpf, senha, foto, perfil);
    }

    public void verResumoDaCarga(){

    }


    @Override
    public Motorista autenticado() {
        Motorista motorista;
        String str =new RequestFuncionario().get(this.getCpf());
        if(str.length()<25){
            motorista = null;
        }else{
            Log.e("Retorno",str);
            motorista = new Gson().fromJson(str,Motorista.class);
            if(motorista.getSenha().equals(this.getSenha())){
                return motorista;
            }
            motorista = this;
        }
        return motorista;
    }
}
