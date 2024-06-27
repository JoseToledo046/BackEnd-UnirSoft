package com.unir.buscador.model.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCastRequest {
    
    private String cast_id;
    private Integer id_film;
    private String name;
    private String original_name;
    private BigDecimal popularity;
    private String profile_path;
    private String character_name;
    
}
