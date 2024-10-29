package br.com.fiap.healy.domain.controller.web;

import br.com.fiap.healy.domain.config.mensageria.ProdutorRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Mensageria {

    @Autowired
    private ProdutorRabbit produtorRabbit;

    @GetMapping("/retorna_pag_msg_rabbit")
    public ModelAndView retonaPagMsgRabbit(Model model) {
        ModelAndView mv = new ModelAndView("enviar_mensagem_rabbit");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("autenticado", auth.isAuthenticated());
        return mv;
    }

    @PostMapping("/enviar_mensagem_rabbit")
    public ModelAndView enviarMensagemRabbit(@RequestParam("mensagem") String msg, Model model) {
        produtorRabbit.enviarMensagem(msg);
        model.addAttribute("mensagem", "Mensagem enviada com sucesso");
        ModelAndView mv = new ModelAndView("enviar_mensagem_rabbit");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        mv.addObject("autenticado", auth.isAuthenticated());
        return mv;
    }

}
