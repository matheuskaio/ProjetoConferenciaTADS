package model.request;

import android.util.Log;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

import model.Carga;

public class    RequestCarga {

    private String parametros;
    public void insert(Carga carga){
        String strCarga = "{" +
                "\"id\":\""+carga.getId()+"\","+
                "\"conferente\":\""+carga.getConferente().getCpf()+"\","+
                "\"expedicao\":"+carga.getExpedicao().getId()+"}";

        try {

            parametros = "carga="+strCarga+"&lotes="+URLEncoder.encode(new Gson().toJson(carga.getLotes()),"UTF-8");
            Log.e("C",parametros);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        new Solicita(parametros).execute(Connection.URL+"insertCarga.php");

    }


    public String selecte(){

        try {
            return new Solicita("").execute(Connection.URL+"selectCargas.php").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Erro ao consultar";
    }

    public String selecte(String cpf){
        parametros ="cpf="+cpf;
        try {
            return new Solicita(parametros).execute(Connection.URL+"selectMyCargas.php").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Erro ao consultar";
    }


}
