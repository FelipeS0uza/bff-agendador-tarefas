//Essa classe configura o esquema de segurança do Swagger/OpenAPI para documentação da API

package com.felipesouza.bff_agendador_tarefas.infrastructure.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

//Registra globalmente um esquema de autenticação na documentação
//name é o nome do esquema, referenciado em outros pontos da aplicação para exigir autenticação em endpoints específicos
//type define que é autenticação via protocolo HTTP
//scheme especifica que usa o padrão Bearer Token
//bearerFormat indica que o token é no formato JWT
@SecurityScheme(name = SecurityConfig.SECURITY_SCHEME, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SecurityConfig {

    public static final String SECURITY_SCHEME = "bearerAuth";
}