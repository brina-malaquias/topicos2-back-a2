package br.unitins.topicos2.ano2024.dto.produto;

import br.unitins.topicos2.ano2024.model.produto.PodRecarregavel;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record PodRecarregavelResponseDTO(
    Long id,
    String nomeImagem,
    String nome,
    Double valor,
    String descricao,
    List<CorDTO> listaCor,
    List<MarcaDTO> listaMarca
) {
    public static PodRecarregavelResponseDTO valueOf(PodRecarregavel podRecarregavel) {
        return new PodRecarregavelResponseDTO(
            podRecarregavel.getId(),
            podRecarregavel.getNomeImagem(),
            podRecarregavel.getNome(),
            podRecarregavel.getValor(),
            podRecarregavel.getDescricao(),
            Optional.ofNullable(podRecarregavel.getListaCor())
                .map(corList -> corList.stream()
                    .map(cor -> new CorDTO(cor.getCor()))
                    .collect(Collectors.toList())
                ).orElse(null),
            Optional.ofNullable(podRecarregavel.getListaMarca())
                .map(marcaList -> marcaList.stream()
                    .map(marca -> new MarcaDTO(marca.getNome(),marca.getDescricao()))
                    .collect(Collectors.toList())
                ).orElse(null)
        );
    }
}