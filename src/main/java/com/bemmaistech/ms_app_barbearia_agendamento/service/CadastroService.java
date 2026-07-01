package com.bemmaistech.ms_app_barbearia_agendamento.service;

import org.springframework.stereotype.Service;
import com.bemmaistech.ms_app_barbearia_agendamento.dto.UserRequestDTO;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CadastroService {
   //CadastroRepository repository;

    public String insertCadastro(UserRequestDTO body) {
       String nome = body.getNome().toUpperCase();
       String telefone = body.getTelefone().toUpperCase();
       String servico = body.getData_criacao().toUpperCase();
       String data = body.getData_criacao().toUpperCase();

       body.setNome(nome);
       body.setTelefone(telefone);
       body.setServico(servico);
       body.setData_criacao(data);

       return "Informaçoes tratadas";
    }
}
