package model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import request.RequestCarga;
import request.RequestLote;

public class Carga implements Serializable {
    private int id;
    private Conferente conferente;
    private Expedicao expedicao;
    private List<Lote> lotes;

    public Carga(Conferente conferente){
        this.lotes = new ArrayList<>();
        this.conferente = conferente;
        this.id = gerarId();
    }

    private  int gerarId(){
        return (int) new Date().getTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Conferente getConferente() {
        return conferente;
    }

    public void setConferente(Conferente conferente) {
        this.conferente = conferente;
    }

    public void addLote(Lote lote){
        this.lotes.add(lote);
    }

    public void removeLote(Lote lote){
        this.lotes.remove(lote);
    }

    public Expedicao getExpedicao() {
        return expedicao;
    }

    public void setExpedicao(Expedicao expedicao) {
        this.expedicao = expedicao;
    }

    public List<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }

    @Override
    public String toString() {
        return "Nº Conferencia: "+ id;
    }
}
