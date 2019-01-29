package model.request;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import model.Expedicao;

public class RequestExpedicao {
    private String params;
    public void insert(Expedicao expedicao){
        params = "expedicao="+new Gson().toJson(expedicao);
        new Solicita(params).execute(Connection.URL+"insertExpedicao.php");
    }
    public String select(){
        try {
            return new Solicita("table=expedicao").execute(Connection.URL+"select.php").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Erro ao consultar";
    }
}
