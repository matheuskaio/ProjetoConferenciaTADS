package model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conection {
    public static final String URL = "http://10.0.0.108/marcio/serverandroid/testeserver.php";

    public static String postDados(String urlUsuario, String parametrosUsuario) {
        URL url;
        HttpURLConnection connection = null;

        try {

            url = new URL(urlUsuario);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            connection.setRequestProperty("Content-Lenght", "" + Integer.toString(parametrosUsuario.getBytes().length));

            connection.setRequestProperty("Content-Language", "pt-BR");

            //connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Envio
            OutputStreamWriter outPutStream = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
            outPutStream.write(parametrosUsuario);
            outPutStream.flush();
            outPutStream.close();
            //Recepção
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

            String linha;
            StringBuffer resposta = new StringBuffer();

            while((linha = bufferedReader.readLine()) != null) {
                resposta.append(linha);
                resposta.append('\r');
            }

            bufferedReader.close();

            return resposta.toString();

        } catch (Exception erro) {

            return  null;
        } finally {

            if(connection != null) {
                connection.disconnect();
            }
        }
    }
}