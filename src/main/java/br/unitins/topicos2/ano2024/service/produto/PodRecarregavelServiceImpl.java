package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.ano2024.dto.produto.PodRecarregavelDTO;
import br.unitins.topicos2.ano2024.dto.produto.PodRecarregavelResponseDTO;
import br.unitins.topicos2.ano2024.model.produto.PodRecarregavel;
import br.unitins.topicos2.ano2024.repository.produto.CorRepository;
import br.unitins.topicos2.ano2024.repository.produto.MarcaRepository;
import br.unitins.topicos2.ano2024.repository.produto.PodRecarregavelRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PodRecarregavelServiceImpl implements PodRecarregavelService{
    
    @Inject
    PodRecarregavelRepository podRecarregavelRepository;

    @Inject
    CorRepository corRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    Validator validator;
    
    @Override
    public List<PodRecarregavelResponseDTO> getAll(int page, int pageSize) {
        List<PodRecarregavel> list = podRecarregavelRepository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        
        return list.stream().map(e -> PodRecarregavelResponseDTO.valueOf(e)).collect(Collectors.toList());
    }    
    
    @Override
    public PodRecarregavelResponseDTO findById(Long id) {
        PodRecarregavel podRecarregavel = podRecarregavelRepository.findById(id);
        if (podRecarregavel == null)
            throw new NotFoundException("PodRecarregavel não encontrado.");
        return PodRecarregavelResponseDTO.valueOf(podRecarregavel);
    }

    @Override
    @Transactional
    public PodRecarregavelResponseDTO create(@Valid PodRecarregavelDTO podRecarregavelDTO) throws ConstraintViolationException {
        //validar(PodRecarregavelDTO);

        PodRecarregavel entity = new PodRecarregavel();
        entity.setNome(podRecarregavelDTO.nome());
        entity.setValor(podRecarregavelDTO.valor());
        entity.setDescricao(podRecarregavelDTO.descricao());
        entity.setListaCor(podRecarregavelDTO.listaMarca().stream().map(id -> {
            return corRepository.findById(id);
        }).toList());
        entity.setListaMarca(podRecarregavelDTO.listaMarca().stream().map(id -> {
            return marcaRepository.findById(id);
        }).toList());

        podRecarregavelRepository.persist(entity);

        return PodRecarregavelResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public PodRecarregavelResponseDTO update(Long id, PodRecarregavelDTO podRecarregavelDTO) throws ConstraintViolationException{
        validar(podRecarregavelDTO);
   
        PodRecarregavel entity = podRecarregavelRepository.findById(id);

        entity.setNome(podRecarregavelDTO.nome());
        entity.setValor(podRecarregavelDTO.valor());
        entity.setDescricao(podRecarregavelDTO.descricao());

        return PodRecarregavelResponseDTO.valueOf(entity);
    }

    private void validar(PodRecarregavelDTO podRecarregavelDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<PodRecarregavelDTO>> violations = validator.validate(podRecarregavelDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        podRecarregavelRepository.deleteById(id);
    }

    @Override
    public List<PodRecarregavelResponseDTO> findByNome(String nome) {
    List<PodRecarregavel> list = podRecarregavelRepository.findByNome(nome).list();
    return list.stream().map(e -> PodRecarregavelResponseDTO.valueOf(e)).collect(Collectors.toList());
    }
    
    @Override
    public long count() {
        return podRecarregavelRepository.count();
    }
}