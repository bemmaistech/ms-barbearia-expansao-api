package com.bemmaistech.ms_app_barbearia_agendamento.service;

import com.bemmaistech.ms_app_barbearia_agendamento.entity.AgendamentoNovo;
import com.bemmaistech.ms_app_barbearia_agendamento.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;
import com.bemmaistech.ms_app_barbearia_agendamento.dto.request.UserRequest;

@Service
public class CadastroService {
    private final AgendamentoRepository agendamentoRepository;

    public CadastroService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public void criarAgendamento(UserRequest body) {
        AgendamentoNovo agendamentoModel = new AgendamentoNovo(body);
        agendamentoRepository.save(agendamentoModel);
    }
}
