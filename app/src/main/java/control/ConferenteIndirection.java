package control;

import java.util.ArrayList;
import java.util.List;

import model.Caminhao;
import model.Carga;
import model.Conferente;
import model.Expedicao;
import model.Motorista;
import model.exceptions.EmptyFieldException;

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

    public void finalizarConferencia(String motorista){
        conferente.finalizarConferencia(motorista);
    }

    public Carga getCarga(){
        return  conferente.getCarga();
    }

    public List<Expedicao> expedicaos(){
        return conferente.listarExpedicaos();
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

    public void cadastrarExpedicao(String name,List<String> cidades) throws EmptyFieldException {
        Expedicao expedicao = new Expedicao(new ArrayList<String>(), name);
        if(name.isEmpty()){
            throw new EmptyFieldException();
        }
        for (String cidade:cidades){
            if(cidade.isEmpty()){
                throw new EmptyFieldException();
            }
            expedicao.add(cidade);
        }
        conferente.cadastraExpedicao(expedicao);
    }

    public List<Carga> myCargas() {
        return conferente.myCargas();
    }

    public void adicionarLote(){

    }
}
