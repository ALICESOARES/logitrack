# LogiTrack Pro 🚛

Projeto desenvolvido como solução para o desafio técnico da LogAP, com foco no gerenciamento de veículos, viagens e análise de dados via dashboard.

---
## 🗄️ Banco de Dados

O script de carga inicial está disponível em:

Database

### Como executar:

1. Criar o banco no PostgreSQL:
   CREATE DATABASE logitrack;

2. Abrir o DBeaver

3. Executar o script:
  Desafio LogAp TRE - Carga Inicial (1).sql 

4. Rodar o backend normalmente

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven

---

## 📦 Funcionalidades

### 🚗 CRUD de Veículos
- Listar veículos
- Cadastrar veículo
- Atualizar veículo
- Remover veículo

### 🛣️ CRUD de Viagens
- Listar viagens
- Cadastrar viagem
- Atualizar viagem
- Remover viagem

### 📊 Dashboard Analítico
- Total de KM percorrido
- Volume de viagens por categoria (Leve / Pesado)
- Ranking de veículos por utilização
- Próximas manutenções
- Projeção financeira de manutenção

---

## ⚙️ Configuração do Banco de Dados

No arquivo `application.properties`:

spring.application.name=logitrack
spring.datasource.url=jdbc:postgresql://localhost:5432/logitrack
spring.datasource.username=postgres
spring.datasource.password=1234

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

---

## ▶️ Como executar o projeto

git clone https://github.com/ALICESOARES/logitrack.git
cd logitrack/logitrack
.\mvnw spring-boot:run

---

## 🌐 Endpoints da API

### Veículos
- GET /veiculos
- POST /veiculos
- PUT /veiculos/{id}
- DELETE /veiculos/{id}

### Viagens
- GET /viagens
- POST /viagens
- PUT /viagens/{id}
- DELETE /viagens/{id}

### Dashboard
- GET /dashboard
- GET /dashboard/total-km
- GET /dashboard/volume-por-categoria
- GET /dashboard/ranking-utilizacao
- GET /dashboard/proximas-manutencoes
- GET /dashboard/projecao-financeira

---

## 🧱 Estrutura do projeto

controller  → endpoints da API  
service     → regras de negócio  
repository  → acesso ao banco  
model       → entidades do sistema  

---

## 📌 Observações

O projeto utiliza o banco de dados fornecido no desafio e implementa consultas SQL para alimentar o dashboard, conforme solicitado.

---
## Prints das funcionalidades

### Veículos
Disponivel em Prints
### Viagens
Disponivel em Prints

### Dashboard
Disponivel em Prints

## Frontend

O frontend em React está na pasta `logitrack-front`.

Para executar:

```bash
cd logitrack-front
npm install
npm start

## 👩‍💻 Desenvolvido por

Alice Soares
