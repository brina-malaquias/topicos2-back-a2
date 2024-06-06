package br.unitins.topicos2.ano2024.service.usuario;

import java.util.List;

import br.unitins.topicos2.ano2024.dto.usuario.UsuarioDTO;
import br.unitins.topicos2.ano2024.dto.usuario.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface UsuarioService {

    public UsuarioResponseDTO insert(@Valid UsuarioDTO dto);

    public UsuarioResponseDTO update(UsuarioDTO dto, Long id);
    
    //public UsuarioResponseDTO alterarSenha(alterarSenhaUsuarioDTO alterarSenhaUsuarioDTO, String login);

    public void delete(Long id);
    
    public UsuarioResponseDTO findById(Long id);

    public List<UsuarioResponseDTO> findByNome(String nome);

    public UsuarioResponseDTO findByLoginAndSenha(String email, String senha);

    public UsuarioResponseDTO findByLogin(String email);

    public List<UsuarioResponseDTO> findByAll(); 
    
}