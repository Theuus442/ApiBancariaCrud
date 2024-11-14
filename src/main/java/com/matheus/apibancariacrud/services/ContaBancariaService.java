package com.matheus.apibancariacrud.services;

import com.matheus.apibancariacrud.contas.ContaBancaria;
import com.matheus.apibancariacrud.exception.ContaNaoEncontradoException;
import com.matheus.apibancariacrud.exception.DepositoInvalidoException;
import com.matheus.apibancariacrud.exception.SaldoInicialException;
import com.matheus.apibancariacrud.exception.SaqueInvalidoException;
import com.matheus.apibancariacrud.repositorio.ContaBancariaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepositorio repositorio;


    public ContaBancaria criarConta(String titular, double saldoInicial){
        if (saldoInicial < 0 || Double.isNaN(saldoInicial)){
            throw new SaldoInicialException("O saldo inicial deve ser maior ou igual a zero!");
        }
        ContaBancaria conta = new ContaBancaria();
        conta.setTitular(titular);
        conta.setSaldoInicial(saldoInicial);

        return repositorio.save(conta);
    }


    public ContaBancaria buscarContaOuFalhar(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradoException("Conta não encontrada com ID: " + id));
    }

    public ContaBancaria deposito(UUID id, double valorDeposito){
        if (valorDeposito <= 0){
            throw new DepositoInvalidoException("O valor deve ser maior que zero!");
        }
        ContaBancaria conta = repositorio.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradoException("Conta não encontrada com o ID: " + id));

        double saldoAtual = conta.getSaldoInicial();
        conta.setSaldoInicial(saldoAtual + valorDeposito);
        return repositorio.save(conta);
    }

    public ContaBancaria saque(UUID id, double valorSaque){
        if (valorSaque <= 0){
            throw new SaqueInvalidoException("O valor deve ser maior que zero!");
        }

        ContaBancaria conta = repositorio.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradoException("Conta não encontrada com o ID: " + id));

        double saldoAtual = conta.getSaldoInicial();
        if (valorSaque > saldoAtual) {
            throw new SaqueInvalidoException("Saldo insuficiente para realizar o saque!");
        }

        conta.setSaldoInicial(saldoAtual - valorSaque);
        return repositorio.save(conta);
    }

}
