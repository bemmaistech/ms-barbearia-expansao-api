package com.bemmaistech.ms_app_barbearia_agendamento.service;
import com.bemmaistech.ms_app_barbearia_agendamento.model.AgendamentoModel;
import com.bemmaistech.ms_app_barbearia_agendamento.repository.CadastroRepository;
import org.springframework.stereotype.Service;
import com.bemmaistech.ms_app_barbearia_agendamento.dto.UserRequestDTO;


@Service
public class CadastroService {
   private final CadastroRepository cadastroRepository;
   public CadastroService(CadastroRepository cadastroRepository) {
       this.cadastroRepository = cadastroRepository;
   }


   public String adicionarDadosService(UserRequestDTO body){
       AgendamentoModel agendamentoModel = new AgendamentoModel();
       agendamentoModel.setNome(body.getNome().toUpperCase());
       agendamentoModel.setTelefone(body.getTelefone().toUpperCase());
       agendamentoModel.setServicos(body.getServicos());
       agendamentoModel.setData_criacao(body.getData_criacao());

       if (body.getTelefone().length() == 11){
           cadastroRepository.save(agendamentoModel);
            return "Cadastro realizado com sucesso:  " + "\nID: "+agendamentoModel.getId() + "\nNome: "+agendamentoModel.getNome();
       }
       else {
           return "Numero invalido";
       }

    }
}
