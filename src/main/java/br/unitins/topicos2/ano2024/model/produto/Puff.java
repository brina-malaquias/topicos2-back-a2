package br.unitins.topicos2.ano2024.model.produto;

import br.unitins.topicos2.ano2024.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Puff extends DefaultEntity {
    
    @Column(nullable = false)
    private String quantidade;

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

}
