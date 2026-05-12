//CRON é um agendador de tarefas que executa comandos baseado no tempo.
//Aqui será configurado para buscar no agengador de tarefas a cada 5 minutos as tarefas agendadas para daqui 1 hora.
//Para que o usuário seja notificado com 1 hora de antecedência.

package com.felipesouza.bff_agendador_tarefas.business;

import com.felipesouza.bff_agendador_tarefas.business.dto.in.LoginDTORequest;
import com.felipesouza.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.felipesouza.bff_agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service    //Indica ao spring que é uma Service
@RequiredArgsConstructor    //Gera um construtor que inicializa apenas os campos PRIVATE FINAL
@Slf4j      //Indica a instancia de um logger para colocar logs no código de forma uniforme e padronizado
public class CronService {

    //Injeção de dependências
    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    //Anotação para indicar que a variavel será definida no application.properties
    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    //Indica que o metodo será agendado
    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasDaProximaHora() {

        //Faz a requisição do token para usar o metodo buscarTarefasAgendadasPorPeriodo
        String token = login(converterParaRequestDTO());
        log.info("Iniciada a busca de tarefas");

        //Pega a hora atual e adiciona 1 hora
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);

        //Pega a hora atual e adiciona 1 hora e 5 minutos
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);
        log.info("Buscando tarefas agendadas entre " + horaFutura + " e " + horaFuturaMaisCinco);

        //Busca todas as tarefas agendadas para daqui 1 hora e retorna uma lista com todas elas
        List<TarefasDTOResponse> listaTarefas = tarefasService.buscarTarefasAgendadasPorPeriodo(horaFutura, horaFuturaMaisCinco, token);
        log.info("Tarefas encontradas: " + listaTarefas);

        //Passa por cada tarefa e envia o email para o respectivo usuário e altera o status para NOTIFICADO
        listaTarefas.forEach(tarefa -> {emailService.enviaEmail(tarefa);
            log.info("Email enviado para o usuario " + tarefa.getEmailUsuario());
        tarefasService.alteracaoStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(), token);});

        log.info("Finalizada a busca e notificação de tarefas");
    }

    //Metodo para fazer a requisição de login
    public String login(LoginDTORequest dto) {
        return usuarioService.loginUsuario(dto);
    }

    //Metodo para fazer a construção do dto passando email e senha estáticos de admin setados no application
    public LoginDTORequest converterParaRequestDTO() {
        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}