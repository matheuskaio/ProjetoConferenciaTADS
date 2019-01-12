package control;

import java.util.List;

import model.dao.CargaDAO;

public class Conferente extends Funcionario {

    private Carga carga;

    public Carga getCarga() {
        return carga;
    }

    public void setCarga(Carga carga) {
        this.carga = carga;
    }

    public void iniciarCoferencia(){
        if(carga==null) {carga = new Carga(this);}
    }

    public void addLote(Lote lote){
        carga.addLote(lote);
    }

    public void finalizarConferencia(){
        new CargaDAO().insert(carga); carga = null;
    }

    public List<Carga> myCargas(){
        return new CargaDAO().selecte(this);
    }

    public List<Carga> allCargas() {
        return  new CargaDAO().selecte();
    }
}
