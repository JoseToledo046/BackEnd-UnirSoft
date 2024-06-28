package com.unir.buscador.repository;

import com.unir.buscador.data.SearchCriteriaCast;
import com.unir.buscador.data.SearchOperation;
import com.unir.buscador.data.SearchStatement;
import com.unir.buscador.model.pojo.Cast;

import org.apache.commons.lang3.StringUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
@RequiredArgsConstructor
public class CastRepository {

    private final CastJpaRepository repository;

    public List<Cast> getCast() {
        return repository.findAll();
    }

    public Cast getById(Integer cast_id) {
        return repository.findById(cast_id).orElse(null);
    }

    public Cast save(Cast cast) {
        return repository.save(cast);
    }

    public void delete(Cast cast) {
        repository.delete(cast);
    }

    public List<Cast> search(
            Integer cast_id,
            Integer id_film,
            String name,
            String original_name,
            String profile_path,
            String character_name) {

        SearchCriteriaCast<Cast> spec;
        spec = new SearchCriteriaCast<>();

        if (cast_id != null) {
            spec.add(new SearchStatement("cast_id", cast_id, SearchOperation.EQUAL));
        }
        if (StringUtils.isNotBlank(name)) {
            spec.add(new SearchStatement("name", name, SearchOperation.MATCH));
        }
        if (StringUtils.isNotBlank(profile_path)) {
            spec.add(new SearchStatement("profile_path", profile_path, SearchOperation.MATCH));
        }
        if (StringUtils.isNotBlank(character_name)) {
            spec.add(new SearchStatement("character_name", character_name, SearchOperation.MATCH));
        }
        if (id_film != null && id_film > 0) {
            spec.add(new SearchStatement("id_film", id_film, SearchOperation.EQUAL));
        }

        return repository.findAll(spec);

    }
    
    public Integer maxID() {
        return repository.findMaxID();
    }

}
