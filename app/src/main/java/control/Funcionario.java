package control;

public abstract class Funcionario {

    private String nome,cpf,senha,observarcao,foto;

    public Funcionario logar(String cpf,String senha){
        if(autenticado(cpf,senha)){
            return this;
        }
        return null;
    }

    public boolean autenticado(String cpf,String senha){
        if(cpf.equals(this.cpf) && senha.equals(this.senha)){
            return true;
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getObservarcao() {
        return observarcao;
    }

    public void setObservarcao(String observarcao) {
        this.observarcao = observarcao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
