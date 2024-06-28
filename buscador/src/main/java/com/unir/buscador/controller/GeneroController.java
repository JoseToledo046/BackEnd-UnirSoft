package com.unir.buscador.controller;

import com.unir.buscador.model.pojo.Genero;
import com.unir.buscador.model.pojo.GeneroDto;
import com.unir.buscador.model.request.CreateGeneroRequest;
import com.unir.buscador.service.GeneroService;
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
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping("/generos")
    public List<Genero> getGenero(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer id_film,
            @RequestParam(required = false) String name) {

        List<Genero> genero = generoService.getGeneroL(id, id_film, name);
        return genero;

    }

    @GetMapping("/generos/{is}")
    public ResponseEntity<Genero> getGeneroID(@PathVariable Integer is) {

        Genero genero = generoService.getGenero(is);

        if (genero != null) {
            return ResponseEntity.ok(genero);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/generos")
    public ResponseEntity<Genero> addGenero(@RequestBody CreateGeneroRequest request) {

        Genero creaCast = generoService.createGenero(request);
        if (creaCast != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(creaCast);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/generos/{is}")
    public ResponseEntity<Void> deleteCast(@PathVariable Integer id) {

        Boolean removed = generoService.removeGenero(id);

        if (removed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    
    @PatchMapping("/generos/{id}")
    public ResponseEntity<Genero> patchCast(@PathVariable Integer id,
            @RequestBody String patchBody) {

        Genero patched = generoService.updateGeneroP(id, patchBody);
        if (patched != null) {
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
    
    @PutMapping("/generos/{is}")
    public ResponseEntity<Genero> putCast(@PathVariable Integer cast_id,
            @RequestBody GeneroDto request) {

        Genero updated = generoService.updateGenero(cast_id, request);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
