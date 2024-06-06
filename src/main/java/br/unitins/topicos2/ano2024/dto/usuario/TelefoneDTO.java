package br.unitins.topicos2.ano2024.dto.usuario;

import br.unitins.topicos2.ano2024.model.usuario.Telefone;

public record TelefoneDTO(
    String codigoArea,
    String numero
) {
    public static TelefoneDTO valueOf(Telefone telefone){
        return new TelefoneDTO(
            telefone.getCodigoArea(), 
            telefone.getNumero()
        );
    }
}