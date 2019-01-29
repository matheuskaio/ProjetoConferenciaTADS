package model.request;

import java.util.concurrent.ExecutionException;

public class RequestCaminhao {
    public void insert(String data){

    }
    public String select(){
        try {
            return new Solicita("table=caminhao").execute(Connection.URL+"select.php").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Erro ao consultar";
    }
}
