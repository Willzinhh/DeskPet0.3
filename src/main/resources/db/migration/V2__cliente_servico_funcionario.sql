CREATE TABLE clientes (
      id SERIAL PRIMARY KEY,
      nome VARCHAR(255) NOT NULL,
      telefone VARCHAR(20) NOT NULL,
      cpf VARCHAR(14) UNIQUE,
      endereco TEXT,
      data_criacao TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,

      owner_id INTEGER NOT NULL, -- Chave Estrangeira

      CONSTRAINT fk_cliente_owner
          FOREIGN KEY (owner_id)
              REFERENCES owner (id)
              ON DELETE CASCADE
);


CREATE TABLE servico (
     id SERIAL PRIMARY KEY,
     nome VARCHAR(100) NOT NULL,
     descricao TEXT,
     valor NUMERIC(10, 2) NOT NULL,
     tempo VARCHAR(5) NOT NULL, -- Formato 'HH:MM'

     owner_id INTEGER NOT NULL,

     CONSTRAINT fk_servico_owner
         FOREIGN KEY (owner_id)
             REFERENCES owner (id)
             ON DELETE CASCADE
);

CREATE TABLE funcionario (
     id SERIAL PRIMARY KEY,
     nome VARCHAR(255) NOT NULL,
     cpf VARCHAR(14) UNIQUE,
     telefone VARCHAR(20),
     cargo VARCHAR(100),
     salario NUMERIC(10, 2),
     ativo BOOLEAN,

     owner_id INTEGER NOT NULL,

     CONSTRAINT fk_funcionario_owner
         FOREIGN KEY (owner_id)
             REFERENCES owner (id)
             ON DELETE CASCADE
);