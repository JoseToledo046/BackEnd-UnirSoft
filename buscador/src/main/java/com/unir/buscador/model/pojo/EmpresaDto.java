package com.unir.buscador.model.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EmpresaDto {

    private Integer id_film;
    private Integer id_prod;
    private String logo_path;
    private String name;

}
