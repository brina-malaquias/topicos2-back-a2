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
public class PodRecarregavel extends Produto{
    
    private String nomeImagem;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "podRecarregavel_cor", 
                joinColumns = @JoinColumn(name = "id_podRecarregavel"), 
                inverseJoinColumns = @JoinColumn(name = "id_cor"))
    private List<Cor> listaCor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "podRecarregavel_marca", 
                joinColumns = @JoinColumn(name = "id_podRecarregavel"), 
                inverseJoinColumns = @JoinColumn(name = "id_marca"))
    private List<Marca> listaMarca;

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public List<Cor> getListaCor() {
        return listaCor;
    }

    public void setListaCor(List<Cor> listaCor) {
        this.listaCor = listaCor;
    }

    public List<Marca> getListaMarca() {
        return listaMarca;
    }

    public void setListaMarca(List<Marca> listaMarca) {
        this.listaMarca = listaMarca;
    }

    
}
