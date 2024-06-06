package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;

import br.unitins.topicos2.ano2024.dto.produto.CoilDTO;
import br.unitins.topicos2.ano2024.dto.produto.CoilResponseDTO;
import jakarta.validation.Valid;

public interface CoilService {
    
    // recursos basicos
    List<CoilResponseDTO> getAll(int page, int pageSize);

    CoilResponseDTO findById(Long id);

    CoilResponseDTO create(@Valid CoilDTO dto);

    CoilResponseDTO update(Long id, CoilDTO dto);

    void delete(Long id);

    // recursos extras

    List<CoilResponseDTO> findByNome(String nome);

    long count();
}