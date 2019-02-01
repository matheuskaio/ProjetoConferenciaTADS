package control;

import model.Conferente;
import model.Lote;
import model.exceptions.EmptyFieldException;

public class LoteControl {
    Conferente conferente;
    public LoteControl(){
        conferente = (Conferente) FuncionarioControl.getIstace().getFuncionario();
    }
    public boolean inserirLote(int altura,int lastro,String produto,String imagemAltura, String imagemLastro) throws EmptyFieldException {
        if(produto.isEmpty()||
                imagemAltura.isEmpty()||
                imagemLastro.isEmpty()||
                altura==0||
                lastro==0){
            throw new EmptyFieldException();
        }
        conferente.addLote(new Lote(altura,lastro,produto,"",imagemAltura,imagemLastro));
        return true;
    }
}
