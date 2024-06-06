package br.unitins.topicos2.ano2024.service.endereco;

import br.unitins.topicos2.ano2024.model.usuario.Usuario;
import br.unitins.topicos2.ano2024.dto.endereco.EnderecoDTO;
import br.unitins.topicos2.ano2024.model.endereco.Endereco;

public interface EnderecoService {
    
    Endereco insertEndereco(EnderecoDTO enderecoDto);

    void updateEndereco(Usuario usuario, EnderecoDTO enderecoDTO);
}
