package control;

import java.io.Serializable;

public class Caminhao implements Serializable {
    private String modelo;
    private String placa;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
