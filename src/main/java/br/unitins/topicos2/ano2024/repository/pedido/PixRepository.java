package br.unitins.topicos2.ano2024.repository.pedido;

import br.unitins.topicos2.ano2024.model.pagamento.Pix;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PixRepository implements PanacheRepository<Pix> {

}
