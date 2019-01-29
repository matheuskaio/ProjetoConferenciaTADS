package model.request;

import java.util.concurrent.ExecutionException;

public class RequestExpedicao {
    private String params;
    public void insert(String data){
        params = "expedicao="+data;
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
