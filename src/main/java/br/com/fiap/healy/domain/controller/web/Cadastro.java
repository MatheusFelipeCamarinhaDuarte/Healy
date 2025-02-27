package br.com.fiap.healy.domain.controller.web;

import br.com.fiap.healy.domain.entity.Pessoa;
import br.com.fiap.healy.domain.entity.Role;
import br.com.fiap.healy.domain.entity.Usuario;
import br.com.fiap.healy.domain.repository.PessoaRepository;
import br.com.fiap.healy.domain.repository.RoleRepository;
import br.com.fiap.healy.domain.repository.UsuarioRepository;
import br.com.fiap.healy.domain.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class Cadastro {

    @Autowired
    private UsuarioRepository usuarioRP;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/cadastro")
    public ModelAndView cadastro() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ModelAndView mv = new ModelAndView("cadastro");
        mv.addObject("autenticado", authentication.isAuthenticated());
        return mv;
    }


    @GetMapping("/cadastro-paciente")
    public ModelAndView cadastroPaciente() {

        ModelAndView mv = new ModelAndView("cadastro_paciente");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("autenticado", authentication.isAuthenticated());

        mv.addObject("usuario", new Usuario());
        return mv;
    }


    @PostMapping("/cadastrar/paciente")
    public ModelAndView cadastrarPaciente(@Valid Usuario usuario, BindingResult bd) {
        if(usuarioRP.findByUsername(usuario.getUsername()).isPresent()){
            ModelAndView mv =  new ModelAndView("cadastro_paciente");
            mv.addObject("errorMessage","Já  existe um usuário com este Username");
            Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
            mv.addObject("autenticado",autenticado.isAuthenticated());
            return mv;
        }


        if (bd.hasErrors()) {
            ModelAndView mv =  new ModelAndView("cadastro_paciente");
            mv.addObject("errorMessage","Já  existe um usuário com este Username");
            Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
            mv.addObject("autenticado",autenticado.isAuthenticated());
            return mv;

        } else {
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            Role role = roleRepository.findAllByNome("ROLE_PACIENTE");
            Set<Role> roleSet= new HashSet();
            roleSet.add(role);
            usuario.setRoles(roleSet);
            usuarioService.save(usuario);

            SecurityContextHolder.clearContext();
            ModelAndView mv = new ModelAndView("login");
            return mv;

        }
    }




    @PostMapping("/atualizar/paciente")
    public ModelAndView atualizaPaciente(@Valid Usuario usuario, BindingResult bd) {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        String username = autenticado.getName();
        Optional<Usuario> opUser = usuarioRP.findByUsername(username);
            Usuario user = opUser.get();
            Pessoa pessoa = user.getPessoa();
            pessoa.setNome(usuario.getPessoa().getNome());
            pessoa.setTelefone(usuario.getPessoa().getTelefone());
            pessoa.setEmail(usuario.getPessoa().getEmail());
            user = Usuario.builder()
                    .id(user.getId())
                    .pessoa(pessoa)
                    .username(user.getUsername())
                    .senha(passwordEncoder.encode(usuario.getSenha()))
                    .build();
            usuarioRP.save(user);
            ModelAndView mv = new ModelAndView("redirect:/perfil");
            return mv;
        }



}
