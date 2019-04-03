package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Carga implements Serializable {
    private int expedicao;
    private Conferente conferente;
    private List<Lote> lotes;
    private Caminhao caminhao;
    private String data;

    public Carga(Conferente conferente){
        this.lotes = new ArrayList<>();
        this.conferente = conferente;
    }

    public int getExpedicao() {
        return expedicao;
    }

    public void setExpedicao(int expedicao) {
        this.expedicao = expedicao;
    }

    public Conferente getConferente() {
        return conferente;
    }

    public void setConferente(Conferente conferente) {
        this.conferente = conferente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Caminhao getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(Caminhao caminhao) {
        this.caminhao = caminhao;
    }

    public void addLote(Lote lote){
        this.lotes.add(lote);
    }

    public void removeLote(Lote lote){
        this.lotes.remove(lote);
    }


    public List<Lote> getLotes() {
        return lotes;
    }

    public void setLotes(List<Lote> lotes) {
        this.lotes = lotes;
    }

    @Override
    public String toString() {
        return "Nº Conferencia: "+ expedicao;
    }
}
