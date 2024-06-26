package br.unitins.topicos2.ano2024.model.pagamento;

import java.time.LocalDate;

import br.unitins.topicos2.ano2024.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento extends DefaultEntity {

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private Boolean confirmacaoPagamento;

    private LocalDate dataConfirmacaoPagamento;

    public Pagamento (Double valor) {

        this.valor = valor;
        this.confirmacaoPagamento = true;
        this.dataConfirmacaoPagamento = LocalDate.now();
    }

    public Pagamento () {

        this.confirmacaoPagamento = false;
    }

    public Boolean getConfirmacaoPagamento() {
        return confirmacaoPagamento;
    }

    public void setConfirmacaoPagamento(Boolean confirmacaoPagamento) {
        this.confirmacaoPagamento = confirmacaoPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDate getDataConfirmacaoPagamento() {
        return dataConfirmacaoPagamento;
    }

    public void setDataConfirmacaoPagamento(LocalDate dataConfirmacaoPagamento) {
        this.dataConfirmacaoPagamento = dataConfirmacaoPagamento;
    }

}