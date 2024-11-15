package com.matheus.apibancariacrud.web.controller;

import com.matheus.apibancariacrud.contas.ContaBancaria;
import com.matheus.apibancariacrud.dto.DepositoRequest;
import com.matheus.apibancariacrud.dto.SaqueRequest;
import com.matheus.apibancariacrud.exception.ContaNaoEncontradoException;
import com.matheus.apibancariacrud.exception.DepositoInvalidoException;
import com.matheus.apibancariacrud.exception.SaqueInvalidoException;
import com.matheus.apibancariacrud.services.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/contas")
public class UsuarioController {

    @Autowired
    private ContaBancariaService service;
    @Autowired
    private ContaBancariaService contaBancariaService;

    @PostMapping
    public ResponseEntity<?> criarConta(@RequestBody ContaBancaria conta) {
        ContaBancaria novaConta = service.criarConta(conta.getTitular(), conta.getSaldoInicial());
        return ResponseEntity.ok(novaConta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancaria> buscarContaPorId(@PathVariable UUID id) {
        try {
            ContaBancaria conta = service.buscarContaOuFalhar(id);
            return ResponseEntity.ok(conta);
        } catch (ContaNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/depositar")
    public ResponseEntity<?> deposito(@PathVariable UUID id, @RequestBody DepositoRequest depositoRequest) {
        try {
            ContaBancaria contaAtualizada = service.deposito(id, depositoRequest.getValorDeposito());
            return ResponseEntity.ok(contaAtualizada);
        } catch (DepositoInvalidoException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ContaNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/saque")
    public ResponseEntity<?> saque(@PathVariable UUID id, @RequestBody SaqueRequest saqueRequest){
        try{
            ContaBancaria contaAtualizada = service.saque(id, saqueRequest.getSaqueValor());
            return ResponseEntity.ok(contaAtualizada);
        } catch (SaqueInvalidoException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ContaNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteConta(@PathVariable UUID id) {
        try{
            contaBancariaService.deleteConta(id);
            return ResponseEntity.ok("Conta com o id " + id + " deletada com sucesso!");
        } catch (ContaNaoEncontradoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
