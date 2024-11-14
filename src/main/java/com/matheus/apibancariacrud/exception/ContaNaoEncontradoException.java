package com.matheus.apibancariacrud.exception;

public class ContaNaoEncontradoException extends RuntimeException {
    public ContaNaoEncontradoException(String message) {
        super(message);
    }
}
