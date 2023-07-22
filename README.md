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
$ git clone https://github.com/Thalles-Eduardo/Desafio-Back-end
```

- Construir o projeto:
```bash
$ ./mvnw clean package
```

- Executar a aplicação:
```bash
$ docker compose up java_app
```

A API poderá ser acessada em [localhost:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui/index.html)

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [Postman](https://www.postman.com/downloads/) ou pode entra [aqui](http://localhost:8080/swagger-ui/index.html) pelo swagger para testar a API:

- Criar Usuário

![Criar Usuário](https://github.com/Thalles-Eduardo/Desafio-Back-end/assets/69612509/3c757429-e7f5-4bad-b04b-bf926adfbaba)

- Fazer transferência, é necessário ter dois usuários criados

![Fazer transferência](https://github.com/Thalles-Eduardo/Desafio-Back-end/assets/69612509/904eaef6-88d2-4d05-870b-f83235a273d4)

- Atualizar Usuário, é necessário passar o id do usuário, mudei apenas o campo "value"

![Atualizar Usuário](https://github.com/Thalles-Eduardo/Desafio-Back-end/assets/69612509/90c2956a-13a0-40f5-955f-bb4e48c8b11a)

- Listas de todos os usuários

![Listas todos os usuários](https://github.com/Thalles-Eduardo/Desafio-Back-end/assets/69612509/35673980-b40a-4fb5-a181-197d3347430e)

- Listas de todas as transações

![Listas de todas as transações](https://github.com/Thalles-Eduardo/Desafio-Back-end/assets/69612509/c8e4c85e-a120-4b2f-8d93-4db83e37690f)