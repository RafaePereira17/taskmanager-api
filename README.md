# TaskManager API

API REST para gerenciamento de tarefas desenvolvida com **Java e Spring Boot**, com autenticação baseada em JWT, controle de acesso por usuário e persistência de dados com PostgreSQL.

O projeto foi desenvolvido com foco no estudo e aplicação de conceitos utilizados no desenvolvimento backend, como arquitetura em camadas, segurança, validação, tratamento de exceções e testes unitários.

## 🚀 Funcionalidades

- Cadastro de usuários
- Autenticação de usuários por e-mail e senha
- Senhas protegidas com BCrypt
- Geração e validação de tokens JWT
- Proteção de endpoints com Spring Security
- Criação de tarefas
- Listagem das tarefas do usuário autenticado
- Busca de tarefa por ID
- Atualização de tarefas
- Exclusão de tarefas
- Controle de propriedade das tarefas por usuário
- Status de tarefas: `PENDENTE`, `EM_ANDAMENTO` e `CONCLUIDA`
- Validação dos dados recebidos pela API
- Tratamento global de exceções
- Paginação na listagem de tarefas
- Testes unitários da camada de serviço com JUnit e Mockito

## 🛠️ Tecnologias

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- PostgreSQL
- JWT
- BCrypt
- Maven
- JUnit 5
- Mockito
- Git

## 🔐 Segurança

A API utiliza autenticação baseada em **JWT (JSON Web Token)**.

Após realizar o login com credenciais válidas, o usuário recebe um token JWT que deve ser enviado nas requisições aos endpoints protegidos.

Cada tarefa é associada ao usuário autenticado, impedindo que um usuário consulte, altere ou exclua tarefas pertencentes a outro usuário.

As senhas dos usuários são armazenadas utilizando **BCrypt**.

## ⚙️ Configuração

Antes de executar a aplicação, configure as seguintes variáveis de ambiente:

- `DB_PASSWORD` — senha do banco de dados PostgreSQL
- `JWT_SECRET` — chave utilizada para assinatura dos tokens JWT

O projeto utiliza por padrão um banco PostgreSQL local chamado:

`taskmanager`

## 🧪 Testes

O projeto possui testes unitários para a camada de serviço utilizando **JUnit 5 e Mockito**.

Os testes cobrem cenários como:

- Busca de tarefas
- Criação de tarefas
- Atualização de tarefas
- Exclusão de tarefas
- Tratamento de tarefas inexistentes
- Associação da tarefa ao usuário autenticado

As dependências externas da camada de serviço são simuladas com mocks, permitindo testar as regras de negócio de forma isolada.