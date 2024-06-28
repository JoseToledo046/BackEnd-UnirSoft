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
public class CreateVideoRequest {
    
    private Integer id;
    private Integer id_film;
    private String id_video;
    private String plataforma;
    private String name_video;
    
}
