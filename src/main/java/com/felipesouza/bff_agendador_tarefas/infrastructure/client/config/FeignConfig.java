package com.felipesouza.bff_agendador_tarefas.infrastructure.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Esse código registra a classe FeignError no Spring, permitindo que o Feign utilize minha lógica personalizada
// para tratar erros de chamadas a outras APIs.

@Configuration
public class FeignConfig {

    @Bean
    public FeignError feignError(){
        return new FeignError();
    }
}