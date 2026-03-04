# Fórum Hub Challenge 🗨️
![](https://img.shields.io/badge/JAVA-FCC61D)
![](https://img.shields.io/badge/SPRING%20BOOT-6CA651)
![](https://img.shields.io/badge/MYSQL-476EAE)

API REST desenvolvida em Java com Spring Boot para gerenciamento de tópicos e respostas em um fórum online.

## Tecnologias utilizadas
- Java 25
- Spring Boot 3
- Spring Security
- Spring Data JPA
- MySQL
- Maven
- Flyway
- Lombok

## Funcionalidades
- Cadastro de tópicos
- Listagem paginada de tópicos
- Detalhamento de tópico
- Atualização e exclusão lógica (soft delete) de tópicos
- Cadastro de respostas
- Listagem paginada de respostas por tópico
- Atualização e exclusão lógica de respostas
- Cadastro de usuário
- Autenticação via JWT
- Controle de autorização: usuário só pode editar/deletar seus próprios conteúdos

## Cadastro de usuário
````
POST /cadastro

Body:
{
    "nome": "usuario",
    "email": "usuario@email.com",
    "senha": "123456"
}
````

## Autenticação
A API utiliza autenticação baseada em JWT.

### Login
````
POST /login

Body:
{
    "email": "usuario@email.com",
    "senha": "123456"
}
````

A resposta retorna um token JWT que deve ser enviado no header das requisições:

`Authorization: Bearer {token}`

## Como executar o projeto
### Clonar o repositório
````
git clone https://github.com/melwolff13/forum-hub-challenge.git
````

### Criar o banco de dados no MySQL
O projeto utiliza Flyway para controle de versão do banco de dados. Após criar o banco vazio e configurar as informaões no `application.properties`, as tabelas serão criadas automaticamente na primeira execução da aplicação.

````
# application.properties

spring.datasource.url=jdbc:mysql://HOST_AQUI/forumhub_api
spring.datasource.username=SEU_USUARIO_AQUI
spring.datasource.password=SUA_SENHA_AQUI

````

## 🍯 Autora
| [<img loading="lazy" src="https://avatars.githubusercontent.com/u/168154573?v=4" width=115><br><sub>Melissa Wolff</sub>](https://github.com/melwolff13) |
|:-------------------------------------------------------------------------------------------------------------------------------------------------------:|


