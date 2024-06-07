package br.unitins.topicos2.ano2024.repository.pedido;

import br.unitins.topicos2.ano2024.model.pagamento.CartaoCredito;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CartaoCreditoRepository implements PanacheRepository<CartaoCredito> {

}
