//Camada SERVICE DO BFF é onde recebe as variáveis e envia para a camada client fazer as requisições e pegar o retorno
//Não possui regras de negócio

package com.felipesouza.bff_agendador_tarefas.business;

import com.felipesouza.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.felipesouza.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.felipesouza.bff_agendador_tarefas.infrastructure.client.TarefasClient;
import com.felipesouza.bff_agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service    //Indica ao spring que é uma Service
@RequiredArgsConstructor    //Gera um construtor que inicializa apenas os campos PRIVATE FINAL
public class TarefasService {

    //Injeção de dependências
    private final TarefasClient client;

    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto) {
        return client.gravarTarefas(dto, token);
    }

    public List<TarefasDTOResponse> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                     LocalDateTime dataFinal,
                                                                     String token) {
        return client.buscarListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token) {
        return client.buscaTarefasPorEmail(token);
    }

    public void deletaTarefaPorId(String id, String token) {
        client.deletaTarefaPorId(id, token);
    }

    public TarefasDTOResponse alteracaoStatus(StatusNotificacaoEnum status, String id, String token) {
        return client.alteraStatusNotificacao(status, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token) {
        return client.updateTarefas(dto, id, token);
    }
}