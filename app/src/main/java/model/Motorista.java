package model;


import java.util.ArrayList;
import java.util.List;

import model.request.RequestCarga;

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
        List<Carga> cargas = new RequestCarga().selecte(this);
        if(cargas != null){
            return cargas;
        }
        return new ArrayList<>();
    }

}
