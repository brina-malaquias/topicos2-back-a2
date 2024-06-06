package br.unitins.topicos2.ano2024.repository.produto;

import br.unitins.topicos2.ano2024.model.produto.Puff;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PuffRepository implements PanacheRepository<Puff> {

    public PanacheQuery<Puff> findByNome(String quantidade) {
        if (quantidade == null)
            return null;
        return find("UPPER(quantidade) LIKE ?1 ", "%" + quantidade.toUpperCase() + "%");
    }

}
