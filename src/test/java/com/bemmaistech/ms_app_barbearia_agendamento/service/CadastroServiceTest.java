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

        try {
            java.lang.reflect.Field nomeField = saved.getClass().getDeclaredField("nome");
            nomeField.setAccessible(true);
            assertEquals(request.nome(), nomeField.get(saved));

            java.lang.reflect.Field telefoneField = saved.getClass().getDeclaredField("telefone");
            telefoneField.setAccessible(true);
            assertEquals(request.telefone(), telefoneField.get(saved));

            java.lang.reflect.Field servicosField = saved.getClass().getDeclaredField("servicos");
            servicosField.setAccessible(true);
            assertEquals(request.servicos(), servicosField.get(saved));

            java.lang.reflect.Field dataAgendamentoField = saved.getClass().getDeclaredField("data_agendamento");
            dataAgendamentoField.setAccessible(true);
            assertEquals(request.data_agendamento(), dataAgendamentoField.get(saved));

            java.lang.reflect.Field dataCriacaoField = saved.getClass().getDeclaredField("data_criacao");
            dataCriacaoField.setAccessible(true);
            assertEquals(request.data_criacao(), dataCriacaoField.get(saved));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to access fields via reflection: " + e.getMessage());
        }
    }
}
