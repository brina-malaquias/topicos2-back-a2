package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.ano2024.dto.produto.PuffDTO;
import br.unitins.topicos2.ano2024.dto.produto.PuffResponseDTO;
import br.unitins.topicos2.ano2024.model.produto.Puff;
import br.unitins.topicos2.ano2024.repository.produto.PuffRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PuffServiceImpl implements PuffService{
    
    @Inject
    PuffRepository puffRepository;

    @Inject
    Validator validator;

    @Override
    public List<PuffResponseDTO> getAll(int page, int pageSize) {
        List<Puff> list = puffRepository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        
        return list.stream().map(e -> PuffResponseDTO.valueOf(e)).collect(Collectors.toList());
    }    

    @Override
    public PuffResponseDTO findById(Long id) {
        Puff puff = puffRepository.findById(id);
        if (puff == null)
            throw new NotFoundException("Puff não encontrado.");
        return PuffResponseDTO.valueOf(puff);
    }

    @Override
    @Transactional
    public PuffResponseDTO create(@Valid PuffDTO puffDTO) throws ConstraintViolationException {
        //validar(puffDTO);

        Puff entity = new Puff();
        entity.setQuantidade(puffDTO.quantidade());

        puffRepository.persist(entity);

        return PuffResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public PuffResponseDTO update(Long id, PuffDTO puffDTO) throws ConstraintViolationException{
        validar(puffDTO);
   
        Puff entity = puffRepository.findById(id);

        entity.setQuantidade(puffDTO.quantidade());

        return PuffResponseDTO.valueOf(entity);
    }

    private void validar(PuffDTO puffDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<PuffDTO>> violations = validator.validate(puffDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        puffRepository.deleteById(id);
    }

    @Override
    public List<PuffResponseDTO> findByNome(String quantidade) {
    List<Puff> list = puffRepository.findByNome(quantidade).list();
    return list.stream().map(e -> PuffResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return puffRepository.count();
    }

}
