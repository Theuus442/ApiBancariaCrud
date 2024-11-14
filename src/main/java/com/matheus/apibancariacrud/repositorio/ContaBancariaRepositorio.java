package com.matheus.apibancariacrud.repositorio;

import com.matheus.apibancariacrud.contas.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContaBancariaRepositorio extends JpaRepository<ContaBancaria, UUID> {

}
