package request;

import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import model.Carga;
import model.Conferente;

public class RequestCarga {

    private String parametros;
    public void insert(Carga carga){
        String strCarga = "{" +
                "\"id\":\""+carga.getId()+"\","+
                "\"conferente\":\""+carga.getConferente().getCpf()+"\","+
                "\"expedicao\":"+"2"+"}";

        Banco.cargas.add(carga);
        try {
            parametros = "carga="+strCarga+"&lotes="+URLEncoder.encode(new Gson().toJson(carga.getLotes()),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        new Solicita(parametros).execute(Connection.URL+"insertCarga.php");

    }

    public void delete(Carga carga){
        Banco.cargas.remove(carga);
    }

    public String selecte(){

        try {
            String str = new Solicita("table=carga").execute(Connection.URL+"select.php").get();
            return str;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Erro ao consultar";
    }

    public List<Carga> selecte(Conferente conferente){
        List<Carga> l = new ArrayList<>();
        for (Carga c:Banco.cargas) {
            if(c.getConferente() == conferente){
                l.add(c);
            }
        }
        return l;
    }


}
