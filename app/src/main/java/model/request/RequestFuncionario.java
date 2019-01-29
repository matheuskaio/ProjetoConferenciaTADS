package model.request;

import android.util.Log;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import model.Conferente;
import model.Funcionario;
import model.Gerente;
import model.Motorista;
import model.exceptions.CPFNotFoundException;

public class RequestFuncionario{
    private String parametros;

    public void insert(Funcionario funcionario){
        parametros = "funcionario="+new Gson().toJson(funcionario);
        Log.e("Funcionario",parametros);
        new Solicita(parametros).execute(Connection.URL+"insertFuncionario.php");
    }

    public Funcionario get(String cpf) throws CPFNotFoundException {
        parametros = "cpf="+cpf;
        String str = null;
        try {
            str = new Solicita(parametros).execute(Connection.URL+"getFuncionario.php").get();
            Log.e("R","chegou "+str+" <-");
            if(str.length()<25){
                throw new CPFNotFoundException();
            }
            char perfil = str.charAt(str.length()-4);
            if(perfil=='G'){
                return new Gson().fromJson(str, Gerente.class);
            }else if(perfil=='C'){
                return new Gson().fromJson(str, Conferente.class);
            }else{
                return new Gson().fromJson(str, Motorista.class);
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

}
