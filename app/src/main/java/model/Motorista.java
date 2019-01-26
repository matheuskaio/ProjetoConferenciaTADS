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
    public String autenticado() {

        String r,str =new RequestFuncionario().get(this.getCpf());
        if(str.length()<25){
            r = "O cpf informado não foi encontrado";
        }else{
            Log.e("Retorno",str);
            Conferente conferente = new Gson().fromJson(str,Conferente.class);
            if(conferente.getSenha().equals(this.getSenha())){
                return null;
            }
            r = "Senha incorreta";
        }
        return r;
    }
}
