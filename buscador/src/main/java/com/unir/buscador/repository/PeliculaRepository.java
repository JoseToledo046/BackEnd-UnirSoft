package com.unir.buscador.repository;

import com.unir.buscador.data.SearchCriteria;
import com.unir.buscador.data.SearchOperation;
import com.unir.buscador.data.SearchStatement;
import com.unir.buscador.model.pojo.Pelicula;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.apache.commons.lang3.StringUtils;

@Repository
@RequiredArgsConstructor
public class PeliculaRepository {

    private final PeliculaJpaRepository repository;
    
    public List<Pelicula> getPeliculas() {
        return repository.findAll();
    }

    public Pelicula getById(Integer idFilm) {
        return repository.findById(idFilm).orElse(null);
    }

    public List<Pelicula> search(
            Integer id,
            String title,
            String original_language,
            String release_date,
            Integer runtime,
            String tagline
    ) {

        SearchCriteria<Pelicula> spec;
        spec = new SearchCriteria<>();

        if (StringUtils.isNotBlank(title)) {
            spec.add(new SearchStatement("title", title, SearchOperation.MATCH));
        }
        if (StringUtils.isNotBlank(original_language)) {
            spec.add(new SearchStatement("original_language", original_language, SearchOperation.EQUAL));
        }
        if (StringUtils.isNotBlank(release_date)) {
            spec.add(new SearchStatement("release_date", release_date, SearchOperation.EQUAL));
        }
        if (StringUtils.isNotBlank(tagline)) {
            spec.add(new SearchStatement("tagline", tagline, SearchOperation.MATCH));
        }
        if (runtime != null) {
            spec.add(new SearchStatement("runtime", runtime, SearchOperation.EQUAL));
        }

        return repository.findAll(spec);
    }
    
    public Pelicula save(Pelicula pelicula){
        return repository.save(pelicula);
    }
    
    public void delete(Pelicula pelicula){
        repository.delete(pelicula);
    }
    
    public  Integer maxID(){
        return repository.findMaxID();
    }
}
