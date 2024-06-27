package com.unir.buscador.model.pojo;

import jakarta.persistence.*;
import java.math.BigDecimal;
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
@Table(name = "t_cast")
public class Cast {
    
    @Id
    @Column(name = "cast_id")
    private Integer cast_id;

    @Column(name = "id_film")
    private Integer id_film;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "popularity")
    private BigDecimal popularity;
    
    @Column(name = "profile_path")
    private String profile_path;
    
    @Column(name = "character_name")
    private String character_name;
    
    public  void update(CastDto castDto){
        
        this.id_film = castDto.getId_film();
        this.name = castDto.getName();
        this.popularity = castDto.getPopularity();
        this.profile_path = castDto.getProfile_path();
        this.character_name = castDto.getCharacter_name();
        
    }
    
}
