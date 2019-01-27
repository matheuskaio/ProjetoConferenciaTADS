package model;

import java.io.Serializable;
import java.util.List;

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
}
