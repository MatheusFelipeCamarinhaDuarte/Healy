package br.com.fiap.healy.domain.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Exame {

    @GetMapping("/adicionar-exame")
    public ModelAndView adicionarExame() {
        ModelAndView mv = new ModelAndView("novo_exame");
        return mv;
    }
}
