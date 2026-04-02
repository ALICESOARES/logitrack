package com.example.logitrack.service;

import com.example.logitrack.model.Viagem;
import com.example.logitrack.repository.ViagemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViagemService {

    private final ViagemRepository repository;

    public ViagemService(ViagemRepository repository) {
        this.repository = repository;
    }

    public List<Viagem> listar() {
        return repository.findAll();
    }

    public Viagem criar(Viagem viagem) {
        return repository.save(viagem);
    }

    public Viagem atualizar(Long id, Viagem viagem) {
        Viagem existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        existente.setVeiculo(viagem.getVeiculo());
        existente.setDataSaida(viagem.getDataSaida());
        existente.setDataChegada(viagem.getDataChegada());
        existente.setOrigem(viagem.getOrigem());
        existente.setDestino(viagem.getDestino());
        existente.setKmPercorrida(viagem.getKmPercorrida());

        return repository.save(existente);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Viagem não encontrada");
        }
        repository.deleteById(id);
    }
}