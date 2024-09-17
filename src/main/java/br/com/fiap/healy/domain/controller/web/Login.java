package br.com.fiap.healy.domain.controller.web;

import br.com.fiap.healy.domain.entity.Exame;
import br.com.fiap.healy.domain.entity.Pessoa;
import br.com.fiap.healy.domain.entity.Usuario;
import br.com.fiap.healy.domain.repository.ExameRepository;
import br.com.fiap.healy.domain.repository.PessoaRepository;
import br.com.fiap.healy.domain.repository.UsuarioRepository;
import br.com.fiap.healy.domain.service.PessoaService;
import br.com.fiap.healy.domain.service.UsuarioService;
import br.com.fiap.healy.domain.dto.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @PostMapping("/entrar/login")
    public ModelAndView login(@ModelAttribute LoginRequest loginRequest, Model model) {

        boolean isValidUser = usuarioService.validarUsuario(loginRequest.getUsername(), loginRequest.getSenha());
        if (isValidUser) {
            Usuario usuario = usuarioRepository.findByUsername(loginRequest.getUsername());

            ModelAndView mv = new ModelAndView("perfil");
            List<Exame> listaExames = exameRepository.findAllByPessoa(usuario.getPessoa());

            mv.addObject("usuario", usuario);
            mv.addObject("lista_exames", listaExames);
            return mv;
        } else {
            // Adiciona a mensagem de erro ao modelo
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return new ModelAndView("login");
        }
    }

}
