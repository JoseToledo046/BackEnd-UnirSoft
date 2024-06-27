package com.unir.buscador.controller;

import com.unir.buscador.model.pojo.Pelicula;
import com.unir.buscador.model.pojo.PeliculaDto;
import com.unir.buscador.model.request.CreatePeliculaRequest;
import com.unir.buscador.service.PeliculaService;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PeliculaController {

    private final PeliculaService peliculaService;

    @GetMapping("/peliculas")
    public List<Pelicula> getPelicula(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String original_language,
            @RequestParam(required = false) String release_date,
            @RequestParam(required = false) Integer runtime,
            @RequestParam(required = false) String tagline
    ) {

        List<Pelicula> peliculas = peliculaService.getPeliculas(
                id,
                title,
                original_language,
                release_date,
                runtime,
                tagline);
        return peliculas;

    }

    @GetMapping("/peliculas/{idFilm}")
    public ResponseEntity<Pelicula> getPeliculaID(@PathVariable Integer idFilm) {

        Pelicula peliculaId = peliculaService.getPelicula(idFilm);
        if (peliculaId != null) {
            return ResponseEntity.ok(peliculaId);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/peliculas")
    public ResponseEntity<Pelicula> addPelicula(@RequestBody CreatePeliculaRequest request) {

        Pelicula creaPelicula = peliculaService.createPelicula(request);
        if (creaPelicula != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(creaPelicula);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/peliculas/{idFilm}")
    public ResponseEntity<Void> deletePelicula(@PathVariable Integer idFilm) {

        Boolean removed = peliculaService.removePelicula(idFilm);

        if (removed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("/peliculas/{idFilm}")
    public ResponseEntity<Pelicula> patchPelicula(@PathVariable Integer idFilm,
            @RequestBody String patchBody) {

        Pelicula patched = peliculaService.updatePeliculaP(idFilm, patchBody);
        if (patched != null) {
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/peliculas/{idFilm}")
    public ResponseEntity<Pelicula> putPelicula(@PathVariable Integer idFilm,
            @RequestBody PeliculaDto request) {

        Pelicula updated = peliculaService.updatePelicula(idFilm, request);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
