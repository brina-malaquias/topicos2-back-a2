package br.unitins.topicos2.ano2024.repository.pedido;

import java.util.List;

import br.unitins.topicos2.ano2024.model.pedido.Pedido;
import br.unitins.topicos2.ano2024.model.usuario.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public List<Pedido> findByUsuarioWhereIsFinished (Usuario usuario) {

        if (usuario == null)
            return null;

            return find("FROM Pedido WHERE usuario = ?1 AND ifConcluida = true", usuario).list();
    }

    public Pedido findByUsuarioWhereIsNotFinished (Usuario usuario) {

        if (usuario == null)
            return null;

        return find("FROM Pedido WHERE usuario = ?1 AND ifConcluida = false", usuario).firstResult();
    }
}
