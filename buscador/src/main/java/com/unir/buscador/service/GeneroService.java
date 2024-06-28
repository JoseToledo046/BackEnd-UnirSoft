package com.unir.buscador.service;

import com.unir.buscador.model.pojo.Genero;
import com.unir.buscador.model.pojo.GeneroDto;
import com.unir.buscador.model.request.CreateGeneroRequest;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface GeneroService {

    List<Genero> getGeneroL(
            Integer id,
            Integer id_film,
            String name);

    Genero getGenero(Integer id);

    Genero createGenero(CreateGeneroRequest request);

    Boolean removeGenero(Integer id);

    Genero updateGeneroP(Integer id, String updRequest);

    Genero updateGenero(Integer id, GeneroDto updRequest);

}
