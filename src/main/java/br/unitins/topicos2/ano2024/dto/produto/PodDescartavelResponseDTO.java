package br.unitins.topicos2.ano2024.dto.produto;

import br.unitins.topicos2.ano2024.model.produto.PodDescartavel;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record PodDescartavelResponseDTO(
    Long id,
    String nomeImagem,
    String nome,
    Double valor,
    String descricao,
    List<SaborDTO> listaSabor,
    List<PuffDTO> listaPuff,
    List<MarcaDTO> listaMarca
) {
    public static PodDescartavelResponseDTO valueOf(PodDescartavel podDescartavel) {
        return new PodDescartavelResponseDTO(
            podDescartavel.getId(),
            podDescartavel.getNomeImagem(),
            podDescartavel.getNome(),
            podDescartavel.getValor(),
            podDescartavel.getDescricao(),
            Optional.ofNullable(podDescartavel.getListaSabor())
                .map(saborList -> saborList.stream()
                    .map(sabor -> new SaborDTO(sabor.getSabor()))
                    .collect(Collectors.toList())
                ).orElse(null),
            Optional.ofNullable(podDescartavel.getListaPuff())
                .map(puffList -> puffList.stream()
                    .map(puff -> new PuffDTO(puff.getQuantidade()))
                    .collect(Collectors.toList())
                ).orElse(null),
            Optional.ofNullable(podDescartavel.getListaMarca())
                .map(marcaList -> marcaList.stream()
                    .map(marca -> new MarcaDTO(marca.getNome(),marca.getDescricao()))
                    .collect(Collectors.toList())
                ).orElse(null)
        );
    }
}