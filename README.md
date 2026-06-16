# ms-barbearia-expansao-api
# Documentação do Banco de Dados — Barbearia Expansão

## Tecnologias Utilizadas

* PostgreSQL
* Flyway Migration
* Spring Boot
* Docker

---

# Estrutura do Banco de Dados

O sistema possui atualmente 3 tabelas principais:

* barbeiro
* cliente
* agendamento

---

# Tabela: barbeiro

Responsável por armazenar os dados dos barbeiros cadastrados no sistema.

| Campo        | Tipo         | Descrição                       |
| ------------ | ------------ | ------------------------------- |
| id           | BIGSERIAL    | Identificador único do barbeiro |
| nome         | VARCHAR(100) | Nome do barbeiro                |
| telefone     | VARCHAR(20)  | Telefone do barbeiro            |
| email        | VARCHAR(100) | Email do barbeiro               |
| data_criacao | TIMESTAMP    | Data de criação do registro     |

## Estrutura SQL

```sql
CREATE TABLE barbeiro (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

# Tabela: cliente

Responsável por armazenar os clientes da barbearia.

O campo `servicos` utiliza um array nativo do PostgreSQL para armazenar múltiplos serviços associados ao cliente.

## Exemplos de serviços

* Corte
* Barba
* Sobrancelha
* Pigmentação

| Campo        | Tipo         | Descrição                      |
| ------------ | ------------ | ------------------------------ |
| id           | BIGSERIAL    | Identificador único do cliente |
| nome         | VARCHAR(100) | Nome do cliente                |
| telefone     | VARCHAR(20)  | Telefone do cliente            |
| servicos     | TEXT[]       | Lista de serviços do cliente   |
| data_criacao | TIMESTAMP    | Data de criação do registro    |

## Estrutura SQL

```sql
CREATE TABLE cliente (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    servicos TEXT[],
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

# Tabela: agendamento

Responsável pelos agendamentos entre clientes e barbeiros.

Relacionamentos:

* barbeiro_id → barbeiro(id)
* cliente_id → cliente(id)

| Campo       | Tipo        | Descrição                          |
| ----------- | ----------- | ---------------------------------- |
| id          | BIGSERIAL   | Identificador único do agendamento |
| barbeiro_id | BIGINT      | Referência do barbeiro             |
| cliente_id  | BIGINT      | Referência do cliente              |
| data_hora   | TIMESTAMP   | Data e hora do agendamento         |
| status      | VARCHAR(20) | Status do agendamento              |

## Possíveis Status

* AGENDADO
* CANCELADO
* FINALIZADO

## Estrutura SQL

```sql
CREATE TABLE agendamento (
    id BIGSERIAL PRIMARY KEY,
    barbeiro_id BIGINT NOT NULL,
    cliente_id BIGINT NOT NULL,
    data_hora TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,

    CONSTRAINT fk_agendamento_barbeiro
        FOREIGN KEY (barbeiro_id)
        REFERENCES barbeiro(id),

    CONSTRAINT fk_agendamento_cliente
        FOREIGN KEY (cliente_id)
        REFERENCES cliente(id)
);
```

---

# Relacionamentos

## Cliente → Agendamento

Um cliente pode possuir vários agendamentos.

```text
cliente 1:N agendamento
```

---

## Barbeiro → Agendamento

Um barbeiro pode possuir vários agendamentos.

```text
barbeiro 1:N agendamento
```

---

# Controle de Migrations

O banco de dados é controlado utilizando Flyway Migration.

## Estrutura

```text
src/main/resources/db/migration
```

## Exemplo de migrations

```text
V1__create_tables.sql
V2__add_servicos_cliente.sql
```

---

# Configuração de Ambiente

A aplicação utiliza variáveis de ambiente para conexão com o banco.

## Exemplo `.env`

```env
DB_URL=jdbc:postgresql://localhost:5432/barbearia
DB_USER=postgres
DB_PASSWORD=123456
```

---

# Docker

O ambiente da aplicação é executado utilizando Docker Compose.

Serviços:

* PostgreSQL
* API Spring Boot

---

# Observações Técnicas

* IDs utilizam `BIGSERIAL` para auto incremento.
* O PostgreSQL é utilizado como banco principal.
* O Flyway controla versionamento do schema.
* O projeto segue arquitetura em camadas.
* O array `TEXT[]` foi utilizado para facilitar múltiplos serviços por cliente na fase inicial do projeto.

---

