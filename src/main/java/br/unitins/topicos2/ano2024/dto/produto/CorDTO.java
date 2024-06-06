package br.unitins.topicos2.ano2024.dto.produto;


//import br.unitins.topicos2.ano2024.model.produto.Cor;
import jakarta.validation.constraints.NotBlank;

public record CorDTO(
    @NotBlank(message = "O campo nome não pode ser nulo.")
    String cor
) {
    /*
        public static CorDTO valueOf(Cor cor){
        return new CorDTO(
            cor.getCor()
        );
    }
    */
}