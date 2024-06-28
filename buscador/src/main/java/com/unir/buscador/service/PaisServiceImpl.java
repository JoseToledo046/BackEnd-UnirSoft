package com.unir.buscador.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.unir.buscador.model.pojo.Pais;
import com.unir.buscador.model.pojo.PaisDto;
import com.unir.buscador.model.request.CreatePaisRequest;
import com.unir.buscador.repository.PaisRepository;

@Service
@Slf4j
public class PaisServiceImpl implements PaisService {
    
    @Autowired
    private PaisRepository repository;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Pais> getPaisL(
            Integer id, 
            Integer id_film,
            String pais) {

        if (id != null
                || id_film != null
                || StringUtils.hasLength(pais)) {
            return repository.search(id, id_film, pais);
        }

        List<Pais> cast = repository.getPais();
        return cast.isEmpty() ? null : cast;
    }

    @Override
    public Pais getPais(Integer id) {
        
        return repository.findById(id);
        
    }

    @Override
    public Pais createPais(CreatePaisRequest request) {
        
        if (request != null) {
            
            request.setId(repository.maxID());
            
            Pais pais;
            pais = Pais.builder()
                    .id(request.getId())
                    .id_film(request.getId_film())
                    .pais(request.getPais())
                    .build();
            
            return repository.save(pais);
            
        } else {
            return null;
        }
        
    }

    @Override
    public Boolean removePais(Integer id) {
        
        Pais pais = repository.findById(id);
        
        if (pais != null) {
            repository.delete(pais);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
        
    }

    @Override
    public Pais updatePaisP(Integer id, String updRequest) {
        
        Pais pais = repository.findById(id);
        
        if (pais != null) {
            
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(updRequest));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(pais)));
                Pais patched = objectMapper.treeToValue(target, Pais.class);
                repository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating pelicula {}", id, e);
                return null;
            }
            
        } else {
            return null;
        }
        
    }

    @Override
    public Pais updatePais(Integer id, PaisDto updRequest) {
        
        Pais pais = repository.findById(id);
        
        if (pais != null) {
            pais.update(updRequest);
            repository.save(pais);
            return pais;
        } else {
            return null;
        }
        
    }

}
