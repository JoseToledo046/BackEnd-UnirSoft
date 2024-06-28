package com.unir.buscador.model.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "t_produccion")
public class Empresa {
    
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_film")
    private Integer id_film;
    
    @Column(name = "id_prod")
    private Integer id_prod;
    
    @Column(name = "logo_path")
    private String logo_path;
    
    @Column(name = "name")
    private String name;
    
    public void update(EmpresaDto empresaDto) {

        this.id_film = empresaDto.getId_film();
        this.id_prod = empresaDto.getId_prod();
        this.logo_path = empresaDto.getLogo_path();
        this.name = empresaDto.getName();

    }
    
}
