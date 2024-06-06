package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;

import br.unitins.topicos2.ano2024.dto.produto.PodRecarregavelDTO;
import br.unitins.topicos2.ano2024.dto.produto.PodRecarregavelResponseDTO;
import jakarta.validation.Valid;

public interface PodRecarregavelService {

        // recursos basicos
        List<PodRecarregavelResponseDTO> getAll(int page, int pageSize);

        PodRecarregavelResponseDTO findById(Long id);
    
        PodRecarregavelResponseDTO create(@Valid PodRecarregavelDTO dto);
    
        PodRecarregavelResponseDTO update(Long id, PodRecarregavelDTO dto);
    
        void delete(Long id);
    
        // recursos extras
    
        List<PodRecarregavelResponseDTO> findByNome(String nome);
    
        long count();
}