////Camada SERVICE DO BFF é onde recebe as variáveis e envia para a camada client fazer as requisições e pegar o retorno
////Não possui regras de negócio
//
//package com.felipesouza.bff_agendador_tarefas.business;
//
//import com.felipesouza.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
//import com.felipesouza.bff_agendador_tarefas.infrastructure.client.EmailClient;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service    //Indica ao spring que é uma Service
//@RequiredArgsConstructor    //Gera um construtor que inicializa apenas os campos PRIVATE FINAL
//public class EmailService {
//
//    //Injeção de dependências
//    private final EmailClient client;
//
//    public void enviaEmail(TarefasDTOResponse dto) {
//        client.enviarEmail(dto);
//    }
//}