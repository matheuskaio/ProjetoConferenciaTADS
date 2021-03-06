package model.request;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.concurrent.ExecutionException;

import model.Caminhao;
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
    public String select(char perfil){
        String str = null;
        try {
            str = new Solicita("perfil="+perfil).execute(Connection.URL+"selectFuncionario.php").get();
            if(str.length()<25){
                return null;
            }
            return str;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Funcionario get(String cpf) throws CPFNotFoundException {
        String str = null;
        try {
            Log.e("cpf",cpf+"");
            str = new Solicita("cpf="+cpf).execute(Connection.URL+"getFuncionario.php").get();
            Log.e("funcionario",str+"");
            if(str.length()<25){
                throw new CPFNotFoundException();
            }
            char perfil = str.charAt(str.length()-4);
            Log.e("pe",perfil+"");
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
