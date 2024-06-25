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
public class CreatePeliculaRequest {
    
    private Integer id;
    private String title;
    private String backdrop_path;
    private String poster_path;
    private BigDecimal budget;
    private String original_language;
    private String original_title;
    private BigDecimal popularity;
    private String release_date;
    private BigDecimal revenue;
    private Integer runtime;
    private String tagline;
    private BigDecimal vote_average;
    private BigDecimal vote_count;
    private String overview;
    
}
