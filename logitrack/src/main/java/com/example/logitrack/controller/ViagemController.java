package com.example.logitrack.controller;

import com.example.logitrack.model.Viagem;
import com.example.logitrack.service.ViagemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viagens")
@CrossOrigin("*")
public class ViagemController {

    private final ViagemService service;

    public ViagemController(ViagemService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Viagem>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Viagem> criar(@RequestBody Viagem viagem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(viagem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viagem> atualizar(@PathVariable Long id, @RequestBody Viagem viagem) {
        return ResponseEntity.ok(service.atualizar(id, viagem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}