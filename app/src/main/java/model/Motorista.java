package model;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import model.request.RequestCarga;
import model.request.RequestFuncionario;

public class Motorista extends Funcionario {
    public Motorista(String cpf, String senha) {
        super(cpf, senha);
    }

    public Motorista(String nome, String cpf, String senha, char perfil) {
        super(nome, cpf, senha, perfil);
    }

    public Motorista(String nome, String cpf, String senha, String foto, char perfil) {
        super(nome, cpf, senha, foto, perfil);
    }

    public List<Carga> myCargas(){
        return new RequestCarga().selecte(this);
    }

}
