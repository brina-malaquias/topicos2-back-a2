package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.ano2024.dto.produto.ResistenciaDTO;
import br.unitins.topicos2.ano2024.dto.produto.ResistenciaResponseDTO;
import br.unitins.topicos2.ano2024.model.produto.Resistencia;
import br.unitins.topicos2.ano2024.repository.produto.ResistenciaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ResistenciaServiceImpl implements ResistenciaService{
    
    @Inject
    ResistenciaRepository resistenciaRepository;

    @Inject
    Validator validator;

    @Override
    public List<ResistenciaResponseDTO> getAll(int page, int pageSize) {
        List<Resistencia> list = resistenciaRepository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        
        return list.stream().map(e -> ResistenciaResponseDTO.valueOf(e)).collect(Collectors.toList());
    }    

    @Override
    public ResistenciaResponseDTO findById(Long id) {
        Resistencia resistencia = resistenciaRepository.findById(id);
        if (resistencia == null)
            throw new NotFoundException("Resistencia não encontrado.");
        return ResistenciaResponseDTO.valueOf(resistencia);
    }

    @Override
    @Transactional
    public ResistenciaResponseDTO create(@Valid ResistenciaDTO resistenciaDTO) throws ConstraintViolationException {
        //validar(resistenciaDTO);

        Resistencia entity = new Resistencia();
        entity.setOhms(resistenciaDTO.ohms());

        resistenciaRepository.persist(entity);

        return ResistenciaResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public ResistenciaResponseDTO update(Long id, ResistenciaDTO resistenciaDTO) throws ConstraintViolationException{
        validar(resistenciaDTO);
   
        Resistencia entity = resistenciaRepository.findById(id);

        entity.setOhms(resistenciaDTO.ohms());

        return ResistenciaResponseDTO.valueOf(entity);
    }

    private void validar(ResistenciaDTO resistenciaDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<ResistenciaDTO>> violations = validator.validate(resistenciaDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        resistenciaRepository.deleteById(id);
    }

    @Override
    public List<ResistenciaResponseDTO> findByNome(String ohms) {
    List<Resistencia> list = resistenciaRepository.findByNome(ohms).list();
    return list.stream().map(e -> ResistenciaResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return resistenciaRepository.count();
    }

}
