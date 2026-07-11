package com.bemmaistech.ms_app_barbearia_agendamento.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record UserRequest(
    String nome,
    String telefone,
    List<String> servicos,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime data_criacao,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime data_agendamento
) {
}
