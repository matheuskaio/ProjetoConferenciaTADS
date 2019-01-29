package model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import model.request.RequestCarga;
import model.request.RequestFuncionario;
import model.request.RequestLote;

public class Gerente extends Funcionario {

    public Gerente(String cpf, String senha) {
        super(cpf, senha);
    }


    public Gerente(String nome, String cpf, String senha, char perfil) {
        super(nome, cpf, senha, perfil);
    }

    public Gerente(String nome, String cpf, String senha, String foto, char perfil) {
        super(nome, cpf, senha, foto, perfil);
    }

    public void gerarRelatorio(){

    }
    public void cadastrarFuncionario(Funcionario funcionario){
        new RequestFuncionario().insert(funcionario);
    }


    public List<Carga> allCargas() {
        String str= new RequestCarga().selecte();
        if(str.length()<5){
            return new ArrayList<Carga>();
        }
        List<Carga> cargas = new Gson().fromJson(str,new TypeToken<List<Carga>>(){}.getType());
        return  cargas;
    }
}
