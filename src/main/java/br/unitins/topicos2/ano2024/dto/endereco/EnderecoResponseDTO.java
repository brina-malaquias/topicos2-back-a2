package br.unitins.topicos2.ano2024.dto.endereco;

import br.unitins.topicos2.ano2024.model.endereco.Endereco;


public record EnderecoResponseDTO(
    String logradouro,
    String bairro,
    String numero,
    String complemento,
    String cep
) {
    
    public EnderecoResponseDTO (Endereco endereco) {

        this(endereco.getLogradouro(),
            endereco.getBairro(),
            endereco.getNumero(),
            endereco.getComplemento(),
            endereco.getCep());
        }
}
