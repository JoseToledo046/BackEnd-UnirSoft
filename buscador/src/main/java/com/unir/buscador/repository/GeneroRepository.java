package com.unir.buscador.repository;

import com.unir.buscador.data.SearchCriteriaGenero;
import com.unir.buscador.data.SearchOperation;
import com.unir.buscador.data.SearchStatement;
import com.unir.buscador.model.pojo.Genero;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GeneroRepository {
    
    private final GeneroJpaRepository repository;
    
    public List<Genero> getGenero(){
        return repository.findAll();
    }
    
    public Genero findById(Integer id){
        return repository.findById(id).orElse(null);
    }
    
    public Genero save(Genero genero){
        return repository.save(genero);
    }
    
    public void delete(Genero genero){
        repository.delete(genero);
    }
    
    public List<Genero> search(
            Integer id,
            Integer id_film,
            String name) {
        
        SearchCriteriaGenero<Genero> spec;
        spec = new SearchCriteriaGenero<>();
        
        if (id != null) {
            spec.add(new SearchStatement("id", id, SearchOperation.EQUAL));
        }
        if (id_film != null) {
            spec.add(new SearchStatement("id_film", id_film, SearchOperation.EQUAL));
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
