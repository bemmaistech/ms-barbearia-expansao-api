package com.bemmaistech.ms_app_barbearia_agendamento.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record UserRequest(
    @NotBlank(message = "Nome não pode ser vazio")
    String nome,
    
    @NotBlank(message = "Telefone não pode ser vazio")
    String telefone,
    
    @NotEmpty(message = "Serviços não podem estar vazios")
    List<String> servicos,
    
    @NotNull(message = "Data de criação é obrigatória")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime data_criacao,
    
    @NotNull(message = "Data de agendamento é obrigatória")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime data_agendamento
) {
}
