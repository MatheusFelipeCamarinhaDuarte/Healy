package br.com.fiap.healy.domain.controller.web;

import br.com.fiap.healy.domain.entity.*;
import br.com.fiap.healy.domain.repository.*;
import br.com.fiap.healy.domain.service.ExameService;
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
    ExameService exameService;



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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<Usuario> user = usuarioRepository.findById(id);
        if (user.isPresent()) {
            exameService.removerExamesPorPessoa(user.get().getPessoa());
            usuarioRepository.deleteById(id);
            ModelAndView mv = new ModelAndView("login");
            mv.addObject("autenticado", authentication.isAuthenticated());
            return mv;

        } else {
            ModelAndView mv = new ModelAndView("acesso_negado");
            model.addAttribute("errorMessage", "Usuário inexistente");
            mv.addObject("autenticado", authentication.isAuthenticated());
            return mv;
        }

    }

    @GetMapping("/perfil/atualizar/{id}")
    public ModelAndView retornaPaginaAtualizaPaciente(@PathVariable Long id){
        Optional<Usuario> op = usuarioRepository.findById(id);
        if(op.isPresent()){
            ModelAndView mv = new ModelAndView("atualiza_paciente");
            mv.addObject("usuario",new Usuario());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            mv.addObject("autenticado", authentication.isAuthenticated());
            return mv;
        } else {
            return new ModelAndView("redirect:/acesso_negado");
        }
    }

}
