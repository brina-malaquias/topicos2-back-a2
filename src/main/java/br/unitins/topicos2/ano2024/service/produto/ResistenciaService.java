package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;

import br.unitins.topicos2.ano2024.dto.produto.ResistenciaDTO;
import br.unitins.topicos2.ano2024.dto.produto.ResistenciaResponseDTO;
import jakarta.validation.Valid;

public interface ResistenciaService {
    
        // recursos basicos
        List<ResistenciaResponseDTO> getAll(int page, int pageSize);

        ResistenciaResponseDTO findById(Long id);
    
        ResistenciaResponseDTO create(@Valid ResistenciaDTO dto);
    
        ResistenciaResponseDTO update(Long id, ResistenciaDTO dto);
    
        void delete(Long id);
    
        // recursos extras
    
        List<ResistenciaResponseDTO> findByNome(String ohms);
    
        long count();
}
