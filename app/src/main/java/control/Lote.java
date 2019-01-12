package control;

import java.io.Serializable;
import java.util.Random;

public class Lote implements Serializable {

    private int altura,lastro;
    private String id,nomeProduto,observacao,fotoAltura,fotoLastro;

    public Lote(int altura, int lastro, String nomeProduto, String observacao) {
        this.altura = altura;
        this.lastro = lastro;
        this.nomeProduto = nomeProduto;
        this.observacao = observacao;
        this.id = new Random().nextInt()+"";
        this.fotoAltura = "altura"+this.id+".png";
        this.fotoLastro = "lastro"+this.id+".png";
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
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getFotoAltura() {
        return fotoAltura;
    }

    public void setFotoAltura(String fotoAltura) {
        this.fotoAltura = fotoAltura;
    }

    public String getFotoLastro() {
        return fotoLastro;
    }

    public void setFotoLastro(String fotoLastro) {
        this.fotoLastro = fotoLastro;
    }
    public String getDimensao(){
        return altura + " de altura por " + lastro + " de lastro";
    }

    @Override
    public String toString() {
        return nomeProduto +
                "(" + altura +
            "/" + lastro +
                ')';
    }
}
