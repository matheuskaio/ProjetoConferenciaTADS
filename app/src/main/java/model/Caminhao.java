package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.exceptions.ConexaoException;
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

    public List<Carga> cargas() throws ConexaoException {
        String str = new RequestCarga().selecte(this);
        try {
            if(str.length()<25){
                return new ArrayList<>();
            }
        }catch (NullPointerException e){
            throw new ConexaoException();
        }
        return new Gson().fromJson(str,new TypeToken<List<Carga>>(){}.getType());
    }

    @Override
    public String toString() {
        return placa;
    }
}
