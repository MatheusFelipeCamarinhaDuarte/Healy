# HEALY ⚕️
***Your healf AI***

O projeto Healy foi baseado em um projeto para a prévia detecção de algumas doenças com bases em exames rotineiros. A IA tem como intuito complementar e auxiliar o profissional de saúde com diagnósticos e predições de possíveis doenças, podendo otimizar o tempo do doutor.

Aplicação em desenvolvimento desde 28/03/2024*
### Link para o GIT
https://github.com/MatheusFelipeCamarinhaDuarte/Healy
### LINK para o Trello
https://trello.com/b/0GSQQFgK/gestao-das-sprints
![image](https://github.com/MatheusFelipeCamarinhaDuarte/Healy/assets/128056372/1e481ca9-4a84-4981-9c37-108c1692a965)


# Sumário

[1 - Integrantes](#_Integrantes)

[2 - Instruções de como rodar a aplicação](#_Instruicoes)

[3 - Diagramas](#_Diagramas)

[4 - Nossa proposta](#_Picth)

[5 - Explicações de classes](#_Explicacoes)

[6 - Endpoints](#_Endpoint)


<a id="_Integrantes"></a>

# 1 - Integrantes
    Matheus Felipe Camarinha Duarte
    Mirelly Ribeiro Azevedo
    Gabriel Francisco Lobo

<a id="_Instruicoes"></a>

# 2 - Instrucoes de como rodar a aplicação
Enrtar em:

src > main > java > br.com.fiap.healy

e executar o arquivo HealyApplication.Java

O projeto estará fluindo a partir dai!
agora basta retirar o arquivo do postman que foi exportado para:

documentacao > requisicoes-json

lá dentro encontrará o arquivo com os GET e POSTs
sendo assim, basta rodar no postman.

<a id="_Diagramas"></a>

# 3 - Diagramas
## Diagrama do projeto Java

![diagrama3.jpg](documentacao%2Fdiagramas%2Fdiagrama_do_projeto.png)

## Diagrama de classe
![diagrama1.jpg](documentacao%2Fdiagramas%2Fdiagrama_classe.png)


<a id="_Picth"></a>

# 4 - Nossa proposta
Após algumas alterações na proposta, decidimos mudar algumas coisas.
- Agora. ao invés de oferecer um plano com determinadas áreas da saude, criaremos um IA que, de acordo com informações de exames,
vejam a probabilidade do desenvolvimento de determinada doença, e alerte ao médico quais as chanches que o paciente tem de desenvolver
aquela patologia.
- Ao invés de enviar os dados de doenças pré-existentes, serão enviados dados de exames recentes feitos pelo paciente.
<a id="_Explicacoes"></a>

# 5 - Explicações de classes
## 5.1. Documento Saude
Todos os atributos desta classe fazem parte de uma única UK, que representa a regra de negócio para não permitir que haja mais de um documento com as mesmas informações
### Sigla
Aqui se destina a identificar o tipo de documento de saude (como CRM e afins).

### Estado
Aqui se coloca o estado emissor do documento

### Numero
Aqui se coloca por extenso em forma de String o número que se refere ao documento do profissional da saude

## 5.2. Exame
### Sexo
Nesta aba, se coloca o sexo biológico do paciente com "F" para feminino e "M" para masculino.

### Idade
Aqui, se coloca a idade do paciente.

### Atributos gerais
Nesta classe, todos os demais atributos se referem a resultados de exame e histórico médico que serão inseridos na IA.

## 5.3. Usuario
### User
Deve ser unico e composto por 2 letras inicialmente e 8 números após. Esta classse conta com um UK para evitar de ter um mesmo Username para mais de um usuário.

### Senha
Regras de caracterização da senha ainda serão definidas.

### Pessoa
Herdará os atributos cadastrados de uma pessoa. Nesta classe, existe uma UK responsável por tornar o atributo único, desta forma, não podendo se repetir uma mesma pessoa. 

## 5.4. Pessoa
### Nome
Guarda o nome completo do paciente.

### Email
Deve ser unico para evitar cadastro com o mesmo e-mail na plataforma.
Há verificação do formato do email.

### CPF
Aqui se coloca sem pontuação e em forma de string o número de CPF da pessoa e é um atributo único.

### Data de nacimento
Um LocalDate que guarda a data de nascimento do paciente.

### Tipo Pessoa
Aqui se define se uma determinada é Paciente(PC) ou Profissional da saude (PF). Este atributo é um Enum.

## 5.5. Profissional da saude
### Documento de saude
Deve ser unico, para que não seja possível a entrada de mais de 1 documento por profissional da saude. é uma classe a parte.

### Pessoa
Herdará os atributos cadastrados de uma pessoa e terá que ser único para não se repetir o cadastro numa mesma pessoa.

### Pacientes
Guarda uma lista dos pacientes atuais, fazendo uma Collection de Pessoas. 

## 5.6. Telefone
Os atributos "DDI", "DDD" e "NUMERO" desta classe fazem parte de uma única UK, que representa a regra de negócio para não permitir que haja mais de um telefone com as mesmas informações
### DDI
Se refere ao código identificador do pais de origem do número

### DDD
Se refere ao código identificador do estado de origem do número

### Numero
Aqui se coloca por extenso em forma de String o número telefônico sem DDD e DDI

### Pessoa
Aqui se coloca a qual pessoa pertence o número telefônico.

<a id="_Endpoint"></a>

# 6 -  Endpoints
### Pessoa
    localhost/pessoa - POST;
    localhost/pessoa - GET;
    localhost/pessoa/{id} - GET
    localhost/pessoa?nome= - GET;
    localhost/pessoa?email= - GET;
    localhost/pessoa?nascimento= - GET;
    localhost/pessoa?sigla= - GET;    

### Documento Saude
    localhost/documento-saude - POST;
    localhost/documento-saude - GET;
    localhost/documento-saude/{id} - GET
    localhost/documento-saude?estado= - GET
    localhost/documento-saude?sigla= - GET
    localhost/documento-saude?numero= - GET

### Profissional de saúde
    localhost/profissional-saude - POST;
    localhost/profissional-saude - GET;
    localhost/profissional-saude/{id} - GET
    localhost/profissional-saude?nome= - GET;
    localhost/profissional-saude?cpf= - GET;
    localhost/profissional-saude?email= - GET;
    localhost/profissional-saude?documento.sigla= - GET;
    localhost/profissional-saude?documento.estado= - GET;
    localhost/profissional-saude?documento.numero= - GET;

### Exame
    localhost/exame - POST;
    localhost/exame - GET;
    localhost/exame/{id} - GET
    localhost/exame?sexo - GET
    localhost/exame?idade - GET
    localhost/exame?indiceMassa - GET
    localhost/exame?mesAteCrise - GET
    localhost/exame?anoAteCrise - GET
    localhost/exame?pessoa.nome - GET
    localhost/exame?pessoa.email - GET

### Telefone
    localhost/telefone - POST;
    localhost/telefone - GET;
    localhost/telefone/{id} - GET
    localhost/telefone?ddd= - GET;
    localhost/telefone?ddi= - GET;
    localhost/telefone?numero= - GET;

### Usuario
    localhost/usuario - POST;
    localhost/usuario - GET;
    localhost/usuario/{id} - GET
    localhost/usuario?usuario= - GET;
    localhost/usuario?pessoa.nome= - GET;
    localhost/usuario?pessoa.email= - GET;

*NOTA¹: Tive  problemas com o git e tive que passar o projeto parte por parte novamente.
**NOTA²: Ainda a serem implementados.
