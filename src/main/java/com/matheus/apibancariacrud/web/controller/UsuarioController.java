package com.matheus.apibancariacrud.web.controller;

import com.matheus.apibancariacrud.contas.ContaBancaria;
import com.matheus.apibancariacrud.dto.*;
import com.matheus.apibancariacrud.exception.ContaNaoEncontradoException;
import com.matheus.apibancariacrud.exception.DepositoInvalidoException;
import com.matheus.apibancariacrud.exception.SaqueInvalidoException;
import com.matheus.apibancariacrud.services.ContaBancariaService;
<<<<<<< HEAD
=======
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
>>>>>>> cc3df81 (Código atualizado com orientação ao objeto e o método transferir, e o swagger implementado)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/contas")
<<<<<<< HEAD
public class UsuarioController {


=======
@Tag(name = "Contas Bancárias", description = "Gerenciamento de contas bancárias")
public class UsuarioController {

>>>>>>> cc3df81 (Código atualizado com orientação ao objeto e o método transferir, e o swagger implementado)
    @Autowired
    private ContaBancariaService contaBancariaService;

    @PostMapping
<<<<<<< HEAD
=======
    @Operation(summary = "Criar conta bancária", description = "Cria uma nova conta bancária com base nos dados fornecidos.")
>>>>>>> cc3df81 (Código atualizado com orientação ao objeto e o método transferir, e o swagger implementado)
    public ResponseEntity<?> criarConta(@RequestBody ContaBancariaRequest contaRequest) {
        ContaBancariaResponse novaContaResponse = contaBancariaService.criarConta(contaRequest);
        return ResponseEntity.ok(novaContaResponse);
    }

<<<<<<< HEAD


    @GetMapping("/{id}")
=======
    @GetMapping("/{id}")
    @Operation(summary = "Buscar conta por ID", description = "Retorna os detalhes de uma conta bancária pelo seu ID.")
>>>>>>> cc3df81 (Código atualizado com orientação ao objeto e o método transferir, e o swagger implementado)
    public ResponseEntity<ContaBancaria> buscarContaPorId(@PathVariable UUID id) {
        try {
            ContaBancaria conta = contaBancariaService.buscarContaOuFalhar(id);
            return ResponseEntity.ok(conta);
        } catch (ContaNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/depositar")
<<<<<<< HEAD
=======
    @Operation(summary = "Depositar em conta", description = "Realiza um depósito em uma conta bancária pelo ID.")
>>>>>>> cc3df81 (Código atualizado com orientação ao objeto e o método transferir, e o swagger implementado)
    public ResponseEntity<?> deposito(@PathVariable UUID id, @RequestBody OperacaoRequest request) {
        try {
            ContaBancaria conta = contaBancariaService.deposito(id, request);
            return ResponseEntity.ok(conta);
<<<<<<< HEAD
        } catch (DepositoInvalidoException e){
=======
        } catch (DepositoInvalidoException e) {
>>>>>>> cc3df81 (Código atualizado com orientação ao objeto e o método transferir, e o swagger implementado)
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ContaNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

<<<<<<< HEAD

    @PostMapping("/{id}/saque")
=======
    @PostMapping("/{id}/saque")
    @Operation(summary = "Sacar de conta", description = "Realiza um saque de uma conta bancária pelo ID.")
>>>>>>> cc3df81 (Código atualizado com orientação ao objeto e o método transferir, e o swagger implementado)
    public ResponseEntity<?> saque(@PathVariable UUID id, @RequestBody OperacaoRequest request) {
        try {
            ContaBancaria contaAtual = contaBancariaService.saque(id, request);
            return ResponseEntity.ok(contaAtual);
<<<<<<< HEAD
        } catch (SaqueInvalidoException e){
=======
        } catch (SaqueInvalidoException e) {
>>>>>>> cc3df81 (Código atualizado com orientação ao objeto e o método transferir, e o swagger implementado)
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ContaNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
<<<<<<< HEAD
    public ResponseEntity<?> deleteConta(@PathVariable UUID id) {
        try{
=======
    @Operation(summary = "Deletar conta", description = "Remove uma conta bancária pelo ID.")
    public ResponseEntity<?> deleteConta(@PathVariable UUID id) {
        try {
>>>>>>> cc3df81 (Código atualizado com orientação ao objeto e o método transferir, e o swagger implementado)
            contaBancariaService.deleteConta(id);
            return ResponseEntity.ok("Conta com o id " + id + " deletada com sucesso!");
        } catch (ContaNaoEncontradoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
<<<<<<< HEAD

=======
>>>>>>> cc3df81 (Código atualizado com orientação ao objeto e o método transferir, e o swagger implementado)
}
