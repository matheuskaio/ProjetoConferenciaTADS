package model;

import android.util.Log;

import com.google.gson.Gson;

import request.RequestFuncionario;

public class Gerente extends Funcionario {

    public Gerente(String cpf, String senha) {
        super(cpf, senha);
    }

    public void gerarRelatorio(){

    }


    @Override
    public String autenticado() {

        String r,str =new RequestFuncionario().get(this.getCpf());
        if(str.length()<25){
            r = "O cpf informado não foi encontrado";
        }else{
            Log.e("Retorno",str);
            Gerente gerente = new Gson().fromJson(str,Gerente.class);
            if(gerente.getSenha().equals(this.getSenha())){
                return null;
            }
            r = "Senha incorreta";
        }
        return r;
    }
}
