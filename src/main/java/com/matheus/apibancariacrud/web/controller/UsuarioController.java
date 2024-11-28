package com.matheus.apibancariacrud.web.controller;

import com.matheus.apibancariacrud.contas.ContaBancaria;
import com.matheus.apibancariacrud.dto.*;
import com.matheus.apibancariacrud.exception.ContaNaoEncontradoException;
import com.matheus.apibancariacrud.exception.DepositoInvalidoException;
import com.matheus.apibancariacrud.exception.SaqueInvalidoException;
import com.matheus.apibancariacrud.services.ContaBancariaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/contas")
@Tag(name = "Contas Bancárias", description = "Gerenciamento de contas bancárias")
public class UsuarioController {

    @Autowired
    private ContaBancariaService contaBancariaService;

    @PostMapping
    @Operation(summary = "Criar conta bancária", description = "Cria uma nova conta bancária com base nos dados fornecidos.")
    public ResponseEntity<?> criarConta(@RequestBody ContaBancariaRequest contaRequest) {
        ContaBancariaResponse novaContaResponse = contaBancariaService.criarConta(contaRequest);
        return ResponseEntity.ok(novaContaResponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar conta por ID", description = "Retorna os detalhes de uma conta bancária pelo seu ID.")
    public ResponseEntity<ContaBancaria> buscarContaPorId(@PathVariable UUID id) {
        try {
            ContaBancaria conta = contaBancariaService.buscarContaOuFalhar(id);
            return ResponseEntity.ok(conta);
        } catch (ContaNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/depositar")
    @Operation(summary = "Depositar em conta", description = "Realiza um depósito em uma conta bancária pelo ID.")
    public ResponseEntity<?> deposito(@PathVariable UUID id, @RequestBody OperacaoRequest request) {
        try {
            ContaBancaria conta = contaBancariaService.deposito(id, request);
            return ResponseEntity.ok(conta);
        } catch (DepositoInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ContaNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/saque")
    @Operation(summary = "Sacar de conta", description = "Realiza um saque de uma conta bancária pelo ID.")
    public ResponseEntity<?> saque(@PathVariable UUID id, @RequestBody OperacaoRequest request) {
        try {
            ContaBancaria contaAtual = contaBancariaService.saque(id, request);
            return ResponseEntity.ok(contaAtual);
        } catch (SaqueInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ContaNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar conta", description = "Remove uma conta bancária pelo ID.")
    public ResponseEntity<?> deleteConta(@PathVariable UUID id) {
        try {
            contaBancariaService.deleteConta(id);
            return ResponseEntity.ok("Conta com o id " + id + " deletada com sucesso!");
        } catch (ContaNaoEncontradoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
