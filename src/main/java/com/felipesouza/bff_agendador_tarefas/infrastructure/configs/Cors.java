package com.felipesouza.bff_agendador_tarefas.infrastructure.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//CORS é um mecanismo de segurança dos navegadores que controla quais sites podem fazer requisições para uma API.

@Configuration      //Indica que a classe contém configurações do Spring.
public class Cors {

    @Bean     //Indica que o objeto retornado pelo metodo será gerenciado pelo Spring.
    public WebMvcConfigurer configCors(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //Permite qualquer rota do back end ser acessada
                registry.addMapping("/**")
                        //Apenas requisições dessa origem/url
                        .allowedOrigins("http://localhost:4200")
                        //Somente os seguintes métodos
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                        //Permite qualquer cabeçalho na requisição.
                        .allowedHeaders("*")
                        //Permite envio de cookies, sessão ou credenciais de autenticação.
                        .allowCredentials(true)
                        //O navegador pode armazenar a resposta do preflight (Cache) por 360 segundos.
                        .maxAge(360);

                WebMvcConfigurer.super.addCorsMappings(registry);
            }
        };
    }
}