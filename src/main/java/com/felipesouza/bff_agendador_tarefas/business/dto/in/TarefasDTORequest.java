//Camada DTO - Data Transfer Object
//É um modelo de projeto usado para transportar dados, evitando passar dados sensíveis para outro serviço.

package com.felipesouza.bff_agendador_tarefas.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felipesouza.bff_agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter //Cria todos os getters
@Setter //Cria todos os setters
@AllArgsConstructor //  Cria o construtor recebendo todos os argumentos
@NoArgsConstructor  //Cria o construtor não recebendo nenhum argumento
@Builder    //Simplifica a instância de classes fornecendo os parâmetros na ordem desejada e de uma só vez.
public class TarefasDTORequest {

    private String nomeTarefa;
    private String descricao;

    //JsonFormat faz a serialização da data e hora, assim fazendo a conversão para o formato que o BD compreenda
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAgendamento;
}