package br.unitins.topicos2.ano2024.dto.produto;

import br.unitins.topicos2.ano2024.model.produto.Resistencia;

public record ResistenciaResponseDTO(
    Long id,
    String ohms
) {
    public static ResistenciaResponseDTO valueOf(Resistencia resistencia) {
        return new ResistenciaResponseDTO(
            resistencia.getId(), 
            resistencia.getOhms()
        );
    }

}
