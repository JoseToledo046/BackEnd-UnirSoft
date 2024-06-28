package com.unir.buscador.repository;

import com.unir.buscador.data.SearchCriteriaEmpresa;
import com.unir.buscador.data.SearchOperation;
import com.unir.buscador.data.SearchStatement;
import com.unir.buscador.model.pojo.Empresa;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EmpresaRepository {

    private final EmpresaJpaRepository repository;

    public List<Empresa> getEmpresa() {
        return repository.findAll();
    }

    public Empresa getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Empresa save(Empresa empresa) {
        return repository.save(empresa);
    }

    public void delete(Empresa empresa) {
        repository.delete(empresa);
    }

    public List<Empresa> search(
            Integer id,
            Integer id_film,
            Integer id_prod,
            String logo_path,
            String name) {
        
        SearchCriteriaEmpresa<Empresa> spec;
        spec = new SearchCriteriaEmpresa<>();
        
        if (id != null) {
            spec.add(new SearchStatement("id", id, SearchOperation.EQUAL));
        }
        if (id_film != null) {
            spec.add(new SearchStatement("id_film", id_film, SearchOperation.EQUAL));
        }
        if (id_prod != null) {
            spec.add(new SearchStatement("id_prod", id_prod, SearchOperation.EQUAL));
        }
        if (StringUtils.isNotBlank(logo_path)) {
            spec.add(new SearchStatement("logo_path", logo_path, SearchOperation.MATCH));
        }
        if (StringUtils.isNotBlank(name)) {
            spec.add(new SearchStatement("name", name, SearchOperation.MATCH));
        }
        
        return repository.findAll(spec);

    }
    
    public Integer maxID() {
        return repository.findMaxID();
    }

}
