package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;

import br.unitins.topicos2.ano2024.dto.produto.MarcaDTO;
import br.unitins.topicos2.ano2024.dto.produto.MarcaResponseDTO;
import jakarta.validation.Valid;

public interface MarcaService {

        // recursos basicos
        List<MarcaResponseDTO> getAll(int page, int pageSize);

        MarcaResponseDTO findById(Long id);
    
        MarcaResponseDTO create(@Valid MarcaDTO dto);
    
        MarcaResponseDTO update(Long id, MarcaDTO dto);
    
        void delete(Long id);
    
        // recursos extras
    
        List<MarcaResponseDTO> findByNome(String nome);
    
        long count();

}