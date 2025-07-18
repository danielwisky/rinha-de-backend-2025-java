# Rinha de Backend 2025 - Java

[![Java](https://img.shields.io/badge/Java-24-red.svg)](https://openjdk.org/projects/jdk/24/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MongoDB](https://img.shields.io/badge/MongoDB-Database-green.svg)](https://www.mongodb.com/)
[![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-Messaging-orange.svg)](https://kafka.apache.org/)

**Backend intermediador de pagamentos** desenvolvido para a **Rinha de Backend 2025** - uma
competição que desafia desenvolvedores a criar sistemas de alta performance para processamento de
pagamentos com estratégias inteligentes de fallback.

## 🎯 O Desafio

Este projeto implementa um **backend intermediador** que recebe solicitações de pagamento e as
encaminha para dois serviços de processamento:

- 🟢 **Payment Processor Default** - Taxa menor, mas instável
- 🔴 **Payment Processor Fallback** - Taxa maior, backup para falhas

### Objetivo da Competição

**Maximizar o lucro** processando o maior número de pagamentos com as menores taxas possíveis,
mantendo alta disponibilidade mesmo com instabilidades nos processadores.

### Sistema de Pontuação

- 💰 **Lucro**: Quanto mais pagamentos com menor taxa, melhor
- ⚡ **Performance**: Bônus de 2% por cada 1ms abaixo de 11ms no p99
- 🚨 **Penalidade**: 35% de multa por inconsistências detectadas

## 🏗️ Arquitetura Obrigatória

O projeto segue as **especificações da Rinha de Backend 2025**:

```
┌──────────────┐    ┌────────────────┐    ┌─────────────────────┐
│ Load Balancer│────│    Backend     │────│ Payment Processor   │
│   (nginx)    │    │ (2+ instâncias)│    │    Default          │
└──────────────┘    │                │    └─────────────────────┘
     │              │                │    ┌─────────────────────┐
     │              │                │────│ Payment Processor   │
     │              │                │    │    Fallback         │
     └──────────────┴────────────────┘    └─────────────────────┘
```

### Componentes

- **Load Balancer**: Distribuição de carga entre instâncias
- **2+ Instâncias Web**: Processamento paralelo das requisições
- **MongoDB**: Persistência dos dados de pagamento
- **Apache Kafka**: Processamento assíncrono

## 🚀 Funcionalidades

### Endpoints Obrigatórios

#### 1. Processar Pagamento

```http
POST /payments
Content-Type: application/json

{
  "correlationId": "4a7901b8-7d26-4d9d-aa19-4dc1c7cf60b3",
  "amount": 19.90
}
```

**Resposta**: `HTTP 2XX` (qualquer status 200-299)

#### 2. Resumo de Pagamentos (Auditoria)

```http
GET /payments-summary?from=2020-07-10T12:34:56.000Z&to=2020-07-10T12:35:56.000Z
```

**Resposta**:

```json
{
  "default": {
    "totalRequests": 43236,
    "totalAmount": 415542345.98
  },
  "fallback": {
    "totalRequests": 423545,
    "totalAmount": 329347.34
  }
}
```

### Estratégias Implementadas

- 🔄 **Fallback Inteligente**: Detecção automática de falhas e switch para processador backup
- 📊 **Health Check**: Monitoramento de saúde dos processadores (limite: 1 call/5s)
- ⚡ **Cache de Estado**: Otimização para evitar calls desnecessários
- 🎯 **Balanceamento de Taxa**: Priorização do processador com menor taxa

## 🛠️ Tecnologias

- **Java 24** - Linguagem de programação
- **Spring Boot 3.5.3** - Framework web
- **Spring Data MongoDB** - Persistência de dados
- **Spring Kafka** - Mensageria assíncrona
- **OpenFeign** - Cliente HTTP para integração com processadores
- **Lombok** - Redução de boilerplate
- **TestContainers** - Testes de integração
- **Maven** - Gerenciamento de dependências
- **Docker** - Containerização
- **Nginx** - Load balancer (em docker-compose)

## 📋 Restrições da Competição

### Recursos Limitados

- **CPU**: Máximo 1.5 unidades entre todos os serviços
- **Memória**: Máximo 350MB entre todos os serviços
- **Porta**: Obrigatório usar porta **9999**
- **Rede**: Modo bridge (host não permitido)

### Arquitetura Obrigatória

- ✅ Mínimo 2 instâncias web para load balancing
- ✅ Docker Compose com imagens públicas
- ✅ Compatibilidade linux-amd64
- ❌ Modo privileged não permitido
- ❌ Serviços replicados não permitidos

## ⚡ Execução Rápida

### 1. Subir Payment Processors (Obrigatório)

```bash
# Baixar processadores da competição
git clone https://github.com/zanfranceschi/rinha-de-backend-2025
cd rinha-de-backend-2025/payment-processor
docker-compose up -d
```

### 2. Clone e Execute o Backend

```bash
git clone https://github.com/danielwisky/rinha-de-backend-2025-java.git
cd rinha-de-backend-2025-java
docker-compose up -d
```

### 3. Testar Aplicação

A aplicação estará disponível em `http://localhost:9999`

**Payment Processors disponíveis**:

- Default: `http://localhost:8001`
- Fallback: `http://localhost:8002`

## 🧪 Teste Local

Siga
as [instruções oficiais de teste](https://github.com/zanfranceschi/rinha-de-backend-2025/tree/main/rinha-test)
para validar seu backend localmente.

```bash
# Instalar gatling (necessário para testes)
# Executar cenários de carga da competição
# Verificar métricas de performance e consistência
```

## 🔧 Configuração

### Variáveis de Ambiente

| Variável                         | Descrição                   | Padrão                                   |
|----------------------------------|-----------------------------|------------------------------------------|
| `SPRING_PROFILES_ACTIVE`         | Profile ativo               | `production`                             |
| `PAYMENT_PROCESSOR_DEFAULT_URL`  | URL do processador padrão   | `http://payment-processor-default:8080`  |
| `PAYMENT_PROCESSOR_FALLBACK_URL` | URL do processador fallback | `http://payment-processor-fallback:8080` |
| `MONGODB_URI`                    | URI do MongoDB              | `mongodb://mongodb:27017/rinha`          |
| `KAFKA_BOOTSTRAP_SERVERS`        | Servidores Kafka            | `kafka:9092`                             |

## 📈 Estratégias de Otimização

### 1. Minimização de Latência

- Connection pooling otimizado
- Cache de health checks
- Processamento assíncrono via Kafka

### 2. Maximização de Lucro

- Priorização do processador default (menor taxa)
- Fallback rápido em caso de falha
- Retry inteligente com backoff

### 3. Garantia de Consistência

- Transações ACID no MongoDB
- Idempotência nos processamentos
- Auditoria completa para evitar multas

## 📁 Estrutura do Projeto

```
src/
├── main/java/br/com/danielwisky/rinhadebackend/
│   ├── domains/              # Entidades de domínio
│   │   ├── enums/           # ProcessorType (DEFAULT, FALLBACK)
│   │   └── Payment.java     # Entidade Payment
│   ├── gateways/            # Interfaces e implementações
│   │   ├── inputs/          # HTTP (PaymentController), Kafka
│   │   └── outputs/         # MongoDB, HTTP (ProcessorClients), Kafka
│   ├── usecases/            # ProcessPayment
│   └── utils/               # Utilitários (JsonUtils)
├── main/resources/          # Configurações (application.yml)
└── test/                    # Testes com TestContainers
```

## 🏆 Submissão para Competição

Para participar oficialmente:

1. **Repositório público** com código fonte
2. **Pull Request** no repositório oficial com:
    - `docker-compose.yml` configurado
    - `info.json` com tecnologias usadas
    - `README.md` explicativo
3. **Prazo**: 17/08/2025 às 23:59:59
4. **Resultados**: 20/08/2025

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👤 Autor

**Daniel Wisky**

- GitHub: [@danielwisky](https://github.com/danielwisky)

---

<p align="center">
  Desenvolvido com ❤️ para a <strong>Rinha de Backend 2025</strong><br>
  🎯 <em>Maximizando lucros, minimizando latência!</em>
</p>