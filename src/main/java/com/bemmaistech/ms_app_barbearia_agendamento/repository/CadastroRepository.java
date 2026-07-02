package com.bemmaistech.ms_app_barbearia_agendamento.repository;
import com.bemmaistech.ms_app_barbearia_agendamento.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CadastroRepository extends JpaRepository<AgendamentoModel, Long> {


}