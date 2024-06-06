package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;

import br.unitins.topicos2.ano2024.dto.produto.NicSaltDTO;
import br.unitins.topicos2.ano2024.dto.produto.NicSaltResponseDTO;
import jakarta.validation.Valid;

public interface NicSaltService {

        // recursos basicos
        List<NicSaltResponseDTO> getAll(int page, int pageSize);

        NicSaltResponseDTO findById(Long id);
    
        NicSaltResponseDTO create(@Valid NicSaltDTO dto);
    
        NicSaltResponseDTO update(Long id, NicSaltDTO dto);
    
        void delete(Long id);
    
        // recursos extras
    
        List<NicSaltResponseDTO> findByNome(String nome);
    
        long count();
}