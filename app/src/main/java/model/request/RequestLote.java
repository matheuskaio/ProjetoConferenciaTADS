package model.request;

import android.util.Log;

import java.util.concurrent.ExecutionException;

public class RequestLote {
    private String parametros;


    public String selectFromCarga(String carga) {
        try {
            carga = new Solicita("carga=" + carga).execute(Connection.URL + "selectLoteFromCarga.php").get();
            Log.e("Cargag",carga);
            return carga;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Erro ao consultar";
    }
}

