//Camada DTO - Data Transfer Object
//É um modelo de projeto usado para transportar dados, evitando passar dados sensíveis para outro serviço.

package com.felipesouza.bff_agendador_tarefas.business.dto.out;

import lombok.*;

import java.util.List;

@Getter //Cria todos os getters
@Setter //Cria todos os setters
@AllArgsConstructor //  Cria o construtor recebendo todos os argumentos
@NoArgsConstructor  //Cria o construtor não recebendo nenhum argumento
@Builder    //Simplifica a instância de classes fornecendo os parâmetros na ordem desejada e de uma só vez.
public class UsuarioDTOResponse {

    //Não possui o id pois é uma informação privada, da qual não faz sentido expor.
    private String nome;
    private String email;
    private String senha;
    private List<EnderecoDTOResponse> enderecos;
    private List <TelefoneDTOResponse> telefones;
}