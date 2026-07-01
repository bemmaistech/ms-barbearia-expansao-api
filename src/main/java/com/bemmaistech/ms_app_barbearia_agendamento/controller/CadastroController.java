package com.bemmaistech.ms_app_barbearia_agendamento.controller;

import com.bemmaistech.ms_app_barbearia_agendamento.service.CadastroService;
import com.bemmaistech.ms_app_barbearia_agendamento.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired // injeção de dependencia, com o autowired não é necessario estancia a classe que estou chamado
    private CadastroService cadastroService;

    @GetMapping // define o tipo da api
    public String visualizarCadastro() {

        return "API GET - ONLINE";

        }

    @PostMapping()
    public String adicionarDados(@RequestBody UserRequestDTO body){


        return cadastroService.insertCadastro(body);
    }
}