package com.unir.buscador.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateGeneroRequest {
    
    private Integer id;
    private Integer id_film;
    private String name;
    
}
