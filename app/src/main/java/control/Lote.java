package control;

public class Lote {
    private int altura,lastro;
    private String nomeProduto,observacao,fotoAltura,fotoLastro;

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLastro() {
        return lastro;
    }

    public void setLastro(int lastro) {
        this.lastro = lastro;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getFotoAltura() {
        return fotoAltura;
    }

    public void setFotoAltura(String fotoAltura) {
        this.fotoAltura = fotoAltura;
    }

    public String getFotoLastro() {
        return fotoLastro;
    }

    public void setFotoLastro(String fotoLastro) {
        this.fotoLastro = fotoLastro;
    }
    public String getDimensao(){
        return altura + " de altura por " + lastro + " de lastro";
    }
}
