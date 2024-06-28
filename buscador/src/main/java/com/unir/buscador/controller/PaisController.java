package com.unir.buscador.controller;

import com.unir.buscador.model.pojo.Pais;
import com.unir.buscador.model.pojo.PaisDto;
import com.unir.buscador.model.request.CreatePaisRequest;
import com.unir.buscador.service.PaisService;
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
public class PaisController {

    private final PaisService paisService;

    @GetMapping("/paises")
    public List<Pais> getPais(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer id_film,
            @RequestParam(required = false) String ipais) {

        List<Pais> pais = paisService.getPaisL(id, id_film, ipais);
        return pais;

    }

    @GetMapping("/paises/{id}")
    public ResponseEntity<Pais> getPaisID(@PathVariable Integer id) {

        Pais pais = paisService.getPais(id);

        if (pais != null) {
            return ResponseEntity.ok(pais);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/paises")
    public ResponseEntity<Pais> addPais(@RequestBody CreatePaisRequest request) {

        Pais creaPais = paisService.createPais(request);
        if (creaPais != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(creaPais);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/paises/{id}")
    public ResponseEntity<Void> deletePais(@PathVariable Integer id) {

        Boolean removed = paisService.removePais(id);

        if (removed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    
    @PatchMapping("/paises/{id}")
    public ResponseEntity<Pais> patchPais(@PathVariable Integer id,
            @RequestBody String patchBody) {

        Pais patched = paisService.updatePaisP(id, patchBody);
        if (patched != null) {
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
    
    @PutMapping("/paises/{id}")
    public ResponseEntity<Pais> putPais(@PathVariable Integer id,
            @RequestBody PaisDto request) {

        Pais updated = paisService.updatePais(id, request);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
