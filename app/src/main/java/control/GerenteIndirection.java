package control;

import java.util.List;

import model.Caminhao;
import model.Carga;
import model.Conferente;
import model.Gerente;
import model.Motorista;
import model.exceptions.ConexaoException;

public class GerenteIndirection {
    private Gerente gerente;
    private FuncionarioControl funcionarioControl;
    private static GerenteIndirection gerenteIndirection;

    private GerenteIndirection(){
        funcionarioControl = FuncionarioControl.getIstace();
        gerente = (Gerente) funcionarioControl.getFuncionario();
    }
    public static GerenteIndirection getInstance(){
        if(gerenteIndirection !=null){
            return gerenteIndirection;
        }
        return new GerenteIndirection();
    }

    public void addFuncionario(String nome,String cpf, String senha,char perfil){
        cpf = funcionarioControl.replaceCPF(cpf);
        if(perfil == 'C'){
            gerente.cadastrarFuncionario(new Conferente(nome,cpf,senha,perfil));
        }else if(perfil == 'M'){
            gerente.cadastrarFuncionario(new Motorista(nome,cpf,senha,perfil));
        }else if(perfil == 'G'){
            gerente.cadastrarFuncionario(new Gerente(nome,cpf,senha,perfil));
        }
    }

    public List<Carga> allCargas() throws ConexaoException {
        return gerente.allCargas();
    }

    public void addFuncionario(String nome,String cpf, String senha,char perfil, String foto){
        cpf = funcionarioControl.replaceCPF(cpf);
        if(perfil == 'C'){
            gerente.cadastrarFuncionario(new Conferente(nome,cpf,senha,foto,perfil));
        }else if(perfil == 'M'){
            gerente.cadastrarFuncionario(new Motorista(nome,cpf,senha,foto,perfil));
        }else if(perfil == 'G'){
            gerente.cadastrarFuncionario(new Gerente(nome,cpf,senha,foto,perfil));
        }
    }

    public List<Conferente> allConferentes() throws ConexaoException {
        return gerente.listarConferente();
    }
    public List<Caminhao> allCaminhoes() throws ConexaoException {
        return gerente.listarCaminhoes();
    }

    public List<Motorista> allMotoristas() throws ConexaoException {
        return gerente.listarMotoristas();
    }
}
