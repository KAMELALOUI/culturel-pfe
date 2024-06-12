# Microservices Project

## Overview

This project is a microservices-based application that includes the following components:
- **Spring Boot** for creating the backend services
- **Spring Cloud** for managing the microservices infrastructure
- **Angular** for the frontend application
- **Nginx** for serving the frontend application
- **Docker** for containerizing the applications
- **Kubernetes** for orchestration and management of the Docker containers
- **Spring Cloud Gateway** for routing and load balancing
- **Eureka** for service discovery

## Project Structure

- **articles-services**: A Spring Boot microservice for managing articles
- **gateway-service**: A Spring Cloud Gateway service for routing requests
- **discovery-server**: A Eureka service for service discovery
- **mapping-service**: A Spring Boot microservice for mapping operations
- **media-service**: A Spring Boot microservice for managing media files
- **site-patrimonial-service**: A Spring Boot microservice for managing patrimonial sites
- **front**: An Angular application served using Nginx

## Getting Started

### Prerequisites

Ensure you have the following installed:
- Java 17
- Node.js and npm
- Docker
- Kubernetes (Minikube or any other K8s setup)
- Maven

### Building the Project

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd <repository-directory>
2. **Build the Backend Services**:

cd articles-services
mvn clean install
cd ../gateway-service
mvn clean install
cd ../discovery-server
mvn clean install
cd ../mapping-service
mvn clean install
cd ../media-service
mvn clean install
cd ../site-patrimonial-service
mvn clean install


3. **Build the Frontend Application**:
cd ../front
npm install
npm run build --prod

## Contact
## For any inquiries or support, please contact:

- **Name: Ibrahim
- **Email: labrasibrahim@gmail.com
