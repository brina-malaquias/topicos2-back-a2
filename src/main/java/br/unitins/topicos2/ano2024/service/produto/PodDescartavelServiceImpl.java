package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.ano2024.dto.produto.PodDescartavelDTO;
import br.unitins.topicos2.ano2024.dto.produto.PodDescartavelResponseDTO;
import br.unitins.topicos2.ano2024.model.produto.PodDescartavel;
import br.unitins.topicos2.ano2024.repository.produto.MarcaRepository;
import br.unitins.topicos2.ano2024.repository.produto.PodDescartavelRepository;
import br.unitins.topicos2.ano2024.repository.produto.PuffRepository;
import br.unitins.topicos2.ano2024.repository.produto.SaborRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PodDescartavelServiceImpl implements PodDescartavelService{
    
    @Inject
    PodDescartavelRepository podDescartavelRepository;

    @Inject
    SaborRepository saborRepository;

    @Inject
    PuffRepository puffRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    Validator validator;
    
    @Override
    public List<PodDescartavelResponseDTO> getAll(int page, int pageSize) {
        List<PodDescartavel> list = podDescartavelRepository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        
        return list.stream().map(e -> PodDescartavelResponseDTO.valueOf(e)).collect(Collectors.toList());
    }    
    
    @Override
    public PodDescartavelResponseDTO findById(Long id) {
        PodDescartavel podDescartavel = podDescartavelRepository.findById(id);
        if (podDescartavel == null)
            throw new NotFoundException("PodDescartavel não encontrado.");
        return PodDescartavelResponseDTO.valueOf(podDescartavel);
    }

    @Override
    @Transactional
    public PodDescartavelResponseDTO create(@Valid PodDescartavelDTO podDescartavelDTO) throws ConstraintViolationException {
        //validar(PodDescartavelDTO);

        PodDescartavel entity = new PodDescartavel();
        entity.setNome(podDescartavelDTO.nome());
        entity.setValor(podDescartavelDTO.valor());
        entity.setDescricao(podDescartavelDTO.descricao());
        entity.setListaSabor(podDescartavelDTO.listaMarca().stream().map(id -> {
            return saborRepository.findById(id);
        }).toList());
        entity.setListaPuff(podDescartavelDTO.listaMarca().stream().map(id -> {
            return puffRepository.findById(id);
        }).toList());
        entity.setListaMarca(podDescartavelDTO.listaMarca().stream().map(id -> {
            return marcaRepository.findById(id);
        }).toList());

        podDescartavelRepository.persist(entity);

        return PodDescartavelResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public PodDescartavelResponseDTO update(Long id, PodDescartavelDTO podDescartavelDTO) throws ConstraintViolationException{
        validar(podDescartavelDTO);
   
        PodDescartavel entity = podDescartavelRepository.findById(id);

        entity.setNome(podDescartavelDTO.nome());
        entity.setValor(podDescartavelDTO.valor());
        entity.setDescricao(podDescartavelDTO.descricao());


        return PodDescartavelResponseDTO.valueOf(entity);
    }

    private void validar(PodDescartavelDTO podDescartavelDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<PodDescartavelDTO>> violations = validator.validate(podDescartavelDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        podDescartavelRepository.deleteById(id);
    }

    @Override
    public List<PodDescartavelResponseDTO> findByNome(String nome) {
    List<PodDescartavel> list = podDescartavelRepository.findByNome(nome).list();
    return list.stream().map(e -> PodDescartavelResponseDTO.valueOf(e)).collect(Collectors.toList());
    }
    
    @Override
    public long count() {
        return podDescartavelRepository.count();
    }
}