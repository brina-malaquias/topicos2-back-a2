package br.unitins.topicos2.ano2024.service.produto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.topicos2.ano2024.dto.produto.NicSaltDTO;
import br.unitins.topicos2.ano2024.dto.produto.NicSaltResponseDTO;
import br.unitins.topicos2.ano2024.model.produto.NicSalt;
import br.unitins.topicos2.ano2024.repository.produto.MarcaRepository;
import br.unitins.topicos2.ano2024.repository.produto.NicSaltRepository;
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
public class NicSaltServiceImpl implements NicSaltService{
    
    @Inject
    NicSaltRepository nicSaltRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    SaborRepository saborRepository;

    @Inject
    Validator validator;
    
    @Override
    public List<NicSaltResponseDTO> getAll(int page, int pageSize) {
        List<NicSalt> list = nicSaltRepository
                                .findAll()
                                .page(page, pageSize)
                                .list();
        
        return list.stream().map(e -> NicSaltResponseDTO.valueOf(e)).collect(Collectors.toList());
    }    
    
    @Override
    public NicSaltResponseDTO findById(Long id) {
        NicSalt nicSalt = nicSaltRepository.findById(id);
        if (nicSalt == null)
            throw new NotFoundException("NicSalt não encontrado.");
        return NicSaltResponseDTO.valueOf(nicSalt);
    }

    @Override
    @Transactional
    public NicSaltResponseDTO create(@Valid NicSaltDTO nicSaltDTO) throws ConstraintViolationException {
        //validar(NicSaltDTO);

        NicSalt entity = new NicSalt();
        entity.setNome(nicSaltDTO.nome());
        entity.setValor(nicSaltDTO.valor());
        entity.setDescricao(nicSaltDTO.descricao());
        entity.setListaSabor(nicSaltDTO.listaMarca().stream().map(id -> {
            return saborRepository.findById(id);
        }).toList());
        entity.setListaMarca(nicSaltDTO.listaMarca().stream().map(id -> {
            return marcaRepository.findById(id);
        }).toList());

        nicSaltRepository.persist(entity);

        return NicSaltResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public NicSaltResponseDTO update(Long id, NicSaltDTO nicSaltDTO) throws ConstraintViolationException{
        validar(nicSaltDTO);
   
        NicSalt entity = nicSaltRepository.findById(id);

        entity.setNome(nicSaltDTO.nome());
        entity.setValor(nicSaltDTO.valor());
        entity.setDescricao(nicSaltDTO.descricao());

        return NicSaltResponseDTO.valueOf(entity);
    }

    private void validar(NicSaltDTO nicSaltDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<NicSaltDTO>> violations = validator.validate(nicSaltDTO);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        nicSaltRepository.deleteById(id);
    }

    @Override
    public List<NicSaltResponseDTO> findByNome(String nome) {
    List<NicSalt> list = nicSaltRepository.findByNome(nome).list();
    return list.stream().map(e -> NicSaltResponseDTO.valueOf(e)).collect(Collectors.toList());
    }
    
    @Override
    public long count() {
        return nicSaltRepository.count();
    }
}