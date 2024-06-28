package com.unir.buscador.controller;

import com.unir.buscador.model.pojo.Empresa;
import com.unir.buscador.model.pojo.EmpresaDto;
import com.unir.buscador.model.request.CreateEmpresaRequest;
import com.unir.buscador.service.EmpresaService;
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
public class EmpresaController {

    private final EmpresaService empresaService;

    @GetMapping("/empresas")
    public List<Empresa> getEmpresa(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer id_film,
            @RequestParam(required = false) Integer id_prod,
            @RequestParam(required = false) String logo_path,
            @RequestParam(required = false) String name) {

        List<Empresa> empresa = empresaService.getEmpresaL(id, id_film, id_prod, logo_path, name);
        return empresa;

    }

    @GetMapping("/empresas/{id}")
    public ResponseEntity<Empresa> getEmpresaID(@PathVariable Integer id) {

        Empresa empresa = empresaService.getEmpresa(id);

        if (empresa != null) {
            return ResponseEntity.ok(empresa);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/empresas")
    public ResponseEntity<Empresa> addEmpresa(@RequestBody CreateEmpresaRequest request) {

        Empresa creaEmpresa = empresaService.createEmpresa(request);
        if (creaEmpresa != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(creaEmpresa);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/empresas/{is}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Integer id) {

        Boolean removed = empresaService.removeEmpresa(id);

        if (removed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("/empresas/{cast_id}")
    public ResponseEntity<Empresa> patchCast(@PathVariable Integer id,
            @RequestBody String patchBody) {

        Empresa patched = empresaService.updateEmpresaP(id, patchBody);
        if (patched != null) {
            return ResponseEntity.ok(patched);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/empresas/{cast_id}")
    public ResponseEntity<Empresa> putCast(@PathVariable Integer cast_id,
            @RequestBody EmpresaDto request) {

        Empresa updated = empresaService.updateEmpresa(cast_id, request);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

}
