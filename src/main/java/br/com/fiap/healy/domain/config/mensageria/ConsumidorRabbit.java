package br.com.fiap.healy.domain.config.mensageria;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class ConsumidorRabbit {

    @RabbitListener(queues = ConfigRabbitMQ.fila)
    public void lerMensagem(String msg) {
        ModelAndView mv = new ModelAndView("enviar_mensagem_rabbit");
        System.out.println("Mensagem recebida: " + msg);
    }

}
