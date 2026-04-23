//Camada CLIENT é onde fará a conexão com o servidor de Email para fazer as requisições

package com.felipesouza.bff_agendador_tarefas.infrastructure.client;

import com.felipesouza.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/* O OpenFeign é um cliente HTTP para a comunição entre microserviços.
   Ele reduz o código repetitivo e facilita a implementação não precisando escrever a implementação da chamada REST  */

//Anotação para indicar que aqui será feito a comunicação com outro microserviço
//O nome é para nomear a API e a url é aquela que será consumida (Definida como variável no Application.properties)
@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    @PostMapping
    void enviarEmail(@RequestBody TarefasDTOResponse dto);
}