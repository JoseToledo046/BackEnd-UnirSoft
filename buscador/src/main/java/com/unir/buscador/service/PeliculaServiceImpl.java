package com.unir.buscador.service;

import java.util.List;

import com.unir.buscador.model.pojo.Pelicula;
import com.unir.buscador.model.request.CreatePeliculaRequest;
import com.unir.buscador.repository.PeliculaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository respository;

    @Override
    public List<Pelicula> getPeliculas(
            Integer id,
            String title,
            String original_language,
            String release_date,
            Integer runtime,
            String tagline
    ) {

        if (StringUtils.hasLength(title)
                || StringUtils.hasLength(original_language)
                || StringUtils.hasLength(release_date)
                || StringUtils.hasLength(tagline)) {

            return respository.search(id, title, original_language, release_date, runtime, tagline);

        }

        List<Pelicula> peliculas = respository.getPeliculas();
        return peliculas.isEmpty() ? null : peliculas;
    }

    @Override
    public Pelicula getPelicula(Integer idFilm) {
        return respository.getById(idFilm);
    }

    @Override
    public Pelicula createPelicula(CreatePeliculaRequest request) {

        if (request != null && StringUtils.hasLength(request.getTitle().trim())) {

            request.setId(respository.maxID());

            Pelicula pelicula;
            pelicula = Pelicula.builder()
                    .id(request.getId())
                    .title(request.getTitle())
                    .backdrop_path(request.getBackdrop_path())
                    .poster_path(request.getPoster_path())
                    .budget(request.getBudget())
                    .original_language(request.getOriginal_language())
                    .original_title(request.getOriginal_title())
                    .popularity(request.getPopularity())
                    .release_date(request.getRelease_date())
                    .revenue(request.getRevenue())
                    .runtime(request.getRuntime())
                    .tagline(request.getTagline())
                    .vote_average(request.getVote_average())
                    .vote_count(request.getVote_count())
                    .overview(request.getOverview())
                    .build();

            return respository.save(pelicula);

        } else {
            return null;
        }

    }
    
    @Override
    public Boolean removePelicula(Integer idFilm){
        Pelicula pelicula = respository.getById(idFilm);
        if(pelicula != null){
            respository.delete(pelicula);
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }

}
