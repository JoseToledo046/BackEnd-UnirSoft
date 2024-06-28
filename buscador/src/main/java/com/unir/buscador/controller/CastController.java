package com.unir.buscador.controller;

import com.unir.buscador.model.pojo.Cast;
import com.unir.buscador.model.pojo.CastDto;
import com.unir.buscador.model.request.CreateCastRequest;
import com.unir.buscador.service.CastService;

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
public class CastController {

    private final CastService castService;

    @GetMapping("/personajes")
    public List<Cast> getCast(
            @RequestParam(required = false) Integer cast_id,
            @RequestParam(required = false) Integer id_film,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String original_name,
            @RequestParam(required = false) String profile_path,
            @RequestParam(required = false) String character_name) {

        List<Cast> cast = castService.getCastL(cast_id, id_film, name, original_name, profile_path, character_name);
        return cast;

    }

    @GetMapping("/personajes/{cast_id}")
    public ResponseEntity<Cast> getCastID(@PathVariable Integer cast_id) {

        Cast cast = castService.getCast(cast_id);

        if (cast != null) {
            return ResponseEntity.ok(cast);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/personajes")
    public ResponseEntity<Cast> addCast(@RequestBody CreateCastRequest request) {

        Cast creaCast = castService.createCast(request);
        if (creaCast != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(creaCast);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/personajes/{cast_id}")
    public ResponseEntity<Void> deleteCast(@PathVariable Integer cast_id) {

        Boolean removed = castService.removeCast(cast_id);

        if (removed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("/personajes/{cast_id}")
    public ResponseEntity<Cast> patchCast(@PathVariable Integer cast_id,
            @RequestBody String patchBody) {

        Cast patched = castService.updateCastP(cast_id, patchBody);
        if (patched != null) {
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/personajes/{cast_id}")
    public ResponseEntity<Cast> putCast(@PathVariable Integer cast_id,
            @RequestBody CastDto request) {

        Cast updated = castService.updateCast(cast_id, request);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
