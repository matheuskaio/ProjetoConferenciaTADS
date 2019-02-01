package model;

import java.io.Serializable;
import java.util.Random;

import model.exceptions.EmptyFieldException;

public class Lote implements Serializable {

    private int altura,lastro;
    private String id,produto,observacao,foto_altura,foto_lastro;

    public Lote(int altura, int lastro, String nomeProduto, String observacao,String fotoAltura,String fotoLastro) throws EmptyFieldException {
        this.altura = altura;
        this.lastro = lastro;
        this.produto = nomeProduto;
        this.observacao = observacao;
        this.id = new Random().nextInt()+"";
        this.foto_altura = fotoAltura;
        this.foto_lastro = fotoLastro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLastro() {
        return lastro;
    }

    public void setLastro(int lastro) {
        this.lastro = lastro;
    }

    public String getNomeProduto() {
        return produto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.produto = nomeProduto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getFotoAltura() {
        return foto_altura;
    }

    public void setFotoAltura(String fotoAltura) {
        this.foto_altura = fotoAltura;
    }

    public String getFotoLastro() {
        return foto_lastro;
    }

    public void setFotoLastro(String fotoLastro) {
        this.foto_lastro = fotoLastro;
    }
    public String getDimensao(){
        return altura + " de altura por " + lastro + " de lastro";
    }

    @Override
    public String toString() {
        return produto +
                "(" + altura +
            "/" + lastro +
                ')';
    }
}
