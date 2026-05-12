package com.felipesouza.bff_agendador_tarefas.infrastructure.exceptions;


//Extende a RunTimeException pois é com ela que criamos exceções não verificadas para tratarmos de acordo com nossa lógica
public class UnauthorizedException extends RuntimeException {

    //Recebe uma mensagem
    public UnauthorizedException(String mensagem) {
        super(mensagem);
    }

    //Recebe uma mensagem e um Throwable
    public UnauthorizedException(String mensagem, Throwable throwable) {
        super(mensagem, throwable);
    }
}