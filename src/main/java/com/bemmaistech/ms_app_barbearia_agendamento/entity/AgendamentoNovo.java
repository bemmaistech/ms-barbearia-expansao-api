package com.bemmaistech.ms_app_barbearia_agendamento.entity;

import com.bemmaistech.ms_app_barbearia_agendamento.dto.request.UserRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Table(name = "agendamento")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AgendamentoNovo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Adiconar ID do barbeiro

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "servicos")
    private List<String> servicos;

    @Column(name = "data_criacao")
    private LocalDateTime data_criacao;

    @Column(name = "data_agendamento", nullable = false)
    private LocalDateTime data_agendamento;

    public AgendamentoNovo(UserRequest body) {
        this.nome = body.nome();
        this.telefone = body.telefone();
        this.servicos = body.servicos();
        this.data_criacao = body.data_criacao();
        this.data_agendamento = body.data_agendamento();
    }
}
