package com.atividade.security_data.model;

public class ClasseTeste {

    private String campoTeste;
    private Integer campoNumeroTeste;

    public ClasseTeste(String campoTeste, Integer campoNumeroTeste) {
        this.campoTeste = campoTeste;
        this.campoNumeroTeste = campoNumeroTeste;
    }

    public String getCampoTeste() {
        return campoTeste;
    }

    public void setCampoTeste(String campoTeste) {
        this.campoTeste = campoTeste;
    }

    public Integer getCampoNumeroTeste() {
        return campoNumeroTeste;
    }

    public void setCampoNumeroTeste(Integer campoNumeroTeste) {
        this.campoNumeroTeste = campoNumeroTeste;
    }
}
