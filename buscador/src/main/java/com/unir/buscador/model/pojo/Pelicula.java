/*Entidad*/
package com.unir.buscador.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.math.BigDecimal;
import lombok.Builder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "t_pelicula")
public class Pelicula {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "backdrop_path")
    private String backdrop_path;

    @Column(name = "poster_path")
    private String poster_path;

    @Column(name = "budget")
    private BigDecimal budget;

    @Column(name = "original_language")
    private String original_language;

    @Column(name = "original_title")
    private String original_title;

    @Column(name = "popularity")
    private BigDecimal popularity;

    @Column(name = "release_date")
    private String release_date;

    @Column(name = "revenue")
    private BigDecimal revenue;

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "tagline")
    private String tagline;

    @Column(name = "vote_average")
    private BigDecimal vote_average;

    @Column(name = "vote_count")
    private BigDecimal vote_count;

    @Column(name = "overview")
    private String overview;

    public void update(PeliculaDto peliculaDto) {
        this.title = peliculaDto.getTitle();
        this.backdrop_path = peliculaDto.getBackdrop_path();
        this.poster_path = peliculaDto.getPoster_path();
        this.budget = peliculaDto.getBudget();
        this.original_language = peliculaDto.getOriginal_language();
        this.original_title = peliculaDto.getOriginal_title();
        this.popularity = peliculaDto.getPopularity();
        this.release_date = peliculaDto.getRelease_date();
        this.revenue = peliculaDto.getRevenue();
        this.runtime = peliculaDto.getRuntime();
        this.tagline = peliculaDto.getTagline();
        this.vote_average = peliculaDto.getVote_average();
        this.vote_count = peliculaDto.getVote_count();
        this.overview = peliculaDto.getOverview();
    }

}
