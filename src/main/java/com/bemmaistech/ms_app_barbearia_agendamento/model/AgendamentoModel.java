package com.bemmaistech.ms_app_barbearia_agendamento.model;

import com.bemmaistech.ms_app_barbearia_agendamento.dto.UserRequestDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "agendamento")
public class AgendamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "servicos")
    private List<String> servicos;

    @Column(name = "data_criacao")
    private LocalDateTime data_criacao;

}
