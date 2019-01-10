package control;

import java.util.List;

import model.Banco;

public class Conferente extends Funcionario {
    private List<Carga> cargas;
    public Conferente(){
        cargas = Banco.cargas;
    }
    public void finalizarConferencia(Carga carga){
        cargas.add(carga);
    }
}
