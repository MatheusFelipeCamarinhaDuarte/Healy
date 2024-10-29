INSERT INTO TB_TELEFONE (ddi, ddd, numero) VALUES ('55', '11', '991512776');
INSERT INTO TB_TELEFONE (ddi, ddd, numero) VALUES ('55', '21', '999999999');
INSERT INTO TB_TELEFONE (ddi, ddd, numero) VALUES ('55', '19', '999999998');
INSERT INTO TB_TELEFONE (ddi, ddd, numero) VALUES ('1', '12', '999999997');

INSERT INTO TB_PESSOA (NM_PESSOA, EMAIL, DT_NASCIMENTO, CPF,TELEFONE) VALUES ('Matheus', 'matheus@gmail.com', '2005-04-05', '12345678910',1);
INSERT INTO TB_PESSOA (NM_PESSOA, EMAIL, DT_NASCIMENTO, CPF,TELEFONE) VALUES ('Ana', 'ana@gmail.com', '2005-04-05', '12345678911',2);
INSERT INTO TB_PESSOA (NM_PESSOA, EMAIL, DT_NASCIMENTO, CPF,TELEFONE) VALUES ('Mirelly', 'mirelly@gmail.com', '2006-04-05', '12345678912',3);
INSERT INTO TB_PESSOA (NM_PESSOA, EMAIL, DT_NASCIMENTO, CPF,TELEFONE) VALUES ('Beatriz', 'beatriz@gmail.com', '2007-04-05', '12345678913',4);

INSERT INTO TB_USUARIO (USERNAME, SENHA, PESSOA) VALUES ('Matheus', '$2a$12$RFh.2.xyOrxXzdVBv1p9GepT6xhY0b3pg5ue/KsELW5DpCT6MDmMy', (SELECT ID_PESSOA FROM TB_PESSOA WHERE NM_PESSOA = 'Matheus'));
INSERT INTO TB_USUARIO (USERNAME, SENHA, PESSOA) VALUES ('Ana', '$2a$12$RFh.2.xyOrxXzdVBv1p9GepT6xhY0b3pg5ue/KsELW5DpCT6MDmMy', (SELECT ID_PESSOA FROM TB_PESSOA WHERE NM_PESSOA = 'Ana'));
INSERT INTO TB_USUARIO (USERNAME, SENHA, PESSOA) VALUES ('Mirelly', '$2a$12$RFh.2.xyOrxXzdVBv1p9GepT6xhY0b3pg5ue/KsELW5DpCT6MDmMy', (SELECT ID_PESSOA FROM TB_PESSOA WHERE NM_PESSOA = 'Mirelly'));
INSERT INTO TB_USUARIO (USERNAME, SENHA, PESSOA) VALUES ('Beatriz', '$2a$12$RFh.2.xyOrxXzdVBv1p9GepT6xhY0b3pg5ue/KsELW5DpCT6MDmMy', (SELECT ID_PESSOA FROM TB_PESSOA WHERE NM_PESSOA = 'Beatriz'));
-- senha Admin
insert into role(nome) values('ROLE_ADMIN');
insert into role(nome) values('ROLE_MEDICO');
insert into role(nome) values('ROLE_PACIENTE');
insert into role(nome) values('ROLE_USER');
insert into usuario_roles_associacao(id_role,id_usuario) values(1,1);
insert into usuario_roles_associacao(id_role,id_usuario) values(2,3);
insert into usuario_roles_associacao(id_role,id_usuario) values(3,1);
insert into usuario_roles_associacao(id_role,id_usuario) values(3,2);
insert into usuario_roles_associacao(id_role,id_usuario) values(3,4);

INSERT INTO TB_DOCUMENTO_SAUDE (estado, sigla, numero) VALUES ('SP', 'CRM', '123456789999');
INSERT INTO TB_PROFISSIONAL_SAUDE (DOCUMENTO, PESSOA) VALUES ((SELECT ID_DOCUMENTO_SAUDE FROM TB_DOCUMENTO_SAUDE WHERE NUMERO = '123456789999'), (SELECT ID_PESSOA FROM TB_PESSOA WHERE NM_PESSOA = 'Mirelly'));



