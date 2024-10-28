package br.com.fiap.healy.domain.controller.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Index {

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("index");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        mv.addObject("autenticado", authentication.isAuthenticated());
        return mv;
    }
    @GetMapping("/")
    public ModelAndView homeNull() {
        ModelAndView mv = new ModelAndView("redirect:/home");
        return mv;
    }

}
