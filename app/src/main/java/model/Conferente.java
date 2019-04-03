package model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import model.exceptions.ConexaoException;
import model.request.RequestCarga;
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

    public void finalizarConferencia(int expedicao,String motorista){
        carga.setExpedicao(expedicao);
        new RequestCarga().insert(carga,motorista); carga = null;
    }

    public List<Carga> myCargas() throws ConexaoException {
        String str = new RequestCarga().selecte(this);
        try {
            if(str.length()<5){
                return new ArrayList<Carga>();
            }
        }catch (NullPointerException e){
            throw new ConexaoException();
        }
        return new Gson().fromJson(str,new TypeToken<List<Carga>>(){}.getType());

    }

    public void cadastrarCaminhao(Caminhao caminhao){
        new RequestCaminhao().insert(caminhao);
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

    public List<Motorista> listarMotoristas() throws ConexaoException {
        String str = new RequestFuncionario().select('M');
        try {

            if(str.length()<5){
                return new ArrayList<Motorista>();
            }
        }catch (NullPointerException e){
            throw new ConexaoException();
        }
        return new Gson().fromJson(str,new TypeToken<List<Motorista>>(){}.getType());
    }





}
