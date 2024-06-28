package com.unir.buscador.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "t_genero")
public class Genero {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_film")
    private Integer id_film;

    @Column(name = "name")
    private String name;

    public void update(GeneroDto generoDto) {

        this.id_film = generoDto.getId_film();
        this.name = generoDto.getName();

    }

}
