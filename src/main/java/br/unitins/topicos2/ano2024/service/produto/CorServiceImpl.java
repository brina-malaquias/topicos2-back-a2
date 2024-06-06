package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.ano2024.dto.produto.CorDTO;
import br.unitins.topicos2.ano2024.dto.produto.CorResponseDTO;
import br.unitins.topicos2.ano2024.model.produto.Cor;
import br.unitins.topicos2.ano2024.repository.produto.CorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CorServiceImpl implements CorService{

    @Inject
    CorRepository corRepository;

    @Inject
    Validator validator;

    @Override
    public List<CorResponseDTO> getAll(int page, int pageSize) {
        List<Cor> list = corRepository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        
        return list.stream().map(e -> CorResponseDTO.valueOf(e)).collect(Collectors.toList());
    }   

    @Override
    public CorResponseDTO findById(Long id) {
        Cor cor = corRepository.findById(id);
        if (cor == null)
            throw new NotFoundException("Cor não encontrado.");
        return CorResponseDTO.valueOf(cor);
    }

    @Override
    @Transactional
    public CorResponseDTO create(@Valid CorDTO corDTO) throws ConstraintViolationException {
        //validar(corDTO);

        Cor entity = new Cor();
        entity.setCor(corDTO.cor());

        corRepository.persist(entity);

        return CorResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public CorResponseDTO update(Long id, CorDTO corDTO) throws ConstraintViolationException{
        validar(corDTO);
   
        Cor entity = corRepository.findById(id);

        entity.setCor(corDTO.cor());

        return CorResponseDTO.valueOf(entity);
    }

    private void validar(CorDTO corDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<CorDTO>> violations = validator.validate(corDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        corRepository.deleteById(id);
    }

    @Override
    public List<CorResponseDTO> findByNome(String cor) {
    List<Cor> list = corRepository.findByNome(cor).list();
    return list.stream().map(e -> CorResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return corRepository.count();
    }

}
