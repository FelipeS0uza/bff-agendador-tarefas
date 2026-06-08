package com.felipesouza.bff_agendador_tarefas.infrastructure.client.config;

import com.felipesouza.bff_agendador_tarefas.infrastructure.exceptions.BusinessException;
import com.felipesouza.bff_agendador_tarefas.infrastructure.exceptions.ConflictException;
import com.felipesouza.bff_agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.felipesouza.bff_agendador_tarefas.infrastructure.exceptions.UnauthorizedException;
import com.felipesouza.bff_agendador_tarefas.infrastructure.exceptions.IllegalArgumentException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

//Essa classe é um tratador de erros do Feign Client.
public class FeignError implements ErrorDecoder {

    //Caso retorne um erro, o Feign executa esse metodo para transformar a resposta de erro em uma exceção personalizada
    @Override
    public Exception decode(String s, Response response) {

        //Captura o corpo da resposta
        String mensagemErro = mensagemErro(response);

        //Verifica o status HTTP e faz o mapeamento
        switch (response.status()) {
            case 409:
                return new ConflictException("Erro: " + mensagemErro);
            case 404:
                return new ResourceNotFoundException("Erro " + mensagemErro);
            case 401:
                return new UnauthorizedException("Erro " + mensagemErro);
            case 400:
                return new IllegalArgumentException("Erro " + mensagemErro);
            default:
                return new BusinessException("Erro " + mensagemErro);
        }
    }

    //Metodo responsavel por ler o corpo da resposta
    private String mensagemErro(Response response) {

        //Transforma o conteúdo do body em String:
        try {
            if (Objects.isNull(response.body())){
                return "";
            }
            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}