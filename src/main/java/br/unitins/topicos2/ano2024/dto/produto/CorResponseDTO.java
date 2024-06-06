package br.unitins.topicos2.ano2024.dto.produto;

import br.unitins.topicos2.ano2024.model.produto.Cor;

public record CorResponseDTO(
    Long id,
    String cor
) {
    public static CorResponseDTO valueOf(Cor cor) {
        return new CorResponseDTO(
            cor.getId(), 
            cor.getCor()
        );
    }
}
