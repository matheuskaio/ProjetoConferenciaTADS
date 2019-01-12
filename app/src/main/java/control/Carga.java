package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carga implements Serializable {
    private String id;
    private Conferente conferente;
    private Expedicao expedicao;
    private List<Lote> lotes;

    public Carga(Conferente conferente){
        this.id = "id1";
        this.lotes = new ArrayList<>();
        this.conferente = conferente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
