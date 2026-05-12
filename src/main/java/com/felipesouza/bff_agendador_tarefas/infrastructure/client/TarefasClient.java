//Camada CLIENT é onde fará a conexão com o servidor de Tarefas para fazer as requisições

package com.felipesouza.bff_agendador_tarefas.infrastructure.client;

import com.felipesouza.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.felipesouza.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.felipesouza.bff_agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

/* O OpenFeign é um cliente HTTP para a comunição entre microserviços.
   Ele reduz o código repetitivo e facilita a implementação não precisando escrever a implementação da chamada REST  */

//Anotação para indicar que aqui será feito a comunicação com outro microserviço
//O nome é para nomear a API e a url é aquela que será consumida (Definida como variável no Application.properties)
@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {


    @PostMapping
    TarefasDTOResponse gravarTarefas(@RequestBody TarefasDTORequest dto, @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscarListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefasDTOResponse> buscaTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    void deletaTarefaPorId(@RequestParam("id") String id, @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasDTOResponse alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                               @RequestParam("id") String id,
                                               @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTOResponse updateTarefas(@RequestBody TarefasDTORequest dto, @RequestParam("id") String id,
                                     @RequestHeader("Authorization") String token);
}