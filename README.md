
## Features
- Modular Microservices architecture
- Service Discovery with Eureka
- Centralized Configuration
- API Gateway with JWT authentication
- Specialized domain services
- Docker and Docker Compose support

## Services Overview

### Core Infrastructure Services
- Eureka (8761): Service discovery dashboard
- ApiGetWay (8090): API Gateway with JWT validation
- configServer_ (8888): Centralized configuration management

### Domain Microservices
- university (8085): Core institution management
- departement (8089): Academic departments and programs
- formation (8083): Courses and curriculum
- finance (3000): Budgeting and financial operations
- equipe (8083): Staff and team management
- contrat (8086): Contract lifecycle
- Ressource (8082): Physical resource allocation

## Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- Docker 20.10+
- MySQL 8.0

### Installation
```bash
# Clone and build
git clone https://github.com/your-org/MicroService-Vghar-4TWIN1.git
cd MicroService-Vghar-4TWIN1
mvn clean install

# Start infrastructure
docker-compose up -d mysql rabbitmq eureka configServer_

# Start services (in order)
mvn spring-boot:run -pl university
mvn spring-boot:run -pl ApiGetWay
mvn spring-boot:run -pl departement
