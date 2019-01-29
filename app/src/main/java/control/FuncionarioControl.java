package control;

import model.Conferente;
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
        }
        return str;
    }

    public Funcionario logar(){
        return funcionario;
    }

    public void addFuncionario(String nome,String cpf, String senha,char perfil){
        cpf = replaceCPF(cpf);
        if(perfil == 'C'){
            ((Gerente) funcionario).cadastrarFuncionario(new Conferente(nome,cpf,senha,perfil));
        }else if(perfil == 'M'){
            ((Gerente) funcionario).cadastrarFuncionario(new Motorista(nome,cpf,senha,perfil));
        }else if(perfil == 'G'){
            ((Gerente) funcionario).cadastrarFuncionario(new Gerente(nome,cpf,senha,perfil));
        }
    }
    public void addFuncionario(String nome,String cpf, String senha,char perfil, String foto){
        cpf = replaceCPF(cpf);
        if(perfil == 'C'){
            ((Gerente) funcionario).cadastrarFuncionario(new Conferente(nome,cpf,senha,foto,perfil));
        }else if(perfil == 'M'){
            ((Gerente) funcionario).cadastrarFuncionario(new Motorista(nome,cpf,senha,foto,perfil));
        }else if(perfil == 'G'){
            ((Gerente) funcionario).cadastrarFuncionario(new Gerente(nome,cpf,senha,foto,perfil));
        }
    }

    private String replaceCPF(String cpf){
        cpf = cpf.replace(".","");
        cpf = cpf.replace("-","");
        return cpf;
    }


}
