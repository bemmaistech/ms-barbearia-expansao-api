package com.bemmaistech.ms_app_barbearia_agendamento.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRequestDTO {

    private String nome;
    private String telefone;
    private String servico;
    private String data_criacao;

}
