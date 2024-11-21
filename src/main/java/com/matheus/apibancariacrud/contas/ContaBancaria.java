package com.matheus.apibancariacrud.contas;

import com.matheus.apibancariacrud.exception.DepositoInvalidoException;
import com.matheus.apibancariacrud.exception.SaqueInvalidoException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String titular;
    private double saldoInicial;

    public ContaBancaria() {
    }

    public void depositar(double valor) {
        if (valor <= 0){
            throw new DepositoInvalidoException("O valor deve ser maior que zero!");
        }
        this.saldoInicial += valor;
    }

    public void sacar(double valor) {
        if (valor <= 0){
            throw new SaqueInvalidoException("O valor deve ser maior que zero!");
        } if (valor > this.saldoInicial){
            throw new SaqueInvalidoException("Saldo insuficiente para realizar o saque!");
        }
        this.saldoInicial -= valor;
    }

    public boolean podeDelatar(){
        return this.saldoInicial == 0;
    }

    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldoInicial = saldoInicial;
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

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

}
