package model;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import model.exceptions.ConexaoException;
import model.request.RequestCaminhao;
import model.request.RequestCarga;
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


    public List<Carga> allCargas() throws ConexaoException {
        String str= new RequestCarga().selecte();
        try {

            if(str.length()<5){
                return new ArrayList<Carga>();
            }
        }catch (NullPointerException e){
            throw new ConexaoException();
        }
        List<Carga> cargas = new Gson().fromJson(str,new TypeToken<List<Carga>>(){}.getType());
        return  cargas;
    }

    public List<Motorista> listarMotoristas() throws ConexaoException {
        String str = new RequestFuncionario().select('M');
        try {

            if(str.length()<5){
                return new ArrayList<>();
            }
        }catch (NullPointerException e){
            throw new ConexaoException();
        }
        return new Gson().fromJson(str,new TypeToken<List<Motorista>>(){}.getType());
    }

    public List<Conferente> listarConferente() throws ConexaoException {
        String str = new RequestFuncionario().select('C');
        try {

            if(str.length()<5){
                return new ArrayList<>();
            }
        }catch (NullPointerException e){
            throw new ConexaoException();
        }
        return new Gson().fromJson(str,new TypeToken<List<Conferente>>(){}.getType());
    }



    public List<Caminhao> listarCaminhoes() throws ConexaoException {
        String str = new RequestCaminhao().select();
        try {

            if(str.length()<5){
                return new ArrayList<>();
            }
        }catch (NullPointerException e){
            throw new ConexaoException();
        }
        return new Gson().fromJson(str,new TypeToken<List<Caminhao>>(){}.getType());
    }
}
