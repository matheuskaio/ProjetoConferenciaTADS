package model;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import model.request.RequestCaminhao;
import model.request.RequestCarga;
import model.request.RequestExpedicao;
import model.request.RequestFuncionario;

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

    public List<Motorista> listarMotoristas(){
        String str = new RequestFuncionario().select('M');
        if(str.length()<5){
            return new ArrayList<>();
        }
        return new Gson().fromJson(str,new TypeToken<List<Motorista>>(){}.getType());
    }

    public List<Conferente> listarConferente(){
        String str = new RequestFuncionario().select('C');
        if(str.length()<5){
            return new ArrayList<>();
        }
        return new Gson().fromJson(str,new TypeToken<List<Conferente>>(){}.getType());
    }

    public List<Expedicao> listarExpedicoes(){
        String str = new RequestExpedicao().select();
        if(str.length()<5){
            return new ArrayList<>();
        }
        return new Gson().fromJson(str,new TypeToken<List<Expedicao>>(){}.getType());
    }

    public List<Caminhao> listarCaminhoes(){
        String str = new RequestCaminhao().select();
        if(str.length()<5){
            return new ArrayList<>();
        }
        return new Gson().fromJson(str,new TypeToken<List<Caminhao>>(){}.getType());
    }
}
