package com.atividade.security_data.controller;

import com.atividade.security_data.model.Nota;
import com.atividade.security_data.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    @Autowired
    private NotaService service;

    @PostMapping
    public ResponseEntity<Nota> create(@RequestBody Nota nota) {
        return ResponseEntity.ok(service.create(nota));
    }

    @GetMapping
    public ResponseEntity<List<Nota>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deletado = service.delete(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
