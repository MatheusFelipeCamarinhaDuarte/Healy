package br.com.fiap.healy.domain.dto.request;

public record ExameRequest(
    String sexo,
    Boolean histDiabetes,
    Boolean histDoencaCoronaria,
    Boolean histDoencaVascular,
    Boolean histFumo,
    Boolean histHipertensao,
    Boolean histDislipidemia,
    Boolean histObesidade,
    Boolean remDislipidemia,
    Boolean remDiabetes,
    Boolean remHipertensao,
    Boolean remACEIeARB,
    Double nvlColesterol,
    Double nvlCreatina,
    Double exameGFRB,
    Integer presSistolica,
    Integer presDiastolica,
    Integer indiceMassa,
    Integer mesAteCrise,
    Integer anoAteCrise,
    AbstractRequest pessoa

) {
}