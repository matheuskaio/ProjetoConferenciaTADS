package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.request.RequestCarga;

public class Expedicao implements Serializable {
    private List<String> cidades;
    private String id;
    private String nome;

    public Expedicao(List<String> cidades, String nome) {
        this.cidades = cidades;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void add(String cidade){
        this.cidades.add(cidade);
    }

    public void remove(String cidade){
        this.cidades.remove(cidade);
    }

    public List<String> getCidades() {
        return cidades;
    }

    public void setCidades(List<String> cidades) {
        this.cidades = cidades;
    }

    public int quantidadeDeCidades(){
        return cidades.size();
    }

    public List<Carga> cargas(){
        String str = new RequestCarga().selecte(this);
        if(str.length()<25){
            return new ArrayList<>();
        }
        return new Gson().fromJson(str,new TypeToken<List<Carga>>(){}.getType());
    }
    @Override
    public String toString() {
        return nome;
    }
}
