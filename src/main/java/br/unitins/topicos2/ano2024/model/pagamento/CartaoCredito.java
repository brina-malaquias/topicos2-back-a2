package br.unitins.topicos2.ano2024.model.pagamento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class CartaoCredito extends Pagamento {

    @Column(nullable = false)
    private String numeroCartao;

    @Column(nullable = false)
    private String nomeTitular;
    
    private BandeiraCartao bandeiraCartao;

    public CartaoCredito (Double valor, String numeroCartao, String nomeTitular,
                             BandeiraCartao bandeiraCartao) {

        super(valor);

        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.bandeiraCartao = bandeiraCartao;
    }

    public CartaoCredito(){}


    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public BandeiraCartao getBandeiraCartao() {
        return bandeiraCartao;
    }
    public void setBandeiraCartao(BandeiraCartao bandeiraCartao) {
        this.bandeiraCartao = bandeiraCartao;
    }
}