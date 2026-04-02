package com.example.logitrack.controller;

import com.example.logitrack.service.DashboardService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin("*")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/total-km")
    public Double totalKm() {
        return dashboardService.totalKm();
    }

    @GetMapping("/volume-por-categoria")
    public Object volumePorCategoria() {
        return dashboardService.volumePorCategoria();
    }

    @GetMapping("/ranking-utilizacao")
    public Object rankingUtilizacao() {
        return dashboardService.rankingUtilizacao();
    }

    @GetMapping("/proximas-manutencoes")
    public Object proximasManutencoes() {
        return dashboardService.proximasManutencoes();
    }

    @GetMapping("/projecao-financeira")
    public Double projecaoFinanceira() {
        return dashboardService.projecaoFinanceiraMesAtual();
    }

    @GetMapping
    public Object resumo() {
        return dashboardService.resumo();
    }
}