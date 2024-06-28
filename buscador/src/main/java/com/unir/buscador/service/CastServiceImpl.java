package com.unir.buscador.service;

import com.unir.buscador.model.pojo.Cast;
import com.unir.buscador.model.pojo.CastDto;
import com.unir.buscador.model.request.CreateCastRequest;
import com.unir.buscador.repository.CastRepository;

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

@Service
@Slf4j
public class CastServiceImpl implements CastService {
    
    @Autowired
    private CastRepository repository;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Override
    public List<Cast> getCastL(
            Integer cast_id,
            Integer id_film,
            String name,
            String original_name,
            String profile_path,
            String character_name) {
        
        if (id_film != null
                || StringUtils.hasLength(name)
                || StringUtils.hasLength(original_name)
                || StringUtils.hasLength(profile_path)
                || StringUtils.hasLength(character_name)
                || cast_id != null) {
            return repository.search(cast_id, id_film, name, original_name, profile_path, character_name);
        }
        
        List<Cast> cast = repository.getCast();
        return cast.isEmpty() ? null : cast;
        
    }
    
    @Override
    public Cast getCast(Integer cast_id) {
        return repository.getById(cast_id);
    }
    
    @Override
    public Cast createCast(CreateCastRequest request) {
        
        if (request != null) {
            
            request.setId_film(repository.maxID());
            
            Cast cast;
            cast = Cast.builder()
                    .cast_id(request.getCast_id())
                    .id_film(request.getId_film())
                    .name(request.getName())
                    .popularity(request.getPopularity())
                    .profile_path(request.getProfile_path())
                    .character_name(request.getCharacter_name())
                    .build();
            
            return repository.save(cast);
            
        } else {
            return null;
        }
        
    }
    
    @Override
    public Boolean removeCast(Integer cast_id) {
        
        Cast cast = repository.getById(cast_id);
        
        if (cast != null) {
            repository.delete(cast);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
        
    }
    
    @Override
    public Cast updateCastP(Integer cast_id, String updRequest) {
        
        Cast cast = repository.getById(cast_id);
        
        if (cast != null) {
            
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(updRequest));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(cast)));
                Cast patched = objectMapper.treeToValue(target, Cast.class);
                repository.save(patched);
                return patched;
            } catch (JsonProcessingException | JsonPatchException e) {
                log.error("Error updating pelicula {}", cast_id, e);
                return null;
            }
            
        } else {
            return null;
        }
    }
    
    @Override
    public Cast updateCast(Integer cast_id, CastDto updRequest) {
        
        Cast cast = repository.getById(cast_id);
        
        if (cast != null) {
            cast.update(updRequest);
            repository.save(cast);
            return cast;
        } else {
            return null;
        }
    }
    
}