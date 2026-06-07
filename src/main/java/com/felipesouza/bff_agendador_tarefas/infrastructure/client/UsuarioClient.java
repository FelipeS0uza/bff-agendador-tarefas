//Camada CLIENT é onde fará a conexão com o servidor de Usuário para fazer as requisições

package com.felipesouza.bff_agendador_tarefas.infrastructure.client;

import com.felipesouza.bff_agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.felipesouza.bff_agendador_tarefas.business.dto.in.LoginDTORequest;
import com.felipesouza.bff_agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.felipesouza.bff_agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.felipesouza.bff_agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.felipesouza.bff_agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.felipesouza.bff_agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import com.felipesouza.bff_agendador_tarefas.business.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/* O OpenFeign é um cliente HTTP para a comunição entre microserviços.
   Ele reduz o código repetitivo e facilita a implementação não precisando escrever a implementação da chamada REST  */

//Anotação para indicar que aqui será feito a comunicação com outro microserviço
//O nome é para nomear a API e a url é aquela que será consumida (Definida como variável no Application.properties)
@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    //O login é feito no MS Usuario, o get retorna os dados do usuario e o token no header.

    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String login(@RequestBody LoginDTORequest loginDTO);

    @DeleteMapping("/{email}")
    void deletarUsuarioPorEmail(@PathVariable String email, @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto, @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto, @RequestParam ("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto, @RequestParam ("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto, @RequestHeader ("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto, @RequestHeader ("Authorization") String token);

    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscarDadosCep(@PathVariable("cep") String cep);

}