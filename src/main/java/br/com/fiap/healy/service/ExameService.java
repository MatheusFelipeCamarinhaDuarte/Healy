package br.com.fiap.healy.service;

import br.com.fiap.healy.dto.request.ExameRequest;
import br.com.fiap.healy.dto.response.ExameResponse;
import br.com.fiap.healy.entity.Exame;
import br.com.fiap.healy.entity.Pessoa;
import br.com.fiap.healy.repository.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class ExameService implements ServiceDTO<Exame, ExameRequest, ExameResponse> {
    @Autowired
    private ExameRepository repo;
    @Autowired
    private PessoaService pessoaService;

    @Override
    public Exame toEntity(ExameRequest dto) {
        Pessoa pessoa = null;
        if(Objects.nonNull(dto.pessoa())){
            pessoa = pessoaService.findById(dto.pessoa().id());
        }
        return Exame.builder()
                .sexo(dto.sexo())
                .idade(dto.idade())
                .histDiabetes(dto.histDiabetes())
                .histDoencaCoronaria(dto.histDoencaCoronaria())
                .histDoencaVascular(dto.histDoencaVascular())
                .histFumo(dto.histFumo())
                .histHipertensao(dto.histHipertensao())
                .histDislipidemia(dto.histDislipidemia())
                .histObesidade(dto.histObesidade())
                .remDislipidemia(dto.remDislipidemia())
                .remDiabetes(dto.remDiabetes())
                .remHipertensao(dto.remHipertensao())
                .remACEIeARB(dto.remACEIeARB())
                .nvlColesterol(dto.nvlColesterol())
                .nvlCreatina(dto.nvlColesterol())
                .exameGFRB(dto.exameGFRB())
                .presSistolica(dto.presSistolica())
                .presDiastolica(dto.presDiastolica())
                .indiceMassa(dto.indiceMassa())
                .mesAteCrise(dto.mesAteCrise())
                .anoAteCrise(dto.anoAteCrise())
                .pessoa(pessoa)
                .build();
    }

    @Override
    public ExameResponse toResponse(Exame e) {
        var pessoa = pessoaService.toResponse(e.getPessoa());

        return ExameResponse.builder()
                .id(e.getId())
                .sexo(e.getSexo())
                .idade(e.getIdade())
                .histDiabetes(e.getHistDiabetes())
                .histDoencaCoronaria(e.getHistDoencaCoronaria())
                .histDoencaVascular(e.getHistDoencaVascular())
                .histFumo(e.getHistFumo())
                .histHipertensao(e.getHistHipertensao())
                .histDislipidemia(e.getHistDislipidemia())
                .histObesidade(e.getHistObesidade())
                .remDislipidemia(e.getRemDislipidemia())
                .remDiabetes(e.getRemDiabetes())
                .remHipertensao(e.getRemHipertensao())
                .remACEIeARB(e.getRemACEIeARB())
                .nvlColesterol(e.getNvlColesterol())
                .nvlCreatina(e.getNvlColesterol())
                .exameGFRB(e.getExameGFRB())
                .presSistolica(e.getPresSistolica())
                .presDiastolica(e.getPresDiastolica())
                .indiceMassa(e.getIndiceMassa())
                .mesAteCrise(e.getMesAteCrise())
                .anoAteCrise(e.getAnoAteCrise())
                .pessoa(pessoa)
                .build();
    }

    @Override
    public Collection<Exame> findAll() {
        return repo.findAll();
    }

    @Override
    public Collection<Exame> findAll(Example<Exame> example) {
        return repo.findAll(example);
    }

    @Override
    public Exame findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Exame save(Exame e) {
        return repo.save(e);
    }
}
