<h1 align="center">
  Transaction-API
</h1>

<p align="center">
 <img src="https://img.shields.io/static/v1?label=Tipo&message=Desafio&color=8257E5&labelColor=000000" alt="Desafio" />
 <img src="https://img.shields.io/static/v1?label=License&message=MIT&color=8257E5&labelColor=000000" alt="License" />
</p>

API para gerenciar a transação de valores monetários que faz parte [desse desafio](https://github.com/PicPay/picpay-desafio-backend) para desenvolvedores backend júnior e sênior.


## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [PostgreSQL](https://www.postgresql.org/download/)
- [Swagger](https://swagger.io)
- [Docker](https://www.docker.com)

## Práticas adotadas

- SOLID, DRY, YAGNI, KISS
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Testes unitários
- Testes de integração
- Geração automática do Swagger com a OpenAPI 3
- Dockerização

## Como Executar

- Clonar repositório git
```bash
git clone https://github.com/Thalles-Eduardo/Desafio-Back-end
```

- Construir o projeto:
```bash
mvn clean package -DskipTests
```

- Executar a aplicação:
```bash
$ docker compose up java_app
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/downloads/) ou pode entra [aqui](http://localhost:8080/swagger-ui/index.html) pelo swagger para testar a API:

- Criar Usuário

![Criar Usuário](https://github.com/Thalles-Eduardo/Desafio-Back-end-Spring-boot/assets/69612509/c7e861f0-3674-4c4a-9ebc-8e2d867d4d19)

- Fazer transferência, é necessário ter dois usuários criados

![Fazer transferência](https://github.com/Thalles-Eduardo/Desafio-Back-end-Spring-boot/assets/69612509/399aeb9a-8ccd-466f-82ef-28e234c1af96)

- Atualizar Usuário, é necessário passar o id do usuário, mudei apenas o campo "value"

![Atualizar Usuário](https://github.com/Thalles-Eduardo/Desafio-Back-end-Spring-boot/assets/69612509/5ee2cbe0-525e-46c0-8a7c-02de3bb2c4c0)


- Listas de todos os usuários

![Listas de todos os usuários](https://github.com/Thalles-Eduardo/Desafio-Back-end-Spring-boot/assets/69612509/7908b33b-e48a-4204-bfad-dda8b7037146)


- Listas de todas as transações

![Listas de todas as transações](https://github.com/Thalles-Eduardo/Desafio-Back-end-Spring-boot/assets/69612509/fd85c003-856d-4cd8-a682-44e211028b23)

# Author

Thalles Eduardo Dias da Silva

- [Linkedin](https://linkedin.com/in/thalles-eduardo-7297a6237)
