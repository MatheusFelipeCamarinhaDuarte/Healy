package br.com.fiap.healy.domain.config.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    final List<String> urlsParaTodos = List.of("/css/**", "/js/**", "/images/**", "/webjars/**", "/static/**");
    final List<String> urlsPaciente = List.of("/");
    final List<String> urlsAdmin = List.of("/cadastrar/paciente", "/cadastro-paciente");

    public SecurityConfig() {

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers(urlsParaTodos.toArray(new String[0])).permitAll()  // Libera acesso a todos os recursos estáticos
                        .requestMatchers(urlsAdmin.toArray(new String[0])).hasAuthority("ROLE_ADMIN")
                        .requestMatchers(urlsPaciente.toArray(new String[0])).hasAuthority("ROLE_PACIENTE")
                        .anyRequest().authenticated())
                .formLogin((form) -> form.loginPage("/login").failureUrl("/login?erro=true")
                        .defaultSuccessUrl("/home").permitAll())
                .logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout=true").permitAll())
                .exceptionHandling((exception) -> exception.accessDeniedHandler(((request, response, accessDeniedException) -> {
                    response.sendRedirect("/acesso_negado");
                })));

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
