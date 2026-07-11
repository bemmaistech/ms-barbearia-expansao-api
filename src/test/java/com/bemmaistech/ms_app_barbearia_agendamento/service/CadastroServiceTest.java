package com.bemmaistech.ms_app_barbearia_agendamento.service;

import com.bemmaistech.ms_app_barbearia_agendamento.dto.request.UserRequest;
import com.bemmaistech.ms_app_barbearia_agendamento.entity.AgendamentoNovo;
import com.bemmaistech.ms_app_barbearia_agendamento.repository.AgendamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastroServiceTest {

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @InjectMocks
    private CadastroService cadastroService;

    @Test
    void criarAgendamento_shouldSaveEntityWithMappedFields() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        UserRequest request = new UserRequest(
                "Joao Silva",
                "11999999999",
                List.of("Corte"),
                now,
                now.plusDays(1)
        );

        // Act
        cadastroService.criarAgendamento(request);

        // Assert
        ArgumentCaptor<AgendamentoNovo> captor = ArgumentCaptor.forClass(AgendamentoNovo.class);
        verify(agendamentoRepository, times(1)).save(captor.capture());

        AgendamentoNovo saved = captor.getValue();
        assertNotNull(saved);
        assertEquals(request.nome(), saved.getNome());
        assertEquals(request.telefone(), saved.getTelefone());
        assertEquals(request.servicos(), saved.getServicos());
        assertEquals(request.data_agendamento(), saved.getData_agendamento());
        assertEquals(request.data_criacao(), saved.getData_criacao());
    }
}
