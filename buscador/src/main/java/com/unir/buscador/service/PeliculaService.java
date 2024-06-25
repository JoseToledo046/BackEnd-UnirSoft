package com.unir.buscador.service;

import com.unir.buscador.model.pojo.Pelicula;
import com.unir.buscador.model.pojo.PeliculaDto;
import com.unir.buscador.model.request.CreatePeliculaRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PeliculaService {

    List<Pelicula> getPeliculas(
            Integer id,
            String title,
            String original_language,
            String release_date,
            Integer runtime,
            String tagline
    );

    Pelicula getPelicula(Integer idFilm);
    Pelicula createPelicula(CreatePeliculaRequest request);
    Boolean removePelicula(Integer idFilm);
    Pelicula updatePeliculaP(Integer idFilm, String updRequist);
    Pelicula updatePelicula(Integer idFilm, PeliculaDto updRequest);

}
