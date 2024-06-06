package br.unitins.topicos2.ano2024.repository.produto;

import br.unitins.topicos2.ano2024.model.produto.Resistencia;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResistenciaRepository implements PanacheRepository<Resistencia> {

    public PanacheQuery<Resistencia> findByNome(String ohms) {
        if (ohms == null)
            return null;
        return find("UPPER(ohms) LIKE ?1 ", "%" + ohms.toUpperCase() + "%");
    }

}
