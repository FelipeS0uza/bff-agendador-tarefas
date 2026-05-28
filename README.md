# 📅 BFF Agendador de Tarefas

Camada **BFF (Backend For Frontend)** responsável por centralizar e orquestrar a comunicação entre o cliente e os microserviços do ecossistema de agendamento de tarefas.

---

## 📌 Sobre o Projeto

Este serviço atua como ponto único de entrada para o frontend, abstraindo a complexidade de comunicação com múltiplos microserviços internos. Ele agrega, transforma e entrega apenas os dados necessários para cada contexto da interface.

O projeto faz parte de um ecossistema maior composto por 4 microserviços:

| Serviço | Responsabilidade |
|---|---|
| **BFF** (este) | Orquestração e gateway para o frontend |
| [Gestão de Usuários](https://github.com/guilhermeoliveira-software/usuario) | Autenticação e gerenciamento de perfis |
| [Agendador de Tarefas](https://github.com/guilhermeoliveira-software/agendador-tarefas) | Ciclo de vida e agendamento das tarefas |
| [Notificação por E-mail](https://github.com/guilhermeoliveira-software/notificacao) | Envio de e-mails e lembretes |

---

## 🚀 Funcionalidades

- Centralização de endpoints para o frontend
- Orquestração de chamadas entre os microserviços via **OpenFeign**
- Automação de tarefas críticas com **Cron Jobs**
- Padronização de erros e respostas da API
- Documentação interativa via **Swagger**
- Pipeline de CI/CD automatizado com **GitHub Actions**

---

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 4**
- **Spring Cloud OpenFeign**
- **Docker**
- **CI/CD com GitHub Actions**
- **Swagger / SpringDoc OpenAPI**
- **Maven**
- **Lombok**

---

## 📁 Estrutura do Projeto

```
src/
└── main/
    └── java/
        └── com/costadev/bff-agendador-tarefas/
            ├── business/          # Lógica de negócio e orquestração
            ├── controller/        # Endpoints REST expostos ao frontend
            └── infrastructure/    # Clientes Feign, configurações e exceções
```

---

## ⚙️ Como Executar Localmente

### Pré-requisitos
- Java 2+
- Docker e Docker Compose

### Passos

```bash
# Clone o repositório
git clone https://github.com/felipes0uza/bff-agendador-tarefas.git
cd bff-agendador-tarefas

# Suba com Docker Compose
docker-compose up --build
```

A API estará disponível em: `http://localhost:8083`
Documentação Swagger: `http://localhost:8083/swagger-ui.html`

---

## 🌍 Variáveis de Ambiente

| Variável | Descrição |
|---|---|
| `USUARIO_URL` | URL do serviço de usuários |
| `AGENDADOR_URL` | URL do serviço de agendamento |
| `NOTIFICACAO_URL` | URL do serviço de notificação |
| `CRON_HORARIO` | Expressão cron para agendamentos automáticos |
| `USUARIO_EMAIL` | E-mail de autenticação entre serviços |
| `USUARIO_SENHA` | Senha de autenticação entre serviços |

---

## 🔄 Fluxo de Requisição

```
Cliente / Frontend
      │
      ▼
  BFF (este serviço)
      │
      ├──► Serviço de Usuários  (autenticação / perfis)
      ├──► Serviço de Agendador (CRUD de tarefas)
      └──► Serviço de Notificação (e-mails / lembretes)
