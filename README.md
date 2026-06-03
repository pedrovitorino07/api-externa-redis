# Consulta API - ViaCEP + Redis Cache

## Sobre o projeto

API REST desenvolvida com Spring Boot para consulta de CEPs utilizando a API pública ViaCEP.

A aplicação implementa cache com Redis para reduzir chamadas externas desnecessárias, além de tratamento de exceções e validações para garantir maior robustez.

## Tecnologias

* Java 17
* Spring Boot 4
* Spring Cache
* Redis
* RestTemplate
* Lombok
* Swagger / OpenAPI

## Funcionalidades

* Consulta de CEP via ViaCEP
* Cache Redis para otimização de performance
* TTL configurável para expiração automática do cache
* Tratamento global de exceções
* Validação de CEP
* Documentação Swagger

## Fluxo da aplicação

Cliente

↓

Controller

↓

Service

↓

Redis Cache

↓

ViaCEP API

## Endpoint

GET /consulta-cep/{cep}

### Exemplo

GET /consulta-cep/01001000

### Resposta

```json
{
  "cep": "01001-000",
  "logradouro": "Praça da Sé",
  "bairro": "Sé",
  "localidade": "São Paulo",
  "uf": "SP"
}
```

## Tratamento de erros

A API retorna respostas padronizadas:

```json
{
  "message": "CEP não encontrado",
  "status": 404,
  "error": "NOT_FOUND",
  "timestamp": "2026-06-02T22:00:00"
}
```

## Cache Redis

As consultas são armazenadas em cache para evitar chamadas repetidas ao ViaCEP.

Cada CEP possui sua própria chave de cache com tempo de expiração configurado via TTL.

## Como executar

### Redis

docker run -d --name redis -p 6379:6379 redis

### Aplicação

./mvnw spring-boot:run

## Autor

Pedro Vitorino
