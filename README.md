# RESTful API JAVA

## Public Security

- **Java Spring**
- **Docker**
- **Azure DevOps** para CI/CD
- **Integration tests**

## Como Rodar o Projeto Localmente

### Pré-requisitos

- Java 17+
- Docker
- Maven

 ```sh
docker compose up --build
```

A Aplicação ficará disponivel na porta 8080:

```sh
http://localhost:8080/auth/test


// Testes unitários (validação)

./mvnw test
```

## Para os testes de integração:

```sh
mvn clean test
```
