package control;

import java.util.List;

public class Carga {

    private Expedicao expedicao;
    private List<Lote> lotes;

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
}
