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
@Table(name = "t_video")
public class Video {
    
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_film")
    private Integer id_film;
    
    @Column(name = "id_video")
    private String id_video;
    
    @Column(name = "plataforma")
    private String plataforma;

    @Column(name = "name_video")
    private String name_video;
    
    public void update(VideoDto videoDto) {

        this.id_film = videoDto.getId_film();
        this.id_video = videoDto.getId_video();
        this.plataforma = videoDto.getPlataforma();
        this.name_video = videoDto.getName_video();

    }
    
}