INSERT INTO TB_EXAME (sexo, HIST_DIABETES, HIST_DOENC_CORONARIA, HIST_DOENC_VASCULAR, HIST_FUMO, HIST_HIPERTENSAO, HIST_DISLIPIDEMIA, HIST_OBESIDADE, REMED_DISLIPIDEMIA, REMED_DIABETES, REMED_HIPERTENSAO, REMED_ACEI_ARB, NVL_COLESTEROL, NVL_CREATINA, EXAM_EGFRB, PRES_SISTOLICA, PRES_DIASTOLICA, INDC_MASSA_CORP, MESES_ATE_CRISE, ANOS_ATE_CRISE, PESSOA) VALUES ('M', false, true, false, true, true, false, false, false, false, true, false, 180.0, 0.9, 85.0, 120, 80, 25, 0, 0, (SELECT ID_PESSOA FROM TB_PESSOA WHERE NM_PESSOA = 'Matheus'));
INSERT INTO TB_EXAME (sexo, HIST_DIABETES, HIST_DOENC_CORONARIA, HIST_DOENC_VASCULAR, HIST_FUMO, HIST_HIPERTENSAO, HIST_DISLIPIDEMIA, HIST_OBESIDADE, REMED_DISLIPIDEMIA, REMED_DIABETES, REMED_HIPERTENSAO, REMED_ACEI_ARB, NVL_COLESTEROL, NVL_CREATINA, EXAM_EGFRB, PRES_SISTOLICA, PRES_DIASTOLICA, INDC_MASSA_CORP, MESES_ATE_CRISE, ANOS_ATE_CRISE, PESSOA) VALUES ('F', true, false, false, false, true, true, false, true, false, true, true, 220.0, 1.2, 60.0, 140, 90, 23, 0, 0, (SELECT ID_PESSOA FROM TB_PESSOA WHERE NM_PESSOA = 'Ana'));
INSERT INTO TB_EXAME (sexo, HIST_DIABETES, HIST_DOENC_CORONARIA, HIST_DOENC_VASCULAR, HIST_FUMO, HIST_HIPERTENSAO, HIST_DISLIPIDEMIA, HIST_OBESIDADE, REMED_DISLIPIDEMIA, REMED_DIABETES, REMED_HIPERTENSAO, REMED_ACEI_ARB, NVL_COLESTEROL, NVL_CREATINA, EXAM_EGFRB, PRES_SISTOLICA, PRES_DIASTOLICA, INDC_MASSA_CORP, MESES_ATE_CRISE, ANOS_ATE_CRISE, PESSOA) VALUES ('F', false, true, false, true, true, true, false, true, false, false, false, 240.0, 1.1, 65.0, 150, 95, 28, 0, 0, (SELECT ID_PESSOA FROM TB_PESSOA WHERE NM_PESSOA = 'Beatriz'));


-- INSERT INTO TB_PROFISSIONAL_PACIENTE (PROFISSIONAL, PESSOA) VALUES ((SELECT ID_PROFISSIONAL_SAUDE FROM TB_PROFISSIONAL_SAUDE WHERE PESSOA = (SELECT ID_PESSOA FROM TB_PESSOA WHERE NM_PESSOA = 'Mirelly')), (SELECT ID_PESSOA FROM TB_PESSOA WHERE NM_PESSOA = 'Matheus'));
-- INSERT INTO TB_PROFISSIONAL_PACIENTE (PROFISSIONAL, PESSOA) VALUES ((SELECT ID_PROFISSIONAL_SAUDE FROM TB_PROFISSIONAL_SAUDE WHERE PESSOA = (SELECT ID_PESSOA FROM TB_PESSOA WHERE NM_PESSOA = 'Mirelly')), (SELECT ID_PESSOA FROM TB_PESSOA WHERE NM_PESSOA = 'Ana'));
-- INSERT INTO TB_PROFISSIONAL_PACIENTE (PROFISSIONAL, PESSOA) VALUES ((SELECT ID_PROFISSIONAL_SAUDE FROM TB_PROFISSIONAL_SAUDE WHERE PESSOA = (SELECT ID_PESSOA FROM TB_PESSOA WHERE NM_PESSOA = 'Mirelly')), (SELECT ID_PESSOA FROM TB_PESSOA WHERE NM_PESSOA = 'Beatriz'));


