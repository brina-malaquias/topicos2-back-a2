package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;

import br.unitins.topicos2.ano2024.dto.produto.CorDTO;
import br.unitins.topicos2.ano2024.dto.produto.CorResponseDTO;
import jakarta.validation.Valid;

public interface CorService {

        // recursos basicos
        List<CorResponseDTO> getAll(int page, int pageSize);

        CorResponseDTO findById(Long id);
    
        CorResponseDTO create(@Valid CorDTO dto);
    
        CorResponseDTO update(Long id, CorDTO dto);
    
        void delete(Long id);
    
        // recursos extras
    
        List<CorResponseDTO> findByNome(String cor);
    
        long count();
}
