# 🐱 Cat Manager - Backend

Backend do projeto **Cat Manager**, desenvolvido como projeto final do curso de programação.

A aplicação foi desenvolvida em **Java com Spring Boot** e tem como objetivo consumir dados de raças de gatos através da **The Cat API**, permitindo que o usuário pesquise essas informações e salve as raças escolhidas em um banco de dados MySQL.

Depois que uma raça é salva no banco, o sistema permite realizar operações de **CRUD** sobre esses dados.

---

## 🚀 Sobre o projeto

O Cat Manager funciona como um sistema de gerenciamento de raças de gatos.

A aplicação utiliza a **The Cat API** como fonte externa de dados. O usuário pode consultar as raças disponíveis, escolher uma delas e salvar suas informações no banco de dados do projeto.

Após o cadastro, os dados passam a ser armazenados no **MySQL**, permitindo que o sistema consulte, atualize e exclua os registros salvos.

Repositório do Frontend:
🔗 Front: https://github.com/Patrick-Bigss/CatAPIManager-front

### Fluxo principal

```text
The Cat API
     ↓
Spring Boot
     ↓
Pesquisa de raças
     ↓
Usuário escolhe uma raça
     ↓
POST
     ↓
MySQL
     ↓
GET / PUT / DELETE


🛠️ Tecnologias utilizadas
Java
Spring Boot
Spring Data JPA
MySQL
REST API
The Cat API
HTML
JavaScript
Bootstrap
🔌 Integração com a The Cat API

O projeto utiliza a The Cat API para consultar informações reais sobre raças de gatos.

Entre os dados utilizados estão:

ID da raça
Nome
Temperamento
Origem
Descrição
Expectativa de vida
Peso
Altura
História
Imagem
Entre outras informações disponíveis pela API

Exemplo de uma raça retornada pela API:

{
    "id": "abys",
    "name": "Abyssinian",
    "life_span": "14-17",
    "temperament": "Active, Energetic, Independent, Intelligent",
    "origin": "Egypt",
    "description": "Medium-sized, elegant cat..."
}


💾 Banco de dados

Os dados selecionados da The Cat API são armazenados no MySQL.

O projeto possui uma entidade chamada GatoEntity, responsável por representar os gatos armazenados no banco.

Um dos campos importantes é:

id

Esse é o ID gerado automaticamente pelo MySQL.

Também existe:

catApiId

Esse campo armazena o ID original da The Cat API.

Por exemplo:

id = 1
catApiId = "abys"
name = "Abyssinian"
origin = "Egypt"

Dessa forma, o sistema consegue utilizar o ID da The Cat API como referência para encontrar o gato dentro do banco de dados.



🔄 Operações CRUD

O backend possui operações para:

GET

Consulta os gatos que estão armazenados no MySQL.

GET /gatinhos

Também existe o endpoint para consultar as raças da The Cat API:

GET /gatinhos/racas
POST

Salva uma nova raça no banco de dados.

POST /gatinhos

Exemplo:

{
    "catApiId": "abys",
    "name": "Abyssinian",
    "lifeSpan": "14-17",
    "temperament": "Active, Energetic, Independent",
    "origin": "Egypt"
}
PUT

Atualiza uma raça que já foi salva no MySQL.

O sistema utiliza o ID da The Cat API para localizar o registro.

PUT /gatinhos/api/abys

Nesse caso, abys é o catApiId.

DELETE

Exclui uma raça do MySQL utilizando o ID da The Cat API.

DELETE /gatinhos/api/abys

A exclusão acontece apenas no banco de dados do projeto.

Os dados da The Cat API não são alterados.



🧩 Estrutura do Backend

O projeto possui uma estrutura baseada no padrão utilizado pelo Spring Boot:

src
└── main
    └── java
        └── com.example.CatManagerBack
            │
            ├── controller
            │   └── GatoController.java
            │
            ├── entity
            │   └── GatoEntity.java
            │
            └── repository
                └── GatoRepository.java
Controller

O GatoController é responsável por receber as requisições HTTP e controlar as operações do sistema.

Nele estão os endpoints:

GET
POST
PUT
DELETE

Também é responsável pela comunicação com a The Cat API.

Entity

A GatoEntity representa os dados que serão armazenados no MySQL.

Ela possui informações como:

id
catApiId
name
temperament
origin
description
lifeSpan
weight
height
image
Repository

O GatoRepository utiliza o Spring Data JPA para realizar a comunicação com o MySQL.

Além das operações padrão do JPA, existe uma busca pelo ID da The Cat API:

Optional<GatoEntity> findByCatApiId(String catApiId);

Isso permite localizar uma raça pelo ID utilizado pela API, por exemplo:

abys
beng
mcoo


🔑 Configuração da API

Para utilizar a The Cat API, é necessário possuir uma chave de API.

No arquivo:

application.properties

é configurada a chave:

thecatapi.key=SUA_CHAVE_AQUI

🗄️ Configuração do MySQL

O projeto utiliza o MySQL como banco de dados.

Exemplo de configuração no application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/catmanager
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


🎯 Objetivo do projeto

O principal objetivo do projeto foi colocar em prática conhecimentos de:

Desenvolvimento de APIs REST
Java e Spring Boot
Consumo de APIs externas
Integração com banco de dados
Spring Data JPA
Operações CRUD
Requisições HTTP
Integração entre Frontend e Backend

O projeto também demonstra como uma aplicação pode utilizar uma API externa como fonte de dados e posteriormente armazenar e gerenciar esses dados em seu próprio banco de dados.

👨‍💻 Autor

Patrick Leal Andrade Pereira

Projeto desenvolvido para fins acadêmicos e de aprendizado em desenvolvimento backend.
