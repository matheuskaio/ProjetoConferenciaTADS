package control;

import java.util.ArrayList;
import java.util.List;

import model.Caminhao;
import model.Carga;
import model.Conferente;
import model.Motorista;
import model.exceptions.EmptyFieldException;
import model.exceptions.NullLoteExeption;

public class ConferenteIndirection {
    private Conferente conferente;
    private static ConferenteIndirection conferenteIndirection;
    private ConferenteIndirection(){
        conferente = (Conferente) FuncionarioControl.getIstace().getFuncionario();
    }

    public static ConferenteIndirection getInstance(){
        if(conferenteIndirection != null){
            return conferenteIndirection;
        }
        return new ConferenteIndirection();
    }

    public void iniciarConferencia(){
         conferente.iniciarConferencia();
    }

    public void finalizarConferencia(String strExpedicao,String motorista) throws EmptyFieldException, NumberFormatException, NullLoteExeption {
        if(strExpedicao.isEmpty()) {
            throw new EmptyFieldException();
        }
        if(conferente.getCarga().getLotes().isEmpty()){
            throw new NullLoteExeption();
        }
        int expedicao = Integer.parseInt(strExpedicao);
        conferente.finalizarConferencia(expedicao,motorista);
    }

    public Carga getCarga(){
        return  conferente.getCarga();
    }


    public List<Motorista> motoristas(){
        return conferente.listarMotoristas();
    }

    public List<Caminhao> caminhoes() {
        return conferente.listarCaminhoes();
    }

    public void cadastrarCaminhao(String modelo,String placa) throws EmptyFieldException {
        if(modelo.isEmpty()||placa.isEmpty()){
            throw new EmptyFieldException();
        }
        conferente.cadastrarCaminhao(new Caminhao(modelo,placa));
    }

    public List<Carga> myCargas() {
        return conferente.myCargas();
    }

    public void adicionarLote(){

    }
}
