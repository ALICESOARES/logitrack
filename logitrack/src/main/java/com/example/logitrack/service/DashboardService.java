package com.example.logitrack.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    private final JdbcTemplate jdbcTemplate;

    public DashboardService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Double totalKm() {
        String sql = """
                SELECT COALESCE(SUM(km_percorrida), 0)
                FROM viagens
                """;
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    public List<Map<String, Object>> volumePorCategoria() {
        String sql = """
                SELECT v.tipo AS categoria, COUNT(vi.id) AS quantidade
                FROM viagens vi
                JOIN veiculos v ON vi.veiculo_id = v.id
                GROUP BY v.tipo
                ORDER BY v.tipo
                """;
        return jdbcTemplate.queryForList(sql);
    }

    public Map<String, Object> rankingUtilizacao() {
        String sql = """
                SELECT ve.id,
                       ve.placa,
                       ve.modelo,
                       ve.tipo,
                       COALESCE(SUM(v.km_percorrida), 0) AS total_km
                FROM viagens v
                JOIN veiculos ve ON v.veiculo_id = ve.id
                GROUP BY ve.id, ve.placa, ve.modelo, ve.tipo
                ORDER BY total_km DESC
                LIMIT 1
                """;

        List<Map<String, Object>> resultado = jdbcTemplate.queryForList(sql);

        if (resultado.isEmpty()) {
            return Map.of("mensagem", "Nenhuma viagem cadastrada");
        }

        return resultado.get(0);
    }

    public List<Map<String, Object>> proximasManutencoes() {
        String sql = """
                SELECT m.id,
                       ve.placa,
                       ve.modelo,
                       m.data_inicio,
                       m.data_finalizacao,
                       m.tipo_servico,
                       m.custo_estimado,
                       m.status
                FROM manutencoes m
                JOIN veiculos ve ON m.veiculo_id = ve.id
                ORDER BY
                    CASE
                        WHEN m.status = 'PENDENTE' THEN 1
                        WHEN m.status = 'EM REALIZACAO' THEN 2
                        WHEN m.status = 'CONCLUIDA' THEN 3
                        ELSE 4
                    END,
                    m.data_inicio ASC
                LIMIT 5
                """;
        return jdbcTemplate.queryForList(sql);
    }

    public Double projecaoFinanceiraMesAtual() {
        String sql = """
                SELECT COALESCE(SUM(custo_estimado), 0)
                FROM manutencoes
                WHERE DATE_TRUNC('month', data_inicio) = (
                    SELECT DATE_TRUNC('month', MAX(data_inicio))
                    FROM manutencoes
                )
                """;
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    public Map<String, Object> resumo() {
        Map<String, Object> dashboard = new LinkedHashMap<>();
        dashboard.put("total_km", totalKm());
        dashboard.put("volume_por_categoria", volumePorCategoria());
        dashboard.put("ranking_utilizacao", rankingUtilizacao());
        dashboard.put("proximas_manutencoes", proximasManutencoes());
        dashboard.put("projecao_financeira", projecaoFinanceiraMesAtual());
        return dashboard;
    }
}