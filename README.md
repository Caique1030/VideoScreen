

![image](https://github.com/user-attachments/assets/5fd92a66-41f9-4a39-bdab-ff9b0cac6647)
# Projeto de Série com API GPT

Este projeto é uma aplicação para gerenciar séries de TV, utilizando a API do GPT para busca de informações e o banco de dados PostgreSQL para armazenamento. A aplicação é construída com Spring Framework e utiliza tecnologias como JPA, Hibernate, Spring Web e Spring MVC.

## Tecnologias Utilizadas

- **Spring Framework**: Framework para desenvolvimento de aplicações Java.
- **Spring Web**: Módulo do Spring para criar aplicações web.
- **Spring MVC**: Sub-framework para criar aplicações web baseadas em modelo-visão-controlador.
- **JPA (Java Persistence API)**: Interface padrão para trabalhar com persistência de dados em Java.
- **Hibernate**: Implementação de JPA para gerenciamento de dados relacionais.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar os dados das séries.
- **API GPT**: API para busca de informações sobre séries.

## Funcionalidades

- Busca de séries por nome.
- Integração com a API do GPT para busca de informações adicionais sobre séries.
- Armazenamento e recuperação de dados usando PostgreSQL.

## Instalação e Configuração

### Pré-requisitos

Certifique-se de ter o Java JDK e o PostgreSQL instalados em seu sistema. Além disso, você precisará do Maven para gerenciar as dependências do projeto.

### Passos para Configuração

1. **Clone o Repositório**

   ```bash
   git clone https://github.com/seuusuario/seurepositorio.git
   cd seurepositorio
Configuração do Banco de Dados

Crie um banco de dados PostgreSQL e configure as credenciais no arquivo application.properties localizado em src/main/resources. Atualize as seguintes propriedades:


spring.datasource.url=jdbc:postgresql://localhost:5432/seubancodedados
spring.datasource.username=seuusuario
spring.datasource.password=suasenha
Dependências

Execute o Maven para baixar as dependências do projeto:


mvn clean install
Executar o Projeto

Você pode executar a aplicação com o seguinte comando Maven:


mvn spring-boot:run
Ou, se preferir, compile o projeto e execute o JAR:


mvn package
java -jar target/seu-arquivo.jar
Uso
Depois de iniciar a aplicação, você pode acessar a API em http://localhost:8080. Veja os endpoints disponíveis na documentação da API.

Contribuição
Se você deseja contribuir para este projeto, por favor, siga estas etapas:

Fork o repositório.
Crie uma branch para a sua feature (git checkout -b feature/nome-da-feature).
Faça commit das suas mudanças (git commit -am 'Adicionando nova feature').
Faça push para a branch (git push origin feature/nome-da-feature).
Abra um Pull Request.
Licença
Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE para mais detalhes.

Contato
Se você tiver dúvidas ou precisar de ajuda, entre em contato com seuemail@dominio.com.

Nota: Substitua os placeholders, como https://github.com/seuusuario/seurepositorio.git, seubancodedados, seuusuario, suasenha, seu-arquivo.jar, e seuemail@dominio.com pelos valores específicos do seu projeto.



Esse README fornece uma visão geral do projeto, como configurá-lo e como contribuir. Lembre-se de personalizar as seções com as informações específicas do seu projeto.





