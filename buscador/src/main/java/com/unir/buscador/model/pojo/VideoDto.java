package com.unir.buscador.model.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class VideoDto {

    private Integer id_film;
    private String id_video;
    private String plataforma;
    private String name_video;

}
