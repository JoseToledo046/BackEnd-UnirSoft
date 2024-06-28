package com.unir.buscador.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmpresaRequest {
    
    private Integer id;
    private Integer id_film;
    private Integer id_prod;
    private String logo_path;
    private String name;
    
}
