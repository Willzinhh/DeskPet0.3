CREATE TABLE pet (
     id SERIAL PRIMARY KEY,
     nomepet VARCHAR(100),
     especie VARCHAR(50),
     raca VARCHAR(50),
     sexo CHAR(1),
     descricao TEXT,
     data_cricao DATE,

     tutor_id INTEGER NOT NULL,
     owner_id INTEGER NOT NULL,

     CONSTRAINT fk_pet_tutor
         FOREIGN KEY (tutor_id)
             REFERENCES clientes (id)
             ON DELETE CASCADE ,

     CONSTRAINT fk_pet_owner
         FOREIGN KEY (owner_id)
             REFERENCES owner (id)
             ON DELETE CASCADE
);


CREATE TABLE funcionario_servico (
     funcionario_id INTEGER NOT NULL,
     servico_id INTEGER NOT NULL,

     PRIMARY KEY (funcionario_id, servico_id),

     CONSTRAINT fk_fs_funcionario
         FOREIGN KEY (funcionario_id)
             REFERENCES funcionario (id)
             ON DELETE CASCADE, -- ON DELETE CASCADE: Deleta a relação se o funcionário for deletado

     CONSTRAINT fk_fs_servico
         FOREIGN KEY (servico_id)
             REFERENCES servico (id)
             ON DELETE CASCADE -- ON DELETE CASCADE: Deleta a relação se o serviço for deletado
);


CREATE TABLE agendamento (
     id SERIAL PRIMARY KEY,
     data DATE,
     horario TIME,
     observacao TEXT,
     status VARCHAR(50),
     pagamento VARCHAR(50),

     pet_id INTEGER NOT NULL,
     servico_id INTEGER NOT NULL,
     funcionario_id INTEGER NOT NULL,
     owner_id INTEGER NOT NULL,

     CONSTRAINT fk_agendamento_pet
         FOREIGN KEY (pet_id)
             REFERENCES pet (id)
             ON DELETE CASCADE ,

     CONSTRAINT fk_agendamento_servico
         FOREIGN KEY (servico_id)
             REFERENCES servico (id)
             ON DELETE CASCADE ,

     CONSTRAINT fk_agendamento_funcionario
         FOREIGN KEY (funcionario_id)
             REFERENCES funcionario (id)
             ON DELETE CASCADE ,

     CONSTRAINT fk_agendamento_owner
         FOREIGN KEY (owner_id)
             REFERENCES owner (id)
             ON DELETE CASCADE
);