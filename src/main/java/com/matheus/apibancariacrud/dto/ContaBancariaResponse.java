package com.matheus.apibancariacrud.dto;

import java.util.UUID;

public class ContaBancariaResponse {

    private UUID id;
    private String titular;
    private double saldo;

    public ContaBancariaResponse() {
    }

    public ContaBancariaResponse(UUID id, String titular, double saldo) {
        this.id = id;
        this.titular = titular;
        this.saldo = saldo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
