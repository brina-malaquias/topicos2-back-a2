package br.unitins.topicos2.ano2024.model.pedido;

import br.unitins.topicos2.ano2024.model.DefaultEntity;
import br.unitins.topicos2.ano2024.model.produto.Produto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido extends DefaultEntity {

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double subTotal;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    public ItemPedido (Produto produto, Integer quantidade) {

        this.produto = produto;
        this.subTotal = produto.getValor();
        this.quantidade = quantidade;
    }

    public ItemPedido() {
        
    }

    public boolean contains(Produto produto) {

        if (this.produto.getId() == produto.getId())
            return true;
        
        else
            return false;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void updateQuantidade(Integer quantidade) {

        this.quantidade += quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double valor) {
        this.subTotal = valor;
    }

}
