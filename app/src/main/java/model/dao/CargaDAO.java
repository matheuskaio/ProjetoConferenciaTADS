package model.dao;

import java.util.ArrayList;
import java.util.List;
import control.Carga;
import control.Conferente;
import model.Banco;

public class CargaDAO {

    public void insert(Carga carga){
        Banco.cargas.add(carga);
    }

    public void delete(Carga carga){
        Banco.cargas.remove(carga);
    }

    public List<Carga> selecte(){
        return Banco.cargas;
    }

    public List<Carga> selecte(Conferente conferente){
        List<Carga> l = new ArrayList<>();
        for (Carga c:Banco.cargas) {
            if(c.getConferente() == conferente){
                l.add(c);
            }
        }
        return l;
    }
}
