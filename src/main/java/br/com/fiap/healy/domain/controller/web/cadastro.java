package br.com.fiap.healy.domain.controller.web;

import br.com.fiap.healy.domain.controller.api.PessoaResource;
import br.com.fiap.healy.domain.dto.request.PessoaRequest;
import br.com.fiap.healy.domain.dto.response.PessoaResponse;
import br.com.fiap.healy.domain.entity.Pessoa;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class cadastro {


    @GetMapping("/cadastro")
        public ModelAndView cadastro(){
            ModelAndView mv = new ModelAndView("cadastro");
            mv.addObject("pessoa",new Pessoa());
            return mv;
        }

    @GetMapping("/cadastro/{tipo}")
    public String carregarFragmento(@PathVariable("tipo") String tipo, Model model) {
        if ("medico".equals(tipo)) {
            return "fragments/cadastro_medico :: conteudo-medico";
        } else {
            return "fragments/cadastro_paciente :: conteudo-paciente";
        }
    }


    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(@Valid PessoaRequest pessoa, BindingResult bd){
        if (bd.hasErrors()){
            return new ModelAndView("cadastro");
        } else{
            ModelAndView mv = new ModelAndView("index");
            PessoaResource pes = new PessoaResource();
            ResponseEntity<PessoaResponse> resp = pes.save(pessoa);
            mv.addObject("resposta", resp);
            return mv;

        }
    }


}
