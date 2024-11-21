package com.matheus.apibancariacrud.mapper;

import com.matheus.apibancariacrud.contas.ContaBancaria;
import com.matheus.apibancariacrud.dto.ContaBancariaRequest;
import com.matheus.apibancariacrud.dto.ContaBancariaResponse;

public class ContaBancariaMapper {
    public static ContaBancaria toEntity(ContaBancariaRequest request){
        ContaBancaria conta = new ContaBancaria();
        conta.setTitular(request.getTitular());
        conta.setSaldoInicial(request.getSaldoInicial());
        return conta;
    }

    public static ContaBancariaResponse toResponse(ContaBancaria conta){
        ContaBancariaResponse response = new ContaBancariaResponse();
        response.setId(conta.getId());
        response.setTitular(conta.getTitular());
        response.setSaldo(conta.getSaldoInicial());
        return response;
    }

}
