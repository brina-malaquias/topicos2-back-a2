package br.unitins.topicos2.ano2024.dto.produto;

import jakarta.validation.constraints.NotBlank;

public record PuffDTO(
    @NotBlank(message = "O campo nome não pode ser nulo.")
    String quantidade
) {
    
}