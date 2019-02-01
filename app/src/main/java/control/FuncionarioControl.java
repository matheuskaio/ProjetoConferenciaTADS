package control;

import java.util.ArrayList;
import java.util.List;

import model.Caminhao;
import model.Carga;
import model.Conferente;
import model.Expedicao;
import model.Funcionario;
import model.Gerente;
import model.Motorista;
import model.exceptions.CPFNotFoundException;
import model.request.RequestFuncionario;

public class FuncionarioControl {

    private Funcionario funcionario;
    private static FuncionarioControl funcionarioControl;

    private FuncionarioControl(){

    }

    public static FuncionarioControl getIstace(){
        if(funcionarioControl == null){
            funcionarioControl = new FuncionarioControl();
        }
        return funcionarioControl;
    }

    public String altenticado(String login,String senha){
        String str = null;
        login = replaceCPF(login);
        try {
            funcionario = new RequestFuncionario().get(login);
            if(!funcionario.getSenha().equals(senha)){
                str = "Senha incorreta";
            }
        } catch (CPFNotFoundException e) {
            str = "CPF não encontrado";
        } catch (NullPointerException e){
            str = "Erro de conexão";
        }
        return str;
    }

    public Funcionario logar(){
        return funcionario;
    }


    public Funcionario getFuncionario(){
        return funcionario;
    }


    public boolean isConferente(){
        if(funcionario instanceof Conferente){
            return true;
        }
        return false;
    }

    public boolean isMotorista(){
        if(funcionario instanceof Motorista){
            return true;
        }
        return false;
    }



    public String validarCPF(String cpf){
        if(cpf.isEmpty()){
            return "Informe um cpf";
        }
        if(cpf.length()<14){
            return "CPF muito curto";
        }
        return null;
    }

    public String replaceCPF(String cpf){
        cpf = cpf.replace(".","");
        cpf = cpf.replace("-","");
        return cpf;
    }
}
