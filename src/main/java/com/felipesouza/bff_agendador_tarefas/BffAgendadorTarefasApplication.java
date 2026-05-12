package com.felipesouza.bff_agendador_tarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication	//Indica que é uma aplicação do spring e faz as configurações necessárias
@EnableFeignClients		//Habilita o uso do FeignClient para usar os metodos de requisições HTTP de outros microserviços
@EnableScheduling		//Habilita o uso do Scheduled para usar o agendamento de tarefas
public class BffAgendadorTarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffAgendadorTarefasApplication.class, args);
	}

}

//Após iniciar os servidores, usar a seguinte url: http://localhost:8083/swagger-ui/index.html