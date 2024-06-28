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
import com.unir.buscador.model.pojo.Empresa;
import com.unir.buscador.model.pojo.EmpresaDto;
import com.unir.buscador.model.request.CreateEmpresaRequest;
import com.unir.buscador.repository.EmpresaRepository;

@Service
@Slf4j
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Empresa> getEmpresaL(
            Integer id,
            Integer id_film,
            Integer id_prod,
            String logo_path,
            String name) {

        if (id_film != null
                || id != null
                || id_prod != null
                || StringUtils.hasLength(logo_path)
                || StringUtils.hasLength(name)) {
            return repository.search(id, id_film, id_prod, logo_path, name);
        }

        List<Empresa> empresa = repository.getEmpresa();
        return empresa.isEmpty() ? null : empresa;
    }

    @Override
    public Empresa getEmpresa(Integer id) {
        
        return repository.getById(id);
        
    }

    @Override
    public Empresa createEmpresa(CreateEmpresaRequest request) {
        
        if (request != null) {
            
            request.setId(repository.maxID());
            
            Empresa empresa;
            empresa = Empresa.builder()
                    .id(request.getId())
                    .id_film(request.getId_film())
                    .id_prod(request.getId_prod())
                    .logo_path(request.getLogo_path())
                    .name(request.getName())
                    .build();
            
            return repository.save(empresa);
            
        } else {
            return null;
        }
        
    }

    @Override
    public Boolean removeEmpresa(Integer id) {
        
        Empresa empresa = repository.getById(id);
        
        if (empresa != null) {
            repository.delete(empresa);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
        
    }

    @Override
    public Empresa updateEmpresaP(Integer id, String updRequest) {
        
        Empresa empresa = repository.getById(id);
        
        if (empresa != null) {
            
            try {
                JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(objectMapper.readTree(updRequest));
                JsonNode target = jsonMergePatch.apply(objectMapper.readTree(objectMapper.writeValueAsString(empresa)));
                Empresa patched = objectMapper.treeToValue(target, Empresa.class);
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
    public Empresa updateEmpresa(Integer id, EmpresaDto updRequest) {
        
        Empresa empresa = repository.getById(id);
        
        if (empresa != null) {
            empresa.update(updRequest);
            repository.save(empresa);
            return empresa;
        } else {
            return null;
        }
        
    }

}
