package br.com.fiap.healy.domain.controller.web;

import br.com.fiap.healy.domain.entity.Exame;
import br.com.fiap.healy.domain.entity.Role;
import br.com.fiap.healy.domain.entity.Usuario;
import br.com.fiap.healy.domain.repository.ExameRepository;
import br.com.fiap.healy.domain.repository.UsuarioRepository;
import br.com.fiap.healy.domain.service.ExameService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class Exames {

    @Autowired
    private ExameService exameService;

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/adicionar-exame")
    public ModelAndView adicionarExame() {
        ModelAndView mv = new ModelAndView("novo_exame");
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        String username = autenticado.getName();
        Optional<Usuario> user = usuarioRepository.findByUsername(username);
        mv.addObject("autenticado",autenticado.isAuthenticated());
        mv.addObject("exame", new Exame());

        return mv;
    }


    @PostMapping("/cadastrar/exame")
    public ModelAndView cadastrarPaciente(@Valid Exame exame, BindingResult bd) {
        if (bd.hasErrors()) {
            ModelAndView mv =  new ModelAndView("novo_exame");
            Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
            mv.addObject("autenticado",autenticado.isAuthenticated());
            return mv;

        } else {
            exame.setMesAteCrise(0);
            exame.setAnoAteCrise(0);
            Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
            String username = autenticado.getName();
            Optional<Usuario> user = usuarioRepository.findByUsername(username);
            user.ifPresent(usuario -> exame.setPessoa(usuario.getPessoa()));
            exameService.save(exame);
            SecurityContextHolder.clearContext();
            ModelAndView mv = new ModelAndView("redirect:/perfil");
            return mv;

        }
    }
    @GetMapping("/exame/deletar/{id}")
    public ModelAndView deletarExame(@PathVariable Long id, Model model) {
        exameRepository.deleteById(id);
        ModelAndView mv = new ModelAndView("redirect:/perfil");
        return mv;
    }
}
