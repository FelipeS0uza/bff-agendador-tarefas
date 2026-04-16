//Camada CONTROLLER recebe as requisições HTTP, mapeia os endpoints, chama a Service para usar os metodos
// e retorna respostas HTTP conforme o necessário.
// Não deve conter lógica de negócio (cálculos, validações complexas, regras de banco)
//Documentado com SWAGGER

package com.felipesouza.bff_agendador_tarefas.controller;

import com.felipesouza.bff_agendador_tarefas.business.TarefasService;
import com.felipesouza.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.felipesouza.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.felipesouza.bff_agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.felipesouza.bff_agendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController  //Indica para o spring que essa classe é o controlador e que vai lidar com as requisições (Padrão REST)
@RequestMapping("/tarefas")     //Responsável por apontar qual é a URI da controller
@RequiredArgsConstructor        //Gera um construtor que inicializa apenas os campos PRIVATE FINAL
@Tag(name="Tarefas", description = "Cadastro de tarefas de usuários")      //Indica o nome da controller e descrição
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME) //Marca a controller inteira como protegida, exigindo o token na documentação.
public class TarefasController {

    //Injeção de dependências
    private final TarefasService tarefasService;

    //ResponseEntity<> é uma classe que indica que o metodo vai retornar uma resposta HTTP do tipo que estiver dentro de <>
    //RequestBody indica que estou passando um objeto no corpo da requisição
    //RequestHeader indica que receberá no Header, na chave Authorization o valor do token
    //RequestParam indica que estou passando um parametro para extrair no corpo da url
    //@Operation indica o nome e descrição da requisição
    //@ApiResponse indica o código HTTP a ser retornado e a descrição
    //@RequestHeader(required = false) indica que no Swagger não é obrigatório passar o token em todas as requisições

    @PostMapping    //Indica que o metodo é um POST
    @Operation(summary = "Salvar Tarefas de Usuários", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {
        //Caso esteja tudo ok, então a tarefa é salva no banco de dados com o email do usuario autenticado
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")     //Inidica que o metodo é um GET na url /eventos
    @Operation(summary = "Busca Tarefas por Período", description = "Busca Tarefas cadastradas por período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscarListaDeTarefasPorPeriodo(
            //DateTimeFormat faz a formatação da data informada para o formato ISO, que é o aceito no banco de dados
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token) {

        //É feito a busca das tarefas no periodo e caso esteja tudo ok retorna a lista com todas elas
        return ResponseEntity.ok(tarefasService.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping     //Inidica que o metodo é um GET
    @Operation(summary = "Busca Tarefas por email de Usuário", description = "Busca Tarefas por email de Usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token) {
        //É feito a busca das tarefas pelo email extraido do token e caso esteja tudo ok retorna a lista com todas elas
        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping      //Indica que o metodo é um DELETE
    @Operation(summary = "Deleta Tarefas por Id", description = "Deleta Tarefas cadastradas por id")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token) {
        //Chama o metodo de deletar a tarefa
        tarefasService.deletaTarefaPorId(id, token);

        //Caso esteja tudo ok é deletado no banco de dados
        return ResponseEntity.ok().build();
    }

    @PatchMapping   //Indica que o metodo é um PATCH
    @Operation(summary = "Altera Status da Tarefa", description = "Altera Status da Tarefa cadastrada por id")
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                      @RequestParam("id") String id,
                                                                      @RequestHeader(name = "Authorization", required = false) String token) {
        //É feito a alteração do status e caso esteja tudo ok, é salvo no bando de dados
        return ResponseEntity.ok(tarefasService.alteracaoStatus(status, id, token));
    }

    @PutMapping     //Indica que o metodo é um PUT
    @Operation(summary = "Altera dados da Tarefa", description = "Altera dados da Tarefa cadastrada por id")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(name = "Authorization", required = false) String token) {
        //É feito a alteração dos dados e caso esteja tudo ok, é salvo no bando de dados
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));
    }
}