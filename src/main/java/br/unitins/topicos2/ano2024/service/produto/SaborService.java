package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;

import br.unitins.topicos2.ano2024.dto.produto.SaborDTO;
import br.unitins.topicos2.ano2024.dto.produto.SaborResponseDTO;
import jakarta.validation.Valid;

public interface SaborService {
    
        // recursos basicos
        List<SaborResponseDTO> getAll(int page, int pageSize);

        SaborResponseDTO findById(Long id);
    
        SaborResponseDTO create(@Valid SaborDTO dto);
    
        SaborResponseDTO update(Long id, SaborDTO dto);
    
        void delete(Long id);
    
        // recursos extras
    
        List<SaborResponseDTO> findByNome(String sabor);
    
        long count();
}
