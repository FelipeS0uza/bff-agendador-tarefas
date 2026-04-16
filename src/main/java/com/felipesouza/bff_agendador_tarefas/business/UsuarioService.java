//Camada SERVICE DO BFF é onde recebe as variáveis e envia para a camada client fazer as requisições e pegar o retorno
//Não possui regras de negócio

package com.felipesouza.bff_agendador_tarefas.business;

import com.felipesouza.bff_agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.felipesouza.bff_agendador_tarefas.business.dto.in.LoginDTORequest;
import com.felipesouza.bff_agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.felipesouza.bff_agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.felipesouza.bff_agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.felipesouza.bff_agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.felipesouza.bff_agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import com.felipesouza.bff_agendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service    //Indica ao spring que é uma Service
@RequiredArgsConstructor    //Gera um construtor que inicializa apenas os campos PRIVATE FINAL
public class UsuarioService {

    //Injeção de dependências
    private final UsuarioClient client;


    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO) {
        return client.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginDTORequest loginDTO) {
        return client.login(loginDTO);
    }

    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token) {
        return client.buscaUsuarioPorEmail(email, token);
    }

    public void deletarUsuarioPorEmail(String email, String token) {
        client.deletarUsuarioPorEmail(email, token);
    }

    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest dto) {
        return client.atualizaDadosUsuario(dto, token);
    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token) {
        return client.atualizaEndereco(enderecoDTO, idEndereco, token);
    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token) {
        return client.atualizaTelefone(telefoneDTO, idTelefone, token);
    }

    public EnderecoDTOResponse cadastroEndereco(String token, EnderecoDTORequest dto) {
        return client.cadastraEndereco(dto, token);
    }

    public TelefoneDTOResponse cadastroTelefone(String token, TelefoneDTORequest dto) {
        return client.cadastraTelefone(dto, token);
    }
}