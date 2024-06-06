package br.unitins.topicos2.ano2024.repository.usuario;

import java.util.List;

import br.unitins.topicos2.ano2024.model.usuario.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario>{
    public List<Usuario> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1) ", "%"+nome+"%").list();
    }

    public Usuario findByLogin(String email) {
        try {
            return find("email = ?1 ", email ).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario findByLoginAndSenha(String email, String senha) {
        try {
            return find("email = ?1 AND senha = ?2 ", email, senha).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}