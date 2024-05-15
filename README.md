# HEALY ⚕️
***Your healf AI***

Aplicação em desenvolvimento desde 28/03/2024*
### Link para o GIT
https://github.com/MatheusFelipeCamarinhaDuarte/Healy

# Sumário

[1 - Integrantes](#_Integrantes)

[2 - Instruções de como rodar a aplicação](#_Instruicoes)

[3 - Diagramas](#_Diagramas)

[4 - Nossa proposta em vídeo PITCH](#_Picth)

[5 - Explicações de classes](#_Explicacoes)

[6 - Endpoints](#_Endpoint)


<a id="_Integrantes"></a>

# 1 - Integrantes
    RM: 551401  Nome: Ana Luiza Fontes Franco
    RM: 551856  Nome: Beatriz Fon Ehnert de Santi
    RM: 552295  Nome: Matheus Felipe Camarinha Duarte
    RM: 98672   Nome: Mirelly Ribeiro Azevedo
    RM: 99708   Nome: Gabriel Francisco Lobo

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
[LINK](https://www.youtube.com/watch?v=5i9sSBTHdaI)

Após algumas alterações na proposta, decidimos mudar algumas coisas.
- Agora. ao imvés de oferecer um plano com determinadas áreas da saude, criaremos um IA que, de acordo com informações de exames,
vejam a probabilidade do desenvolvimento de determinada doença, e alerte ao médico quais as chanches que o paciente tem de desenvolver
aquela patologia.
- Ao invés de enviar os dados de doenças pré-existentes, serão enviados dados de exames recentes feitos pelo paciente.
<a id="_Explicacoes"></a>

# 5 - Explicações de classes
## 5.1. Documento Saude
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
Deve ser unico e composto por 2 letras inicialmente e 8 números após.

### Senha
Regras de caracterização da senha ainda serão definidas.

### Pessoa
Herdará os atributos cadastrados de uma pessoa.

## 5.4. Pessoa
### Nome
Guarda o nome completo do paciente.

### Email
Deve ser unico para evitar cadastro com o mesmo e-mail na plataforma.
Há verificação do formato do email.

### CPF
Aqui se coloca sem pontuação e em forma de string o número de CPF da pessoa.

### Data de nacimento
Um LocalDate que guarda a data de nascimento do paciente.

### Tipo Pessoa
Aqui se define se uma determinada pessoa é física ou Jurídica. Este atributo é um Enum.

## 5.5. Proficional da saude
### Documento de saude
Deve ser unico, para que não seja possível a entrada de mais de 1 documento por profissional da saude. é uma classe a parte.

### Pessoa
Herdará os atributos cadastrados de uma pessoa.

### Pacientes
Guarda uma lista dos pacientes atuais, fazendo uma Collection de Pessoas. 

## 5.6. Telefone
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
### Documento Saude
    localhost/documento-saude - GET;

    OUTROS**
    
    localhost/documento-saude - POST;
    localhost/documento-saude/{id} - GET

### Exame
    localhost/exame - GET;
    
    OUTROS**

    localhost/exame - POST;
    localhost/exame/{id} - GET

### Usuario
    localhost/usuario - GET;
    localhost/usuario?user= - GET;

    OUTROS**
    
    localhost/usuario - POST;
    localhost/usuario/{id} - GET

### Pessoa
    localhost/pessoa - GET;
    localhost/pessoa?nascimento= - GET;

    OUTROS**
    
    localhost/pessoa - POST;
    localhost/pessoa/{id} - GET

### Telefone
    localhost/telefone - GET;
    localhost/telefone?ddd= - GET;
    localhost/telefone?ddi= - GET;
    localhost/telefone - POST;
    localhost/telefone/{id} - GET

### Profissional de saúde
    localhost/profissional-saude - GET;
    localhost/profissional-saude?documento= - GET;

    OURTOS**
    
    localhost/profissional-saude - POST;
    localhost/profissional-saude/{id} - GET

*NOTA¹: Tive  problemas com o git e tive que passar o projeto parte por parte novamente.
**NOTA²: Ainda a serem implementados.
