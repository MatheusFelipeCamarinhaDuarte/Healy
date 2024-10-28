package br.com.fiap.healy.domain;

import br.com.fiap.healy.domain.entity.*;
import br.com.fiap.healy.domain.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@EntityScan
@ComponentScan
@SpringBootApplication
public class HealyApplication {

        public static void main(String[] args) {
            SpringApplication.run(HealyApplication.class, args);
        }

}
