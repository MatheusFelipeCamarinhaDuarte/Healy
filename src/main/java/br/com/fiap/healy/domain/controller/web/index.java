package br.com.fiap.healy.domain.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class index {

    @GetMapping("/home")
    public ModelAndView home() {

        ModelAndView mv = new ModelAndView("index");
        return mv;
    }
    @GetMapping("/")
    public ModelAndView homeNull() {
        ModelAndView mv = new ModelAndView("redirect:/home");
        return mv;
    }

}
