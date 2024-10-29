package br.com.fiap.healy.domain.config.servicos;

import br.com.fiap.healy.domain.entity.Usuario;
import br.com.fiap.healy.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.stream.Collectors;

@Service
public class UsuarioUserDateilsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new User(usuario.getUsername(),usuario.getSenha(),usuario.getRoles()
                .stream().map(role-> new SimpleGrantedAuthority(role.getNome())).collect(Collectors.toList()));
    }
}
