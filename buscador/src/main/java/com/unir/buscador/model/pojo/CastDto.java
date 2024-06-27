package com.unir.buscador.model.pojo;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CastDto {

    private Integer id_film;
    private String name;
    private String original_name;
    private BigDecimal popularity;
    private String profile_path;
    private String character_name;

}
