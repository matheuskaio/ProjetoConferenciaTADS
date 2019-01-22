package model;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import request.RequestCarga;
import request.RequestFuncionario;
import request.RequestLote;

public class Conferente extends Funcionario {

    private Carga carga;

    public Conferente(String cpf, String senha) {
        super(cpf, senha);
    }

    public Carga getCarga() {
        return carga;
    }

    public void setCarga(Carga carga) {
        this.carga = carga;
    }

    public void iniciarCoferencia(){
        if(carga==null) {
            carga = new Carga(this);
        }
    }

    public void addLote(Lote lote){
        carga.addLote(lote);
    }

    public void finalizarConferencia(){
        new RequestCarga().insert(carga); carga = null;
    }

    public List<Carga> myCargas(){
        return new RequestCarga().selecte(this);
    }

    public List<Carga> allCargas() {
        String str= new RequestCarga().selecte();
        if(str.length()<5){
            return new ArrayList<Carga>();
        }
        List<Carga> cargas = new Gson().fromJson(str,new TypeToken<List<Carga>>(){}.getType());
        for (Carga c:cargas){
            c.setLotes((List<Lote>) new Gson().fromJson(new RequestLote().selectFromCarga(c.getId()+""),new TypeToken<List<Lote>>(){}.getType()));
        }
        return  cargas;
    }


    @Override
    public String autenticado() {

        String r,str =new RequestFuncionario().get(this.getCpf())+"";
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
