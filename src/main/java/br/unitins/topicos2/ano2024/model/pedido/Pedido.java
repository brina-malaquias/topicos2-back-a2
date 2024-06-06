package br.unitins.topicos2.ano2024.model.pedido;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.unitins.topicos2.ano2024.model.DefaultEntity;
import br.unitins.topicos2.ano2024.model.endereco.Endereco;
import br.unitins.topicos2.ano2024.model.pagamento.Pagamento;
import br.unitins.topicos2.ano2024.model.usuario.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public class Pedido extends DefaultEntity {

    private LocalDate dataCompra;

    private Double valorTotal;

    private Boolean ifConcluida;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @OneToOne
    @JoinColumn (name = "id_pagamento", unique = true)
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @OneToMany
    @JoinColumn(name = "id_pedido")
    private List<ItemPedido> itemPedido;

    public Pedido (Usuario usuario) {

        this.ifConcluida = false;
        this.usuario = usuario;
        this.itemPedido = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public Pedido() {
        
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataDaCompra) {
        this.dataCompra = dataDaCompra;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void plusvalorTotal(Double valorTotal) {

        this.valorTotal += valorTotal;
    }

    public void minusValorTotal(Double valorTotal) {

        this.valorTotal -= valorTotal;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pagamento getPagamento() {
         return pagamento;
     }

    public void setPagamento(Pagamento pagamento) {
         this.pagamento = pagamento;
     }

    public Usuario getUsuario(){
        return usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    public List<ItemPedido> getItemPedido(){
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido.add(itemPedido);
    }

    public Boolean getIfConcluida() {
        return ifConcluida;
    }

    public void setIfConcluida(Boolean ifConcluida) {
        this.ifConcluida = ifConcluida;
    }
}