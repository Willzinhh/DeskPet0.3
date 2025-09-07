CREATE TABLE cliente_usuario (
                                 id SERIAL PRIMARY KEY,
                                 uuid UUID DEFAULT gen_random_uuid(),
                                 nome VARCHAR(100) NOT NULL,
                                 cpf VARCHAR(14) UNIQUE NOT NULL,
                                 cnpj VARCHAR(18) UNIQUE,
                                 telefone VARCHAR(20)NOT NULL,
                                 endereco TEXT,
                                 plano VARCHAR(50) NOT NULL,
                                 nome_empresa VARCHAR(100)
);

CREATE TABLE usuario (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         senha_hash VARCHAR(255) NOT NULL,  -- senha criptografada --
                         ativo BOOLEAN DEFAULT TRUE,
                         cliente_usuario_id INTEGER REFERENCES cliente_usuario(id) ON DELETE CASCADE
);
