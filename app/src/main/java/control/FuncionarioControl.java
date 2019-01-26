package control;

import model.Conferente;
import model.Funcionario;
import model.Gerente;
import model.Motorista;
import model.Perfil;

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
    public void addFuncionario(String nome,String cpf, String senha,Perfil perfil){
        if(perfil == Perfil.CONFERENTE){
            ((Gerente) funcionario).cadastrarFuncionario(new Conferente(nome,cpf,senha,perfil));
        }else if(perfil == Perfil.MOTORISTA){
            ((Gerente) funcionario).cadastrarFuncionario(new Motorista(nome,cpf,senha,perfil));
        }else if(perfil == Perfil.GERENTE){
            ((Gerente) funcionario).cadastrarFuncionario(new Gerente(nome,cpf,senha,perfil));
        }
    }
    public void addFuncionario(String nome,String cpf, String senha,Perfil perfil, String foto){
        if(perfil == Perfil.CONFERENTE){
            ((Gerente) funcionario).cadastrarFuncionario(new Conferente(nome,cpf,senha,foto,perfil));
        }else if(perfil == Perfil.MOTORISTA){
            ((Gerente) funcionario).cadastrarFuncionario(new Motorista(nome,cpf,senha,foto,perfil));
        }else if(perfil == Perfil.GERENTE){
            ((Gerente) funcionario).cadastrarFuncionario(new Gerente(nome,cpf,senha,foto,perfil));
        }
    }
}
