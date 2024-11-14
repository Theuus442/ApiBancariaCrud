package com.matheus.apibancariacrud.exception;

public class DepositoInvalidoException extends RuntimeException {
    public DepositoInvalidoException(String message) {
        super(message);
    }
}
