package br.unitins.topicos2.ano2024.model.produto;

import br.unitins.topicos2.ano2024.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Resistencia extends DefaultEntity {
    
    @Column(nullable = false)
    private String ohms;

    public String getOhms() {
        return ohms;
    }

    public void setOhms(String ohms) {
        this.ohms = ohms;
    }
}
