package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;

import br.unitins.topicos2.ano2024.dto.produto.PodDescartavelDTO;
import br.unitins.topicos2.ano2024.dto.produto.PodDescartavelResponseDTO;
import jakarta.validation.Valid;

public interface PodDescartavelService {

        // recursos basicos
        List<PodDescartavelResponseDTO> getAll(int page, int pageSize);

        PodDescartavelResponseDTO findById(Long id);
    
        PodDescartavelResponseDTO create(@Valid PodDescartavelDTO dto);
    
        PodDescartavelResponseDTO update(Long id, PodDescartavelDTO dto);
    
        void delete(Long id);
    
        // recursos extras
    
        List<PodDescartavelResponseDTO> findByNome(String nome);
    
        long count();
}