# 🏥 API Clínica Popular - Trabalho Prático Individual
### 🎓 Residência em TIC de Software 2026.1 - SERRATEC

Este projeto consiste em uma API RESTful completa para o gerenciamento de uma **Clínica Popular**, permitindo o controle de pacientes, prontuários, médicos, especialidades e agendamento de consultas[cite: 11, 81]. [cite_start]O sistema foi desenvolvido individualmente como requisito avaliativo da disciplina de Desenvolvimento de API RESTful com Spring Boot[cite: 1, 3, 6].

---

## 👨‍💻 Dados do Aluno
* **Nome:** Mário José Praun [cite: 9]
* **Tema Escolhido:** Clínica Popular [cite: 9, 81]
* **Data de Entrega:** 22/05/2026 [cite: 8]

---

## 🚀 Tecnologias Utilizadas
O projeto foi construído utilizando o ecossistema do Spring Boot e persistência relacional[cite: 11, 16]:
***Java 17** (Linguagem de programação) [cite: 17]
***Spring Boot 3.x** (Framework base) [cite: 20]
***Spring Data JPA** (Camada de persistência e ORM) [cite: 18]
***Hibernate** (Implementação do JPA) [cite: 20]
***PostgreSQL** (Banco de dados relacional) [cite: 21]
***Bean Validation** (Validação de dados de entrada) [cite: 23]
***SpringDoc OpenAPI / Swagger** (Documentação interativa da API) [cite: 18]
***Maven** (Gerenciador de dependências e build) [cite: 22]
***Git/GitHub** (Controle de versão e histórico de commits) [cite: 24]

---

## 📂 Estrutura de Pacotes (Arquitetura em Camadas)
A API segue estritamente a organização e separação de responsabilidades exigida pelo edital[cite: 25, 26]:
*`com.serratec.clinica.controller`: Recebe as requisições HTTP e delega as ações para a camada de serviço[cite: 28].
*`com.serratec.clinica.service`: Concentra todas as regras de negócio e validações da aplicação[cite: 35].
*`com.serratec.clinica.repository`: Interfaces de comunicação com o banco de dados via Spring Data JPA[cite: 36].
*`com.serratec.clinica.domain`: Classes de entidade que representam o mapeamento das tabelas do banco[cite: 37].
*`com.serratec.clinica.dto`: Objetos de Transferência de Dados (Request/Response) isolando as entidades[cite: 38, 62].
*`com.serratec.clinica.exception`: Handler global (`@ControllerAdvice`) e exceções customizadas para respostas padronizadas[cite: 39, 69].
*`com.serratec.clinica.config`: Configurações gerais do sistema, incluindo a personalização do Swagger[cite: 40].

---

## 🗄️ Relacionamentos JPA Implementados
Os seguintes relacionamentos complexos foram mapeados para atender os requisitos de negócio[cite: 42, 141]:
1.**Paciente ↔ Prontuário (`@OneToOne`):** Cada paciente possui um prontuário médico exclusivo mapeado no sistema[cite: 47, 48, 81].
2.**Consulta ↔ Paciente / Médico (`@ManyToOne`):** Muitas consultas podem ser agendadas para um paciente e realizadas por um médico específico[cite: 52, 81].
3.**Médico ↔ Especialidade (`@ManyToMany`):** Um médico pode ter várias especialidades e uma especialidade pode pertencer a vários médicos, gerenciados via tabela associativa[cite: 50, 51, 53, 81].

*Nota de integridade:* Utilizou-se o isolamento por DTOs e mapeamentos adequados para evitar problemas de JSON recursivo/infinito na serialização dos relacionamentos[cite: 54, 127].

## 📸 Demonstração do Swagger
* [Tela Inicial do Swagger](imagens/SWAGGER_PACIENTE_MEDICO_CONSULTA.png)
* [Teste de Endpoint](imagens/SWAGGER_GET_PACINTE_200_OK.png)
---

## 🔧 Instruções de Execução e Pré-requisitos

### Pré-requisitos localmente:
*Java Development Kit (JDK) 17 instalado[cite: 17, 112].
*PostgreSQL Server ativo[cite: 21, 112].
*Ferramenta de testes HTTP (Postman, Insomnia) ou acesso ao Swagger[cite: 75].

### Passos para rodar:
1. Clone este repositório no seu computador[cite: 151].
2. Abra o pgAdmin (ou terminal do Postgres) e crie um banco de dados vazio com o nome: `clinica_db`.
3. Verifique o arquivo `src/main/resources/application.properties` e certifique-se de que o usuário (`spring.datasource.username`) e a senha (`spring.datasource.password`) correspondem às suas credenciais locais do PostgreSQL[cite: 148].
4. Abra o terminal na raiz do projeto e execute o comando do Maven Wrapper para subir a aplicação:
   ```bash
   .\mvnw spring-boot:run
