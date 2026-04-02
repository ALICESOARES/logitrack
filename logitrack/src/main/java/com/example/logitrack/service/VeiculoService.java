package com.example.logitrack.service;

import com.example.logitrack.model.Veiculo;
import com.example.logitrack.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public List<Veiculo> listar() {
        return repository.findAll();
    }

    public Veiculo criar(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public Veiculo atualizar(Long id, Veiculo veiculo) {
        Veiculo existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        existente.setPlaca(veiculo.getPlaca());
        existente.setModelo(veiculo.getModelo());
        existente.setTipo(veiculo.getTipo());
        existente.setAno(veiculo.getAno());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Veículo não encontrado");
        }
        repository.deleteById(id);
    }
}