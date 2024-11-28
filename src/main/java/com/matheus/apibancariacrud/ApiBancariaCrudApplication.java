package com.matheus.apibancariacrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

<<<<<<< HEAD
@SpringBootApplication
=======
@SpringBootApplication(scanBasePackages = "com.matheus.apibancariacrud")
>>>>>>> cc3df81 (Código atualizado com orientação ao objeto e o método transferir, e o swagger implementado)
public class ApiBancariaCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiBancariaCrudApplication.class, args);
    }

}
