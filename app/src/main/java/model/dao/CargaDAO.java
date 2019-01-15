package model.dao;

import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import control.Carga;
import control.Conferente;
import model.Banco;
import model.Conection;

public class CargaDAO {

    private String parametros;
    public void insert(Carga carga){

        Banco.cargas.add(carga);
        parametros = "lotes="+new Gson().toJson(carga.getLotes());
        new CargaDAO.solicita().execute(Conection.URL);

    }

    public void delete(Carga carga){
        Banco.cargas.remove(carga);
    }

    public List<Carga> selecte(){
        return Banco.cargas;
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

    private class solicita extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            return Conection.postDados(urls[0], parametros);
        }

        @Override
        protected void onPostExecute(String resultado) {

            Log.e("result",resultado);

        }
    }
}
