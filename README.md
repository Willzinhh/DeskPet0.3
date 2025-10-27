# DeskPet RestAPI

## 🌟 Visão Geral do Projeto

O **DeskPet RestAPI** é o backend da aplicação DeskPet, projetada para gerenciar clínicas veterinárias, pet shops ou qualquer negócio focado em pets e seus tutores.

A API é construída com **Java (Spring Boot)** e utiliza **JPA/Hibernate** para persistência de dados. A documentação interativa completa é fornecida via **Swagger (OpenAPI 3)**.

## 🌟 Modelo de ER

<img src="https://raw.githubusercontent.com/Willzinhh/DeskPet0.3/main/Diagrama1.png" alt="Captura de tela" width="400" height="200" />

### 🔑 Principais Funcionalidades

* **Gerenciamento de Proprietários (Owners):** Cadastro e controle da conta principal e seus planos de assinatura (`Free`, `Basic`, `Premium`).
* **Gerenciamento de Usuários (Users/Funcionários):** Controle de acesso de colaboradores ligados a um Proprietário.
* **Gestão de Clientes e Pets:** Cadastro de tutores e seus respectivos animais.
* **Serviços:** Cadastro e manutenção dos serviços oferecidos (Banho, Tosa, Consulta, etc.).
* **Agendamentos:** Controle da agenda de serviços, incluindo status, horários e funcionários responsáveis.

## 🚀 Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot 3**
* **Spring Data JPA** (Hibernate)
* **Maven**
* **MySQL / PostgreSQL** (Banco de dados de preferência)
* **Swagger/OpenAPI 3** (Documentação da API)
* **Lombok** (Para simplificar a escrita de código boilerplate nos modelos)

## 🛠️ Configuração e Instalação

Siga os passos abaixo para configurar e rodar a API localmente.

### 1. Pré-requisitos

Certifique-se de ter instalado:

* JDK 17 ou superior
* Maven
* Um servidor de banco de dados (ex: MySQL ou Docker com a imagem do PostgreSQL).

### 2. Configuração do Banco de Dados

1.  Crie um banco de dados vazio (ex: `deskpet_db`).
2.  Atualize o arquivo `application.properties` (ou `application.yml`) com as credenciais do seu banco:

    ```properties
    # Exemplo para PostgreSQL
    spring.datasource.url=jdbc:postgresql://localhost:5432/deskpet_db
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

### 3. Execução do Projeto

1.  **Clone o repositório:**
    ```bash
    git clone [URL_DO_SEU_REPOSITORIO]
    cd DeskPet-RestAPI
    ```

2.  **Compile o projeto com Maven:**
    ```bash
    mvn clean install
    ```

3.  **Execute a aplicação:**
    ```bash
    mvn spring-boot:run
    ```
A aplicação estará rodando em `http://localhost:8080` (porta padrão do Spring Boot).

## 🗺️ Estrutura da API e Documentação

A API segue o padrão RESTful e possui a seguinte estrutura de endpoints base:

| Entidade | Endpoint Base | Operações Comuns |
| :--- | :--- | :--- |
| **Proprietários** | `/owner` | `POST`, `GET /listar`, `GET /{id}`, `PUT`, `DELETE /{id}` |
| **Usuários/Funcionários** | `/user` | `GET /listar`, `GET /{id}`, `PUT`, `DELETE /{id}` |
| **Clientes** | `/cliente` | `POST`, `GET /listar`, `GET /{id}`, `PUT`, `DELETE /{id}` |
| **Pets** | `/pet` | `POST`, `GET /listar`, `GET /{id}`, `PUT`, `DELETE /{id}` |
| **Serviços** | `/servico` | `POST`, `GET /listar`, `GET /{id}`, `PUT`, `DELETE /{id}` |
| **Agendamentos** | `/agendamento` | `POST`, `GET /listar`, `GET /{id}`, `PUT`, `DELETE /{id}` |

### 📕 Documentação Interativa (Swagger/OpenAPI)

Após iniciar o servidor, você pode acessar a documentação completa da API através do Swagger UI no seu navegador:

🔗 **URL do Swagger UI:** `http://localhost:8080/DeskPet/swagger-ui.html`

Nesta interface, você encontrará:

* **Modelos (Schemas):** Detalhes de cada objeto (`Owner`, `Clientes`, `Pet`, etc.), incluindo tipo de dado e descrição de cada campo (documentados via `@Schema`).
* **Endpoints:** Lista de todas as rotas com suas descrições (`@Operation`), parâmetros de entrada (`@Parameter`) e códigos de resposta esperados (`@ApiResponses`).

## ✍️ Contribuição

Contribuições são bem-vindas! Se você encontrar um bug ou tiver sugestões de melhoria:

1.  Faça um fork do projeto.
2.  Crie uma nova branch (`git checkout -b feature/minha-melhoria`).
3.  Commit suas mudanças (`git commit -m 'feat: Adiciona nova funcionalidade X'`).
4.  Faça push para a branch (`git push origin feature/minha-melhoria`).
5.  Abra um Pull Request.

## 🧑‍💻 Desenvolvedor

* **[Willian Potkova]** - *Desenvolvimento Inicial*
