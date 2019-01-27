package request;

import android.util.Log;

public class RequestExpedicao {
    private String params;
    public void insert(String data){
        params = "expedicao="+data;
        Log.e("Data",params);
        new Solicita(params).execute(Connection.URL+"insertExpedicao.php");
    }
}
