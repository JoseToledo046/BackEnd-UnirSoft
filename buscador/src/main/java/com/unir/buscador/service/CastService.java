package com.unir.buscador.service;

import com.unir.buscador.model.pojo.Cast;
import com.unir.buscador.model.pojo.CastDto;
import com.unir.buscador.model.request.CreateCastRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CastService {

    List<Cast> getCastL(
            Integer cast_id,
            Integer id_film,
            String name,
            String original_name,
            String profile_path,
            String character_name);

    Cast getCast(Integer cast_id);

    Cast createCast(CreateCastRequest request);

    Boolean removeCast(Integer cast_id);

    Cast updateCastP(Integer cast_id, String updRequest);

    Cast updateCast(Integer cast_id, CastDto updRequest);

}
