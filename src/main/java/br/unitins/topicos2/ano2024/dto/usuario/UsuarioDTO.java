package br.unitins.topicos2.ano2024.dto.usuario;

import java.util.List;

import br.unitins.topicos2.ano2024.dto.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
    @NotBlank(message = "O campo nome não pode ser nulo.")
    String nome,
    String idade,
    String email,
    String senha,
    String cpf,
    Integer idTipoUsuario,
    List<TelefoneDTO> listaTelefone,
    EnderecoDTO endereco

) {
    
}
