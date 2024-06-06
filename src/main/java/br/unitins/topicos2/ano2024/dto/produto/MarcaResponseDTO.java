package br.unitins.topicos2.ano2024.dto.produto;

import br.unitins.topicos2.ano2024.model.produto.Marca;

public record MarcaResponseDTO (
    Long id,
    String nome,
    String descricao
) {

    public static MarcaResponseDTO valueOf(Marca marca) {
        return new MarcaResponseDTO(
            marca.getId(), 
            marca.getNome(),
            marca.getDescricao()
        );
    }

}