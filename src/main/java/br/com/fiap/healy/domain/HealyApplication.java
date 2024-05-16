package br.com.fiap.healy.domain;

import br.com.fiap.healy.domain.entity.*;
import br.com.fiap.healy.domain.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@SpringBootApplication
public class HealyApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger( HealyApplication.class);

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private TelefoneRepository telefoneRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProfissionalSaudeRepository profissionalSaudeRepository;
    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private DocumentoSaudeRepository documentoSaudeRepository;


    public static void main(String[] args) {
        SpringApplication.run( HealyApplication.class, args );
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        criarSistema();
    }

    private void criarSistema() {
        var matheus = Pessoa.builder()
                .nome("Matheus").email("matheus@gmail.com")
                .nascimento(LocalDate.of(2005, 4, 5))
                .cpf("12345678910").tipoPessoa(Tipo.PC).build();
        pessoaRepository.save(matheus);
        var ana = Pessoa.builder()
                .nome("Ana").email("ana@gmail.com")
                .nascimento(LocalDate.of(2005, 4, 5))
                .cpf("12345678911").tipoPessoa(Tipo.PC).build();
        pessoaRepository.save(ana);
        var mirelly = Pessoa.builder()
                .nome("Mirelly").email("mirelly@gmail.com")
                .nascimento(LocalDate.of(2006, 4, 5))
                .cpf("12345678912").tipoPessoa(Tipo.PF).build();
        pessoaRepository.save(mirelly);
        var beatriz = Pessoa.builder()
                .nome("Beatriz").email("beatriz@gmail.com")
                .nascimento(LocalDate.of(2007, 4, 5))
                .cpf("12345678913").tipoPessoa(Tipo.PC).build();
        pessoaRepository.save(beatriz);

        var userMatheus = Usuario.builder().user("Matheus").senha("Matheus@1").pessoa(matheus).build();
        usuarioRepository.save(userMatheus);
        var userAna = Usuario.builder().user("Ana").senha("AnaAna@1").pessoa(ana).build();
        usuarioRepository.save(userAna);
        var userMirelly = Usuario.builder().user("Mirelly").senha("Mirelly@1").pessoa(mirelly).build();
        usuarioRepository.save(userMirelly);
        var userBeatriz = Usuario.builder().user("Beatriz").senha("Beatriz@1").pessoa(beatriz).build();
        usuarioRepository.save(userBeatriz);

        var telefoneMatheus = Telefone.builder().ddi("55").ddd("11").numero("991512776").pessoa(matheus).build();
        telefoneRepository.save(telefoneMatheus);
        var telefoneAna = Telefone.builder().ddd("21").ddi("55").numero("999999999").pessoa(ana).build();
        telefoneRepository.save(telefoneAna);
        var telefoneMirelly = Telefone.builder().ddi("55").ddd("19").numero("999999998").pessoa(mirelly).build();
        telefoneRepository.save(telefoneMirelly);
        var telefoneBeatriz = Telefone.builder().ddi("1").ddd("12").numero("999999997").pessoa(beatriz).build();
        telefoneRepository.save(telefoneBeatriz);

        var exameMatheus = Exame.builder().sexo("M").idade(30)
                .histDiabetes(false).histDoencaCoronaria(true).histDoencaVascular(false)
                .histFumo(true).histHipertensao(true).histDislipidemia(false).histObesidade(false)
                .remDislipidemia(false).remDiabetes(false).remHipertensao(true).remACEIeARB(false)
                .nvlColesterol(180.0).nvlCreatina(0.9).exameGFRB(85.0).presSistolica(120).presDiastolica(80)
                .indiceMassa(25).mesAteCrise(0).anoAteCrise(0).pessoa(matheus).build();
        exameRepository.save(exameMatheus);
        var exameAna = Exame.builder().sexo("F").idade(45)
                .histDiabetes(true).histDoencaCoronaria(false).histDoencaVascular(false)
                .histFumo(false).histHipertensao(true).histDislipidemia(true).histObesidade(false)
                .remDislipidemia(true).remDiabetes(false).remHipertensao(true).remACEIeARB(true)
                .nvlColesterol(220.0).nvlCreatina(1.2).exameGFRB(60.0).presSistolica(140).presDiastolica(90)
                .indiceMassa(23).mesAteCrise(0).anoAteCrise(0).pessoa(ana).build();
        exameRepository.save(exameAna);
        var exameBeatriz = Exame.builder().sexo("F").idade(55)
                .histDiabetes(false).histDoencaCoronaria(true).histDoencaVascular(false)
                .histFumo(true).histHipertensao(true).histDislipidemia(true).histObesidade(false)
                .remDislipidemia(true).remDiabetes(false).remHipertensao(false).remACEIeARB(false)
                .nvlColesterol(240.0).nvlCreatina(1.1).exameGFRB(65.0).presSistolica(150).presDiastolica(95)
                .indiceMassa(28).mesAteCrise(0).anoAteCrise(0).pessoa(beatriz).build();
        exameRepository.save(exameBeatriz);

        var documentoMirelly = DocumentoSaude.builder().estado("SP").sigla("CRM").numero("123456789999").build();

        Set<Pessoa> pacientes = new LinkedHashSet<>();
        pacientes.add(matheus);
        pacientes.add(beatriz);
        pacientes.add(ana);
        var medicaMirelly = ProfissionalSaude.builder().documento(documentoMirelly).pessoa(mirelly).pacientes(pacientes).build();
        profissionalSaudeRepository.save(medicaMirelly);
    }
}
