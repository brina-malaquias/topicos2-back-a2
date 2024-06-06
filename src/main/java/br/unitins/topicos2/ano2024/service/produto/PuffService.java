package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;

import br.unitins.topicos2.ano2024.dto.produto.PuffDTO;
import br.unitins.topicos2.ano2024.dto.produto.PuffResponseDTO;
import jakarta.validation.Valid;

public interface PuffService {

        // recursos basicos
        List<PuffResponseDTO> getAll(int page, int pageSize);

        PuffResponseDTO findById(Long id);
    
        PuffResponseDTO create(@Valid PuffDTO dto);
    
        PuffResponseDTO update(Long id, PuffDTO dto);
    
        void delete(Long id);
    
        // recursos extras
    
        List<PuffResponseDTO> findByNome(String quantidade);
    
        long count();
}