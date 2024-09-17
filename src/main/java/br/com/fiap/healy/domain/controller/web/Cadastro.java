package br.com.fiap.healy.domain.controller.web;

import br.com.fiap.healy.domain.entity.Pessoa;
import br.com.fiap.healy.domain.entity.Tipo;
import br.com.fiap.healy.domain.entity.Usuario;
import br.com.fiap.healy.domain.repository.PessoaRepository;
import br.com.fiap.healy.domain.repository.UsuarioRepository;
import br.com.fiap.healy.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class Cadastro {

    @Autowired
    private UsuarioRepository usuarioRP;
    @Autowired
    private PessoaRepository pessoaRP;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/cadastro")
    public ModelAndView cadastro() {
        ModelAndView mv = new ModelAndView("cadastro");
        return mv;
    }


    @GetMapping("/cadastro-paciente")
    public ModelAndView cadastroPaciente() {

        ModelAndView mv = new ModelAndView("cadastro_paciente");
        mv.addObject("tipo_pessoa", Tipo.PC);
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @GetMapping("/cadastro-medico")
    public ModelAndView cadastroMedico(Model model) {
        boolean temAutorizacao = false;

        if (!temAutorizacao) {
            model.addAttribute("errorMessage", "Você não tem autorização para esta ação");
            return new ModelAndView("index");
        }

        return new ModelAndView("cadastro_medico");
    }


    @PostMapping("/cadastrar/paciente")
    public ModelAndView cadastrarPaciente(@Valid Usuario usuario, BindingResult bd) {
        if (bd.hasErrors()) {
            return new ModelAndView("cadastro_paciente");
        } else {
            usuarioService.save(usuario);
            Usuario user = usuarioRP.findByUsername(usuario.getUsername());
            ModelAndView mv = new ModelAndView("perfil");
            mv.addObject("usuario",user);
            return mv;

        }
    }


    @GetMapping("atualiza-paciente/{id}")
    public ModelAndView retornaPaginaAtualizaPaciente(@PathVariable Long id){
        Optional<Usuario> op = usuarioRP.findById(id);
        if(op.isPresent()){
            ModelAndView mv = new ModelAndView("atualiza_paciente");
            mv.addObject("usuario",new Usuario());
            return mv;
        } else {
            return new ModelAndView("redirect:/perfil");
        }
    }

    @PostMapping("/atualizar/paciente/{id}")
    public ModelAndView atualizaPaciente(@PathVariable Long id, @Valid Usuario usuario, BindingResult bd) {
        if (bd.hasErrors()) {
            ModelAndView mv = new ModelAndView("atualiza_paciente");
            return mv;
        } else {
            Optional<Usuario> opUser = usuarioRP.findById(id);
            if (opUser.isPresent()) {
                Usuario user = opUser.get();
                user = Usuario.builder()
                        .id(user.getId())
                        .pessoa(user.getPessoa())
                        .username(user.getUsername())
                        .senha(user.getSenha())
                        .build();
                usuarioRP.save(user);
                return new ModelAndView("redirect:/home");
            } else {
                ModelAndView mv = new ModelAndView("atualiza_cadastro");
                return mv;
            }
        }
    }

    @GetMapping("/deletar/paciente/{id}")
    public ModelAndView deletarpaciente(@PathVariable Long id) {
        Optional<Usuario> opUser = usuarioRP.findById(id);
        if (opUser.isPresent()) {
            Usuario user = opUser.get();
            usuarioRP.deleteById(id);

            pessoaRP.deleteById(user.getPessoa().getId());
            return new ModelAndView("redirect:/home");
        } else {
            return new ModelAndView("redirect:/home");
        }
    }

}
