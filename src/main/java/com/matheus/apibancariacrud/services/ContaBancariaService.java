package com.matheus.apibancariacrud.services;

import com.matheus.apibancariacrud.contas.ContaBancaria;
import com.matheus.apibancariacrud.dto.ContaBancariaRequest;
import com.matheus.apibancariacrud.dto.ContaBancariaResponse;
import com.matheus.apibancariacrud.dto.OperacaoRequest;
import com.matheus.apibancariacrud.exception.ContaNaoEncontradoException;
import com.matheus.apibancariacrud.exception.DepositoInvalidoException;
import com.matheus.apibancariacrud.exception.SaldoInicialException;
import com.matheus.apibancariacrud.exception.SaqueInvalidoException;
import com.matheus.apibancariacrud.mapper.ContaBancariaMapper;
import com.matheus.apibancariacrud.repositorio.ContaBancariaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepositorio repositorio;


    public ContaBancariaResponse criarConta(ContaBancariaRequest request){
        ContaBancaria conta = ContaBancariaMapper.toEntity(request);
        if (conta.getSaldoInicial() < 0 || Double.isNaN(conta.getSaldoInicial())){
            throw new SaldoInicialException("O saldo inicial deve ser maior ou igual a zero!");
        }
        ContaBancaria contaSalva = repositorio.save(conta);
        return ContaBancariaMapper.toResponse(contaSalva);

    }

    public ContaBancaria buscarContaOuFalhar(UUID id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradoException("Conta não encontrada com ID: " + id));
    }

    public ContaBancaria deposito(UUID id, OperacaoRequest request){
        ContaBancaria conta = buscarContaOuFalhar(id);
        if(request.getValor() <= 0){
            throw new DepositoInvalidoException("O valor deve ser maior que zero!");
        }
        conta.depositar(request.getValor());
        return repositorio.save(conta);
    }

    public ContaBancaria saque(UUID id, OperacaoRequest request){
        ContaBancaria conta = buscarContaOuFalhar(id);
        conta.sacar(request.getValor());
        return repositorio.save(conta);
    }

    public void deleteConta(UUID id) {
        ContaBancaria conta = buscarContaOuFalhar(id);
        if (!conta.podeDelatar()){
        throw new SaldoInicialException("O saldo para deletar deve ser igual a zero!");}
        repositorio.deleteById(id);
    }

}
