# Tech Challenge Fase 4
## Projeto do curso de Pós-Graduação da FIAP

## Grupo 50

- [Daniel Santiago](https://github.com/SantiagoDDaniel)
- [Kassiane Silva Mentz](https://github.com/kassimentz)
- [Leandro Paulino Costa](https://github.com/LeandroPC)
- [Luiz Felipe da Silva Santos](https://github.com/Felipe-3191)
- [Vitor Hugo Campos Alves](https://github.com/vitorAlves1992/)

## Fase 4 (30/10/2023 até 29/01/2024)

**Objetivos:**

Desenvolver uma aplicação web de streaming de vídeos utilizando as tecnologias Spring Framework, Spring WebFlux, Spring Boot e Spring Data. A aplicação deve permitir o gerenciamento e a exibição de vídeos, onde cada vídeo possui um título, descrição, URL e data de publicação.

### Requisitos Funcionais 

- Criação, atualização, listagem e exclusão de vídeos.
- Os vídeos devem conter os seguintes campos: título, descrição, URL e data de publicação.
- A listagem de vídeos deve ser paginada e ordenável por data de publicação.
- Implementar filtros de busca por título e data de publicação na listagem.
- Implementar sistema de marcação de vídeos como favoritos.
- Implementar categorias para os vídeos e permitir a filtragem por categoria na listagem.
- Implementar um sistema de recomendação de vídeos com base nos favoritos do usuário.
- Implementar um endpoint para estatísticas, mostrando a quantidade total de vídeos, a quantidade de vídeos favoritados e a média de visualizações.

### Requisitos Técnicos 
- Utilização do Spring WebFlux para a criação de endpoints reativos.
- Utilização do Spring Boot para configuração e inicialização da aplicação.
- Utilização do Spring Data para a camada de persistência com suporte a bancos de dados reativos (por exemplo, MongoDB).
- Implementar a arquitetura Clean Architecture, separando a aplicação em camadas: Controllers, Services, Use Cases, Repositories.
- Implementar testes unitários e de integração para as diferentes camadas da aplicação, com cobertura de testes de pelo menos 80% do código.
- Utilizar boas práticas de nomenclatura, organização de código e comentários quando necessário.
- Utilizar validações de entrada nos endpoints.
- Gerenciar dependências utilizando o gerenciador de pacotes Maven ou Gradle.


### Entregas
- Código-fonte do projeto no repositório Git (GitHub, GitLab, Bitbucket, etc.).
- Uma apresentação gravada demonstrando o funcionamento da plataforma, mostrando o código e explicando a arquitetura do projeto.
- Documentação descrevendo a arquitetura escolhida, decisões técnicas relevantes e um guia de uso da aplicação.

---
## Tecnologias utilizadas
- Spring Webflux
- Spring Data
- Spring Boot
- Mongodb
- Maven
- Surefire 
- Jacoco
- Docker
- Make 
- Git  

## Código Fonte: 

https://github.com/kassimentz/TC_Fase4/tree/main/tech_challenge_web_streaming

## Documentação: 

### Arquitetura
[Documentação da Arquitetura](./doc/Arquitetura.md)

### Decisões Técnicas Relevantes 
[Decisões Técnicas Relevantes](./doc/decisoesTecnicas.md)
### Guia de Uso da Aplicação