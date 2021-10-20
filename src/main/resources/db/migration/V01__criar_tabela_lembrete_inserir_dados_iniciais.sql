CREATE TABLE lembrete
(
   id bigserial NOT NULL PRIMARY KEY,
   conteudo character varying(255) NOT NULL,
   prioridade character varying(20) CHECK ( prioridade in ('BAIXA', 'MEDIA', 'ALTA')) NOT NULL
);

INSERT INTO lembrete(conteudo, prioridade) VALUES ('Reunião com diretoria', 'ALTA');
INSERT INTO lembrete(conteudo, prioridade) VALUES ('Pagar as contas', 'MEDIA');
INSERT INTO lembrete(conteudo, prioridade) VALUES ('Passear com cachorro', 'BAIXA');