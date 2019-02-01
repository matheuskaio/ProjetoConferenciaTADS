package model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.request.RequestCarga;

public class Caminhao implements Serializable {
    private String modelo;
    private String placa;

    public Caminhao(String modelo, String placa) {
        this.modelo = modelo;
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public List<Carga> cargas(){
        String str = new RequestCarga().selecte(this);
        Log.e("str",str);
        if(str.length()<25){
            return new ArrayList<>();
        }
        return new Gson().fromJson(str,new TypeToken<List<Carga>>(){}.getType());
    }

    @Override
    public String toString() {
        return placa;
    }
}
