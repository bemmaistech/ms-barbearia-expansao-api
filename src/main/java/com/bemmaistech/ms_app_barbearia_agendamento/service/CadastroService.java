package com.bemmaistech.ms_app_barbearia_agendamento.service;

import com.bemmaistech.ms_app_barbearia_agendamento.entity.Agendamento;
import com.bemmaistech.ms_app_barbearia_agendamento.repository.AgendamentoRepository;
import com.bemmaistech.ms_app_barbearia_agendamento.exception.AgendamentoNaoEncontradoException;
import com.bemmaistech.ms_app_barbearia_agendamento.dto.request.UserRequest;
import com.bemmaistech.ms_app_barbearia_agendamento.dto.request.UpdateAgendamentoRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

@Service
public class CadastroService {
    private final AgendamentoRepository agendamentoRepository;

    public CadastroService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public Agendamento criarAgendamento(UserRequest request) {
        Agendamento agendamento = new Agendamento(
            null,
            request.nome(),
            request.telefone(),
            request.servicos(),
            request.data_criacao(),
            request.data_agendamento()
        );
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public Agendamento obterPorId(Long id) {
        return agendamentoRepository.findById(id)
            .orElseThrow(() -> new AgendamentoNaoEncontradoException(id));
    }

    public Agendamento alterarAgendamento(Long id, UpdateAgendamentoRequest request) {
        Agendamento agendamento = obterPorId(id);
        
        agendamento.setNome(request.nome());
        agendamento.setTelefone(request.telefone());
        agendamento.setServicos(request.servicos());
        agendamento.setData_agendamento(request.data_agendamento());
        
        return agendamentoRepository.save(agendamento);
    }

    public void deletarAgendamento(Long id) {
        Agendamento agendamento = obterPorId(id);
        agendamentoRepository.delete(agendamento);
    }

    public Map<String, List<String>> obterHorariosPorDia() {
        DateTimeFormatter dataFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter horaFmt = DateTimeFormatter.ofPattern("HH:mm");
        return agendamentoRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        a -> a.getData_agendamento().toLocalDate().format(dataFmt),
                        Collectors.mapping(a -> a.getData_agendamento().toLocalTime().format(horaFmt), Collectors.toList())
                ));
    }
}
