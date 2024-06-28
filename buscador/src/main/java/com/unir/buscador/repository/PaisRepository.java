package com.unir.buscador.repository;

import com.unir.buscador.data.SearchCriteriaPais;
import com.unir.buscador.data.SearchOperation;
import com.unir.buscador.data.SearchStatement;
import com.unir.buscador.model.pojo.Pais;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaisRepository {
    
    private final PaisJpaRepository repository;
    
    public List<Pais> getPais(){
        return repository.findAll();
    }
    
    public Pais findById(Integer id){
        return repository.findById(id).orElse(null);
    }
    
    public Pais save(Pais pais){
        return repository.save(pais);
    }
    
    public void delete(Pais pais){
        repository.delete(pais);
    }
    
    public List<Pais> search(
            Integer id,
            Integer id_film,
            String pais) {
        
        SearchCriteriaPais<Pais> spec;
        spec = new SearchCriteriaPais<>();
        
        if (id != null) {
            spec.add(new SearchStatement("id", id, SearchOperation.EQUAL));
        }
        if (id_film != null) {
            spec.add(new SearchStatement("id_film", id_film, SearchOperation.EQUAL));
        }
        if (StringUtils.isNotBlank(pais)) {
            spec.add(new SearchStatement("pais", pais, SearchOperation.MATCH));
        }
        
        return repository.findAll(spec);
        
    }
    
    public Integer maxID() {
        return repository.findMaxID();
    }
    
}
