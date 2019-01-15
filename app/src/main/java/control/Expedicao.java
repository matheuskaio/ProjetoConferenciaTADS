package control;

import java.io.Serializable;
import java.util.List;

public class Expedicao implements Serializable {
    private List<String> cidades;

    public void add(String cidade){
        this.cidades.add(cidade);
    }

    public void remove(String cidade){
        this.cidades.remove(cidade);
    }

    public List<String> getCidades() {
        return cidades;
    }

    public void setCidades(List<String> cidades) {
        this.cidades = cidades;
    }

    public int quantidadeDeCidades(){
        return cidades.size();
    }
}
