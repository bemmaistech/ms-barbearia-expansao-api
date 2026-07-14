package com.bemmaistech.ms_app_barbearia_agendamento.controller;

import com.bemmaistech.ms_app_barbearia_agendamento.entity.Agendamento;
import com.bemmaistech.ms_app_barbearia_agendamento.repository.AgendamentoRepository;
import com.bemmaistech.ms_app_barbearia_agendamento.service.CadastroService;
import com.bemmaistech.ms_app_barbearia_agendamento.dto.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @GetMapping
    public List<Agendamento> visualizarCadastro() {
        return agendamentoRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<String> criar(@RequestBody UserRequest body) {

        cadastroService.criarAgendamento(body);

        return ResponseEntity.ok("Agendamento concluido com sucesso");
    }
}