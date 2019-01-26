package model;

public enum Perfil {
    GERENTE('G'),CONFERENTE('G'),MOTORISTA('M');

    private final char tipo;
    Perfil(char tipo){
        this.tipo = tipo;
    }

}
