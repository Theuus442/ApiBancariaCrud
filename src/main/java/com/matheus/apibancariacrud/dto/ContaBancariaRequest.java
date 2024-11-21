package com.matheus.apibancariacrud.dto;

public class ContaBancariaRequest {
    private String titular;
    private double saldoInicial;

    public ContaBancariaRequest() {}

    public ContaBancariaRequest(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldoInicial = saldoInicial;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }
}
