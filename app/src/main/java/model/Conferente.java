package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import model.request.RequestCarga;
import model.request.RequestExpedicao;
import model.request.RequestFuncionario;
import model.request.RequestCaminhao;

public class Conferente extends Funcionario {

    private Carga carga;

    public Conferente(String cpf, String senha) {
        super(cpf, senha);
    }

    public Conferente(String nome, String cpf, String senha, char perfil) {
        super(nome, cpf, senha, perfil);
    }

    public Conferente(String nome, String cpf, String senha, String foto, char perfil) {
        super(nome, cpf, senha, foto, perfil);
    }

    public Carga getCarga() {
        return carga;
    }

    public void setCarga(Carga carga) {
        this.carga = carga;
    }

    public void iniciarConferencia(){
        if(carga==null) {
            carga = new Carga(this);
        }
    }

    public void addLote(Lote lote){
        carga.addLote(lote);
    }

    public void finalizarConferencia(String motorista){
        new RequestCarga().insert(carga,motorista); carga = null;
    }

    public List<Carga> myCargas(){
        String str = new RequestCarga().selecte(this);
        if(str.length()<5){
            return new ArrayList<Carga>();
        }
        return new Gson().fromJson(str,new TypeToken<List<Carga>>(){}.getType());

    }


    public void cadastraExpedicao(Expedicao expedicao){
        new RequestExpedicao().insert(expedicao);
    }

    public List<Expedicao> listarExpedicaos(){
        String str = new RequestExpedicao().select();
        if(str.length()<5){
            return new ArrayList<>();
        }
        return new Gson().fromJson(str,new TypeToken<List<Expedicao>>(){}.getType());
    }

    public void cadastrarCaminhao(Caminhao caminhao){
        new RequestCaminhao().insert(caminhao);
    }

    public List<Caminhao> listarCaminhoes(){
        String str = new RequestCaminhao().select();
        if(str.length()<5){
            return new ArrayList<>();
        }
        return new Gson().fromJson(str,new TypeToken<List<Caminhao>>(){}.getType());
    }

    public List<Motorista> listarMotoristas(){
        String str = new RequestFuncionario().select('M');
        return new Gson().fromJson(str,new TypeToken<List<Motorista>>(){}.getType());
    }





}
