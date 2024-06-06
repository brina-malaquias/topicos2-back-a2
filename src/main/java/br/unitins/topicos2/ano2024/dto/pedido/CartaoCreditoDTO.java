package br.unitins.topicos2.ano2024.dto.pedido;

import java.time.LocalDate;

public record CartaoCreditoDTO(
    String numeroCartao,
    String nomeTitular,
    LocalDate dataValidade,
    String codigoSeguranca,
    Integer bandeiraCartao
) {
    
}
