package br.unitins.topicos2.ano2024.repository.produto;

import br.unitins.topicos2.ano2024.model.produto.Coil;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CoilRepository implements PanacheRepository<Coil>{
    
    public PanacheQuery<Coil> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }
}