# HEALY ⚕️
***Your healf AI***

Aplicação em desenvolvimento desde 28/03/2024*


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
## Diagrama de classe
![diagrama.jpg](documentacao%2Fdiagramas%2Fdiagrama.jpg)

## Diagrama de entidade e relacionamento (DER)

<a id="_Picth"></a>

# 4 - Nossa proposta
[Veja aqui!](#https://www.youtube.com)

<a id="_Explicacoes"></a>

# 5 - Explicações de classes
## 5.1. Area médica
### Nome
Deve ser unico para que não haja repetições de diferentes áreas, assim, sempre sendo diferentes como neurologia, cardiologia ou afins.

### Descricao
Nele contém a descrição do que cada área abrenge.

## 5.2. Histórico médico
    Nesta classe, por enquanto, há atributos provisórios até que se decidam
    quais serão pertinentes a ela de acordo com o modelo de previsão em IA.

## 5.3. Paciente
### User
Deve ser unico e composto por 2 letras inicialmente e 8 números após.

### Senha
Regras de caracterização da senha ainda serão definidas.

### CPF
Deve ser unico, para que não seja possível a entrada de mais de 1 paciente.

### Pessoa
Herdará os atributos cadastrados de uma pessoa.

### Plano
Após feita a análise, será gerado um plano personalizado para cada paciente, contendo apenas as coisas necessárias para ele. atributo será preenchido separado 

### Histórico médico
Deverá ser adicionado após o cadastro, contendo as informações necessárias para a previsão com a IA

## 5.4. Pessoa
### Nome
Guarda o nome completo do paciente.

### Email
Deve ser unico para evitar cadastro com o mesmo e-mail na plataforma.

### Telefone
Guarda o telefone para contato com o paciente.

### Data de nacimento
Um LocalDate que guarda a data de nascimento do paciente.

## 5.5. Plano de saude
### Cobertura
Guarda uma lista de quais as areas da saude que o plano cobre.

### Valor
Guarda o valor final do plano, dado pela IA de previsão.

## 5.6. Proficional da saude
### User
Deve ser unico e composto por 2 letras inicialmente e 8 números após.

### Senha
Regras de caracterização da senha ainda serão definidas.

### CRM
Deve ser unico, para que não seja possível a entrada de mais de 1 CRM do proficional de saude.

### Pessoa
Herdará os atributos cadastrados de uma pessoa.

### Paciente
Guarda uma lista dos pacientes atuais. 

<a id="_Endpoint"></a>

# 6 -  Endpoints


*NOTA¹: Tive  problemas com o git e tive que passar o projeto parte por parte novamente.
