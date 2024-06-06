package br.unitins.topicos2.ano2024.dto.produto;

import br.unitins.topicos2.ano2024.model.produto.Puff;

public record PuffResponseDTO(
    Long id,
    String quantidade
) {
    public static PuffResponseDTO valueOf(Puff puff) {
        return new PuffResponseDTO(
            puff.getId(), 
            puff.getQuantidade()
        );
    }
}