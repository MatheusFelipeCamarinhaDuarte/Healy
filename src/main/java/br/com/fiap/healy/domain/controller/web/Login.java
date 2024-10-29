package br.com.fiap.healy.domain.controller.web;

import br.com.fiap.healy.domain.repository.ExameRepository;
import br.com.fiap.healy.domain.repository.UsuarioRepository;
import br.com.fiap.healy.domain.service.PessoaService;
import br.com.fiap.healy.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login {

    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }



}
