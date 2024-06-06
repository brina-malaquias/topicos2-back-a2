package br.unitins.topicos2.ano2024.service.pedido;

import br.unitins.topicos2.ano2024.model.pedido.ItemPedido;
import br.unitins.topicos2.ano2024.model.produto.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemPedidoRepository implements PanacheRepository<ItemPedido> {
    
    public ItemPedido findByProduto (Produto produto) {

        if (produto == null)
            return null;

        return find("FROM ItemPedido WHERE produto = ?1", produto).firstResult();
    }
}
