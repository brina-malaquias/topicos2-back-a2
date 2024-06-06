package br.unitins.topicos2.ano2024.dto.produto;

import br.unitins.topicos2.ano2024.model.produto.Sabor;

public record SaborResponseDTO(
    Long id,
    String sabor
) {
    public static SaborResponseDTO valueOf(Sabor sabor) {
        return new SaborResponseDTO(
            sabor.getId(), 
            sabor.getSabor()
        );
    }
}