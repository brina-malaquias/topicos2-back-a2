package br.unitins.topicos2.ano2024.service.usuario;

import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos2.ano2024.dto.endereco.EnderecoDTO;
import br.unitins.topicos2.ano2024.dto.usuario.TelefoneDTO;
import br.unitins.topicos2.ano2024.dto.usuario.UsuarioDTO;
import br.unitins.topicos2.ano2024.dto.usuario.UsuarioResponseDTO;
import br.unitins.topicos2.ano2024.model.endereco.Endereco;
import br.unitins.topicos2.ano2024.model.usuario.Telefone;
import br.unitins.topicos2.ano2024.model.usuario.TipoUsuario;
import br.unitins.topicos2.ano2024.model.usuario.Usuario;
import br.unitins.topicos2.ano2024.repository.usuario.UsuarioRepository;
import br.unitins.topicos2.ano2024.service.hash.HashService;
import br.unitins.topicos2.ano2024.validation.ValidationException;
//import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Inject
    HashService hashService;

    @Override
    @Transactional
    public UsuarioResponseDTO insert(@Valid UsuarioDTO dto) {

       if (repository.findByLogin(dto.email()) != null) {
            throw new ValidationException("email", "Email já existe.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(dto.nome());

        novoUsuario.setCpf(dto.cpf());

        novoUsuario.setEmail(dto.email());

        novoUsuario.setSenha(hashService.getHashSenha(dto.senha()));

        novoUsuario.setTipoUsuario(TipoUsuario.valueOf(dto.idTipoUsuario()));

        if (dto.listaTelefone() != null && 
                    !dto.listaTelefone().isEmpty()){
            novoUsuario.setListaTelefone(new ArrayList<Telefone>());
            for (TelefoneDTO tel : dto.listaTelefone()) {
                Telefone telefone = new Telefone();
                telefone.setCodigoArea(tel.codigoArea());
                telefone.setNumero(tel.numero());
                novoUsuario.getListaTelefone().add(telefone);
            }
        }

        novoUsuario.setEndereco(Endereco.valueOf(dto.endereco()));

        repository.persist(novoUsuario);

        return UsuarioResponseDTO.valueOf(novoUsuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(UsuarioDTO dto, Long id) {
        Usuario usuario = repository.findById(id);
        usuario.setNome(dto.nome());
        usuario.setSenha(hashService.getHashSenha(dto.senha()));

        repository.persist(usuario);
        
        return UsuarioResponseDTO.valueOf(usuario);
    }

    /*@Override
    @Transactional
    public UsuarioResponseDTO alterarSenha(alterarSenhaUsuarioDTO alterarSenhaUsuarioDTO, String login) {
        Usuario usuario = repository.findByLogin(login);
        Log.info("Senha antiga: "+ usuario.getSenha());
        usuario.setSenha(hashService.getHashSenha(alterarSenhaUsuarioDTO.senha()));
        Log.info("Senha nova: "+ usuario.getSenha());
        Log.info("Senha alterada com sucesso!");
        repository.persist(usuario);

        return UsuarioResponseDTO.valueOf(usuario);
    }
    */

    @Override
    @Transactional
    public void delete(Long id) {
        Usuario usuario = repository.findById(id);

        if(usuario != null){
            repository.delete(usuario);
        }else {
            throw new NotFoundException("Usuário não encontrado!");
        }
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
             return null;
    }

    @Override
    public List<UsuarioResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> UsuarioResponseDTO.valueOf(e)).toList();
    }

    @Override
    public UsuarioResponseDTO findByLoginAndSenha(String email, String senha) {
        try {
            Usuario usuario = repository.findByLoginAndSenha(email, senha);
            if (usuario == null) {
                throw new ValidationException("email", "Email ou senha inválido");
            }
            return UsuarioResponseDTO.valueOf(usuario);
        } catch (Exception e) {
            e.printStackTrace(); // Adicione esta linha para imprimir a pilha de exceção no console
            throw new ValidationException("email", "Ocorreu um erro durante a autenticação. Consulte os logs para obter mais informações.");
        }
    }


    @Override
    public UsuarioResponseDTO findByLogin(String email) {
        Usuario usuario = repository.findByLogin(email);
        if (usuario == null) 
            throw new ValidationException("email", "Email inválido");
        
        return UsuarioResponseDTO.valueOf(usuario);
    }
    
}