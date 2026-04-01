package com.example.logitrack.controller;

import com.example.logitrack.model.Veiculo;
import com.example.logitrack.repository.VeiculoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin("*")
public class VeiculoController {

    private final VeiculoRepository repository;

    public VeiculoController(VeiculoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Veiculo> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Veiculo criar(@RequestBody Veiculo veiculo) {
        return repository.save(veiculo);
    }

    @PutMapping("/{id}")
    public Veiculo atualizar(@PathVariable Long id, @RequestBody Veiculo veiculo) {
    veiculo.setId(id);
    return repository.save(veiculo);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
    repository.deleteById(id);
    }
}