package br.unitins.topicos2.ano2024.dto.usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import br.unitins.topicos2.ano2024.model.usuario.TipoUsuario;
import br.unitins.topicos2.ano2024.model.usuario.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String idade,
    String email,
    String senha,
    String cpf,
    TipoUsuario tipoUsuario,
    List<TelefoneDTO> listaTelefone,
    Map<String, Object> endereco
) {
    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
            usuario.getId(), 
            usuario.getNome(),
            usuario.getIdade(),
            usuario.getEmail(),
            usuario.getSenha(),
            usuario.getCpf(),
            usuario.getTipoUsuario(),
            Optional.ofNullable(usuario.getListaTelefone())
                    .map(telefones -> telefones.stream().map(TelefoneDTO::valueOf).toList())
                    .orElse(null),
            viewEndereco(usuario.getEndereco().getCep(),
                    usuario.getEndereco().getBairro(), 
                    usuario.getEndereco().getLogradouro(),
                    usuario.getEndereco().getNumero(), 
                    usuario.getEndereco().getComplemento())
        );
    }

    public static Map<String, Object> viewEndereco (String cep, String bairro, String logradouro, String numero, String complemento) {
        Map<String, Object> endereco = new HashMap<>();
        endereco.put("cep", cep);
        endereco.put("bairro", bairro);
        endereco.put("logradouro", logradouro);
        endereco.put("numero", numero);
        endereco.put("complemento", complemento);
        return endereco;
    }

}