package model.request;

import android.os.AsyncTask;

public class Solicita extends AsyncTask<String, Void, String> {

    private String parametros;

    public Solicita(String parametros){
        this.parametros = parametros;
    }

    @Override
    protected String doInBackground(String... urls) {
        return Connection.postDados(urls[0], parametros);

    }

    @Override
    protected void onPostExecute(String resultado) {

    }


}
