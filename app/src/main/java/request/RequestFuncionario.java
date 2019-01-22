package request;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.ExecutionException;

import model.Funcionario;

public class RequestFuncionario{
    private String parametros;
    private String retorno;
    public void insert(String cpf,String nome,String senha,char tipo){
        parametros = "funcionario = {" +
                "\"cpf\"    :\""+cpf+"\"," +
                "\"nome\"   :\""+nome+"\'," +
                "\"senha\"  :\""+senha+"\"," +
                "\"tipo\"   :\'"+tipo+"\'" +
                "}";
        new Solicita(parametros).execute(Connection.URL+"insertFuncionario.php");
    }
    public void insert(String data){
        parametros = data;
    }

    public String selecte(){
        return "selecteFuncionario.php";
    }

    public String get(String cpf){
        parametros = "cpf="+cpf;
        try {
            retorno = new Solicita(parametros).execute(Connection.URL+"getFuncionario.php").get();
            Log.e("Funcionario ",retorno+"");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return retorno;
    }

}
