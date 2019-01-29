package model.request;

import android.util.Log;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import model.Caminhao;

public class RequestCaminhao {
    public void insert(Caminhao caminhao){
        String str = new Gson().toJson(caminhao);
        Log.e("camina",str);
        new Solicita("caminhao="+str).execute(Connection.URL+"insertCaminhao.php");
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
