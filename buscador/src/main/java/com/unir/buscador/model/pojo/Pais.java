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
@Table(name = "t_pais_rodaje")
public class Pais {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_film")
    private Integer id_film;

    @Column(name = "pais")
    private String pais;

    public void update(PaisDto paisDto) {

        this.id_film = paisDto.getId_film();
        this.pais = paisDto.getPais();

    }

}
