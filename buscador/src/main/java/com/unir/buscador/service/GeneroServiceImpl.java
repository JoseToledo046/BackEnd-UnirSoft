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
import com.unir.buscador.model.pojo.Genero;
import com.unir.buscador.model.pojo.GeneroDto;
import com.unir.buscador.model.request.CreateGeneroRequest;
import com.unir.buscador.repository.GeneroRepository;

@Service
@Slf4j
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Genero> getGeneroL(
            Integer id,
            Integer id_film,
            String name) {

        if (id != null
                || id_film != null
                || StringUtils.hasLength(name)) {
            return repository.search(id, id_film, name);
        }

        List<Genero> genero = repository.getGenero();
        return genero.isEmpty() ? null : genero;
    }

    @Override
    public Genero getGenero(Integer id) {

        return repository.findById(id);

    }

    @Override
    public Genero createGenero(CreateGeneroRequest request) {

        if (request != null) {
            
            request.setId(repository.maxID());

            Genero genero;
            genero = Genero.builder()
                    .id(request.getId())
                    .id_film(request.getId_film())
                    .name(request.getName())
                    .build();

            return repository.save(genero);

        } else {
            return null;
        }

    }

    @Override
    public Boolean removeGenero(Integer id) {

        Genero genero = repository.findById(id);

        if (genero != null) {
            repository.delete(genero);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }

    }

    @Override
    public Genero updateGeneroP(Integer id, String updRequest) {
        
        Genero genero = repository.findById(id);
        
        if (genero != null) {
            
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(updRequest));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(genero)));
                Genero patched = objectMapper.treeToValue(target, Genero.class);
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
    public Genero updateGenero(Integer id, GeneroDto updRequest) {
        
        Genero genero = repository.findById(id);
        
        if (genero != null) {
            genero.update(updRequest);
            repository.save(genero);
            return genero;
        } else {
            return null;
        }
        
    }

}
