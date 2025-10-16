
CREATE TABLE owner (
                       id SERIAL PRIMARY KEY,
                       uuid UUID DEFAULT gen_random_uuid(),
                       nome VARCHAR(255) NOT NULL,
                       cpf VARCHAR(14) NOT NULL,
                       cnpj VARCHAR(18),
                       telefone VARCHAR(14) NOT NULL,
                       endereco TEXT,
                       nome_empresa VARCHAR(255),
                       plano VARCHAR(50) NOT NULL
);


CREATE TABLE usuario (

                      id SERIAL PRIMARY KEY,
                      nome VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      senha VARCHAR(255),
                      permissao VARCHAR(100),

    -- Chave Estrangeira para a tabela owner (@ManyToOne)
    -- O CASCADE e ORPHAN REMOVAL são definidos pelo JPA, mas aqui garantimos a referência.
                      owner_id BIGINT , -- Geralmente não é nulo se o ManyToOne não for opcional

    -- Definição da Chave Estrangeira
                      CONSTRAINT fk_user_owner
                          FOREIGN KEY (owner_id)
                              REFERENCES owner (id)
                              -- ON DELETE CASCADE é comum quando o 'owner' é removido, mas o JPA/Hibernate configura isso.
                              -- Para garantir a consistência do modelo JPA (orphanRemoval = true e CascadeType.ALL),
                              -- ON DELETE CASCADE pode ser apropriado, dependendo da sua regra de negócio.
                              -- Adicionando ON DELETE CASCADE aqui:
                              ON DELETE CASCADE
);