package control;

import model.Conferente;
import model.Funcionario;
import model.Gerente;
import model.Motorista;

public class FuncionarioControl {
    private Funcionario funcionario;
    public String altenticado(String login,String senha){
        Funcionario[] list = new Funcionario[3];
        list[0] = new Conferente(login,senha);
        list[1] = new Gerente(login,senha);
        list[2] = new Motorista(login,senha);
        String str = null;
        for (int i = 0; i < 3; i++){
            str = list[i].autenticado();
            if(str == null) {
                funcionario = list[i];
                break;
            }
        }
        return str;
    }
    public Funcionario logar(){
        return funcionario;
    }
}
