package control;

import java.util.List;

import model.Carga;
import model.Motorista;

public class MotoristaIndirection {
    private Motorista motorista;
    private static MotoristaIndirection motoristaIndirection;

    private MotoristaIndirection(){
        motorista = (Motorista) FuncionarioControl.getIstace().getFuncionario();
    }

    public static MotoristaIndirection getInstance(){
        if(motoristaIndirection!=null){
            return motoristaIndirection;
        }
        return new MotoristaIndirection();
    }

    public List<Carga> myCargas() {
        return motorista.myCargas();
    }
}
