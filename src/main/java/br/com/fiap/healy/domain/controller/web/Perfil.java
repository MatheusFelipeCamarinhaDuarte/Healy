package br.com.fiap.healy.domain.controller.web;

import br.com.fiap.healy.domain.entity.*;
import br.com.fiap.healy.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class Perfil {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    ExameRepository exameRepository;

    @Autowired
    ProfissionalSaudeRepository profissionalSaudeRepository;

    @Autowired
    TelefoneRepository telefoneRepository;

    @GetMapping("/perfil")
    public ModelAndView perfil() {
        ModelAndView mv = new ModelAndView("perfil");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("autenticado", auth.isAuthenticated());
        String username = auth.getName();
        Optional<Usuario> user = usuarioRepository.findByUsername(username);

        user.ifPresent(usuario -> mv.addObject("usuario", usuario));


        return mv;
    }

    @GetMapping("/perfil/deletar/{id}")
    public ModelAndView deletarpaciente(@PathVariable Long id, Model model) {
        boolean temAutorizacao = false;
        ModelAndView mv = new ModelAndView("index");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        mv.addObject("autenticado", authentication.isAuthenticated());
        if (!temAutorizacao) {
            model.addAttribute("errorMessage", "Você não tem autorização para esta ação");
            return mv;
        }
        return new ModelAndView("index");
    }

}
