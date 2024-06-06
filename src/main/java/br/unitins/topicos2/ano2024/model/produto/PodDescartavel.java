package br.unitins.topicos2.ano2024.model.produto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class PodDescartavel extends Produto{
    
    private String nomeImagem;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "podDescartavel_sabor", 
                joinColumns = @JoinColumn(name = "id_podDescartavel"), 
                inverseJoinColumns = @JoinColumn(name = "id_sabor"))
    private List<Sabor> listaSabor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "podDescartavel_puff", 
                joinColumns = @JoinColumn(name = "id_podDescartavel"), 
                inverseJoinColumns = @JoinColumn(name = "id_puff"))
    private List<Puff> listaPuff;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "podDescartavel_marca", 
                joinColumns = @JoinColumn(name = "id_podDescartavel"), 
                inverseJoinColumns = @JoinColumn(name = "id_marca"))
    private List<Marca> listaMarca;

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public List<Sabor> getListaSabor() {
        return listaSabor;
    }

    public void setListaSabor(List<Sabor> listaSabor) {
        this.listaSabor = listaSabor;
    }

    public List<Puff> getListaPuff() {
        return listaPuff;
    }

    public void setListaPuff(List<Puff> listaPuff) {
        this.listaPuff = listaPuff;
    }

    public List<Marca> getListaMarca() {
        return listaMarca;
    }

    public void setListaMarca(List<Marca> listaMarca) {
        this.listaMarca = listaMarca;
    }    
}