package com.bemmaistech.ms_app_barbearia_agendamento.controller;

import com.bemmaistech.ms_app_barbearia_agendamento.entity.Agendamento;
import com.bemmaistech.ms_app_barbearia_agendamento.service.CadastroService;
import com.bemmaistech.ms_app_barbearia_agendamento.dto.request.UserRequest;
import com.bemmaistech.ms_app_barbearia_agendamento.dto.request.UpdateAgendamentoRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/cadastro")
public class CadastroController {

    private final CadastroService cadastroService;

    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }
    @GetMapping("/horarios-por-dia")
    public ResponseEntity<Map<String, List<String>>> listarHorariosPorDia() {
        return ResponseEntity.ok(cadastroService.obterHorariosPorDia());
    }
    @GetMapping
    public ResponseEntity<List<Agendamento>> listar() {
        return ResponseEntity.ok(cadastroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cadastroService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<Agendamento> criar(@Valid @RequestBody UserRequest request) {
        Agendamento agendamento = cadastroService.criarAgendamento(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> alterar(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAgendamentoRequest request) {
        Agendamento agendamento = cadastroService.alterarAgendamento(id, request);
        return ResponseEntity.ok(agendamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cadastroService.deletarAgendamento(id);
        return ResponseEntity.noContent().build();
    }


}