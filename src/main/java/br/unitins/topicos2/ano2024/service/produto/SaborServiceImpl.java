package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.ano2024.dto.produto.SaborResponseDTO;
import br.unitins.topicos2.ano2024.dto.produto.SaborDTO;
import br.unitins.topicos2.ano2024.model.produto.Sabor;
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
public class SaborServiceImpl implements SaborService{
    
    @Inject
    SaborRepository saborRepository;

    @Inject
    Validator validator;

    @Override
    public List<SaborResponseDTO> getAll(int page, int pageSize) {
        List<Sabor> list = saborRepository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        
        return list.stream().map(e -> SaborResponseDTO.valueOf(e)).collect(Collectors.toList());
    }   

    @Override
    public SaborResponseDTO findById(Long id) {
        Sabor sabor = saborRepository.findById(id);
        if (sabor == null)
            throw new NotFoundException("Sabor não encontrado.");
        return SaborResponseDTO.valueOf(sabor);
    }

    @Override
    @Transactional
    public SaborResponseDTO create(@Valid SaborDTO saborDTO) throws ConstraintViolationException {
        //validar(saborDTO);

        Sabor entity = new Sabor();
        entity.setSabor(saborDTO.sabor());

        saborRepository.persist(entity);

        return SaborResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public SaborResponseDTO update(Long id, SaborDTO saborDTO) throws ConstraintViolationException{
        validar(saborDTO);
   
        Sabor entity = saborRepository.findById(id);

        entity.setSabor(saborDTO.sabor());

        return SaborResponseDTO.valueOf(entity);
    }

    private void validar(SaborDTO saborDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<SaborDTO>> violations = validator.validate(saborDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        saborRepository.deleteById(id);
    }

    @Override
    public List<SaborResponseDTO> findByNome(String sabor) {
    List<Sabor> list = saborRepository.findByNome(sabor).list();
    return list.stream().map(e -> SaborResponseDTO.valueOf(e)).collect(Collectors.toList());
    }
    
    @Override
    public long count() {
        return saborRepository.count();
    }
}
