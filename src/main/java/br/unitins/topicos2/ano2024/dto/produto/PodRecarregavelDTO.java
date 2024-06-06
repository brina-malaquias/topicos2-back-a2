package br.unitins.topicos2.ano2024.dto.produto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record PodRecarregavelDTO(
    @NotBlank(message = "O campo nome não pode ser nulo.")
    String nome,
    Double valor,
    String descricao,
    List<Long> listaCor,
    List<Long> listaMarca

) {
    
}
