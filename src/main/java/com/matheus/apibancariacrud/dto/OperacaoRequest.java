package com.matheus.apibancariacrud.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OperacaoRequest {

    @NotNull(message = "O valor não pode ser nulo.")
    @Positive(message = "O valor deve ser maior que zero.")
    private double valor;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}

