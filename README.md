# 🏥 API Clínica Popular - Trabalho Prático Individual
### 🎓 Residência em TIC de Software 2026.1 - SERRATEC

[cite_start]Este projeto consiste em uma API RESTful completa para o gerenciamento de uma **Clínica Popular**, permitindo o controle de pacientes, prontuários, médicos, especialidades e agendamento de consultas[cite: 11, 81]. [cite_start]O sistema foi desenvolvido individualmente como requisito avaliativo da disciplina de Desenvolvimento de API RESTful com Spring Boot[cite: 1, 3, 6].

---

## 👨‍💻 Dados do Aluno
* [cite_start]**Nome:** Mário José Praun [cite: 9]
* [cite_start]**Tema Escolhido:** Clínica Popular [cite: 9, 81]
* [cite_start]**Data de Entrega:** 22/05/2026 [cite: 8]

---

## 🚀 Tecnologias Utilizadas
[cite_start]O projeto foi construído utilizando o ecossistema do Spring Boot e persistência relacional[cite: 11, 16]:
* [cite_start]**Java 17** (Linguagem de programação) [cite: 17]
* [cite_start]**Spring Boot 3.x** (Framework base) [cite: 20]
* [cite_start]**Spring Data JPA** (Camada de persistência e ORM) [cite: 18]
* [cite_start]**Hibernate** (Implementação do JPA) [cite: 20]
* [cite_start]**PostgreSQL** (Banco de dados relacional) [cite: 21]
* [cite_start]**Bean Validation** (Validação de dados de entrada) [cite: 23]
* [cite_start]**SpringDoc OpenAPI / Swagger** (Documentação interativa da API) [cite: 18]
* [cite_start]**Maven** (Gerenciador de dependências e build) [cite: 22]
* [cite_start]**Git/GitHub** (Controle de versão e histórico de commits) [cite: 24]

---

## 📂 Estrutura de Pacotes (Arquitetura em Camadas)
[cite_start]A API segue estritamente a organização e separação de responsabilidades exigida pelo edital[cite: 25, 26]:
* [cite_start]`com.serratec.clinica.controller`: Recebe as requisições HTTP e delega as ações para a camada de serviço[cite: 28].
* [cite_start]`com.serratec.clinica.service`: Concentra todas as regras de negócio e validações da aplicação[cite: 35].
* [cite_start]`com.serratec.clinica.repository`: Interfaces de comunicação com o banco de dados via Spring Data JPA[cite: 36].
* [cite_start]`com.serratec.clinica.domain`: Classes de entidade que representam o mapeamento das tabelas do banco[cite: 37].
* [cite_start]`com.serratec.clinica.dto`: Objetos de Transferência de Dados (Request/Response) isolando as entidades[cite: 38, 62].
* [cite_start]`com.serratec.clinica.exception`: Handler global (`@ControllerAdvice`) e exceções customizadas para respostas padronizadas[cite: 39, 69].
* [cite_start]`com.serratec.clinica.config`: Configurações gerais do sistema, incluindo a personalização do Swagger[cite: 40].

---

## 🗄️ Relacionamentos JPA Implementados
[cite_start]Os seguintes relacionamentos complexos foram mapeados para atender os requisitos de negócio[cite: 42, 141]:
1. [cite_start]**Paciente ↔ Prontuário (`@OneToOne`):** Cada paciente possui um prontuário médico exclusivo mapeado no sistema[cite: 47, 48, 81].
2. [cite_start]**Consulta ↔ Paciente / Médico (`@ManyToOne`):** Muitas consultas podem ser agendadas para um paciente e realizadas por um médico específico[cite: 52, 81].
3. [cite_start]**Médico ↔ Especialidade (`@ManyToMany`):** Um médico pode ter várias especialidades e uma especialidade pode pertencer a vários médicos, gerenciados via tabela associativa[cite: 50, 51, 53, 81].

[cite_start]*Nota de integridade:* Utilizou-se o isolamento por DTOs e mapeamentos adequados para evitar problemas de JSON recursivo/infinito na serialização dos relacionamentos[cite: 54, 127].

## 📸 Demonstração do Swagger
* [Tela Inicial do Swagger](imagens/SWAGGER_PACIENTE_MEDICO_CONSULTA.png)
* [Teste de Endpoint](imagens/SWAGGER_GET_PACINTE_200_OK.png)
---

## 🔧 Instruções de Execução e Pré-requisitos

### Pré-requisitos localmente:
* [cite_start]Java Development Kit (JDK) 17 instalado[cite: 17, 112].
* [cite_start]PostgreSQL Server ativo[cite: 21, 112].
* [cite_start]Ferramenta de testes HTTP (Postman, Insomnia) ou acesso ao Swagger[cite: 75].

### Passos para rodar:
1. [cite_start]Clone este repositório no seu computador[cite: 151].
2. Abra o pgAdmin (ou terminal do Postgres) e crie um banco de dados vazio com o nome: `clinica_db`.
3. [cite_start]Verifique o arquivo `src/main/resources/application.properties` e certifique-se de que o usuário (`spring.datasource.username`) e a senha (`spring.datasource.password`) correspondem às suas credenciais locais do PostgreSQL[cite: 148].
4. Abra o terminal na raiz do projeto e execute o comando do Maven Wrapper para subir a aplicação:
   ```bash
   .\mvnw spring-boot:run
