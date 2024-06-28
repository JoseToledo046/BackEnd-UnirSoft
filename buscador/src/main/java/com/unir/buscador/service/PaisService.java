package com.unir.buscador.service;

import com.unir.buscador.model.pojo.Pais;
import com.unir.buscador.model.pojo.PaisDto;
import com.unir.buscador.model.request.CreatePaisRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface PaisService {
    
    List<Pais> getPaisL(
            Integer id,
            Integer id_film,
            String pais);

    Pais getPais(Integer id);

    Pais createPais(CreatePaisRequest request);

    Boolean removePais(Integer id);

    Pais updatePaisP(Integer id, String updRequest);

    Pais updatePais(Integer id, PaisDto updRequest);
    
}
