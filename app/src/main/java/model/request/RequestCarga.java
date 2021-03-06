package model.request;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ExecutionException;

import model.Caminhao;
import model.Carga;
import model.Conferente;
import model.Motorista;

public class    RequestCarga {

    private String parametros;
    public void insert(Carga carga,String motorista){
        String strCarga = "{" +
                "\"expedicao\":"+carga.getExpedicao()+","+
                "\"conferente\":\""+carga.getConferente().getCpf()+"\","+
                "\"caminhao\":\""+carga.getCaminhao().getPlaca()+"\""+"}";

        try {

            parametros = "carga="+strCarga+"&lotes="+URLEncoder.encode(new Gson().toJson(carga.getLotes()),"UTF-8")+"&motorista="+motorista;
            Log.e("C",parametros);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            String str = new Solicita(parametros).execute(Connection.URL+"insertCarga.php").get();
            Log.e("result",str);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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


    public String selecte(Caminhao caminhao){

        try {
            Log.e("Placa",caminhao.getPlaca());
            return new Solicita("caminhao="+caminhao.getPlaca()).execute(Connection.URL+"selectCargas.php").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Erro ao consultar";
    }

    public String selecte(Conferente conferente){
        parametros ="cpf="+conferente.getCpf();
        try {
            return new Solicita(parametros).execute(Connection.URL+"selectCargas.php").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Erro ao consultar";
    }

    public List<Carga> selecte(Motorista motorista){
        String str = null;
        try {
           str = new Solicita("cpf="+motorista.getCpf()).execute(Connection.URL+"selecteCargaMotorista.php").get();
           Log.e("String",str);
           if(str.length()<25){
               return null;
           }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Gson().fromJson(str,new TypeToken<List<Carga>>(){}.getType());
    }


}
