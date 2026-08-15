# Library Management System

A secure, containerized Library Management System REST API built with Spring Boot, PostgreSQL, JWT authentication, Swagger/OpenAPI, JUnit, Mockito, and Docker Compose.

## About the Project

This project provides REST APIs for managing a library system. It includes user registration and login, JWT-based security, book management, database persistence, API documentation, logging, validation, exception handling, unit testing, and Docker-based deployment.

## Features

- User signup and login
- JWT authentication and authorization
- Secure protected APIs
- Book management REST APIs
- PostgreSQL database integration
- JPA and Hibernate ORM
- DTO-based request and response handling
- Input validation
- Global exception handling
- Application logging with SLF4J and Logback
- Swagger/OpenAPI API documentation
- Unit testing with JUnit 5 and Mockito
- Dockerized Spring Boot application
- Dockerized PostgreSQL database
- Docker Compose multi-container setup

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Swagger / OpenAPI
- JUnit 5
- Mockito
- Docker
- Docker Compose

## Project Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL Database
```

```text
Browser / Swagger
       ↓
Spring Boot Container :8080
       ↓
Docker Network
       ↓
PostgreSQL Container :5432
```

## Prerequisites

Install the following before running the project:

- Java 21
- Docker Desktop
- Git (optional, for cloning the repository)

## Run with Docker

### 1. Clone the repository

```cmd
git clone <your-repository-url>
cd library-management-system-spring-boot-docker
```

### 2. Create the `.env` file

Create a `.env` file in the project root:

```text
POSTGRES_DB=LibrarySystem
POSTGRES_USER=postgres
POSTGRES_PASSWORD=your_database_password
JWT_SECRET=your_base64_jwt_secret
```

Do not upload `.env` to GitHub.

### 3. Build the JAR file

On Windows:

```cmd
mvnw.cmd package -DskipTests
```

### 4. Start the application and database

```cmd
docker compose up --build
```

Docker Compose starts:

- Spring Boot application at port `8080`
- PostgreSQL database at host port `5433`

### 5. Open Swagger UI

```text
http://localhost:8080/swagger-ui.html
```

If needed, use:

```text
http://localhost:8080/swagger-ui/index.html
```

## API Testing Flow

1. Open Swagger UI.
2. Test `POST /api/auth/signup`.
3. Test `POST /api/auth/login`.
4. Copy the JWT token from the login response.
5. Use the token to test protected Book APIs.

## Run Tests

Run all unit tests:

```cmd
mvnw.cmd test
```

The project uses:

- JUnit 5 for test execution
- Mockito for mocking service dependencies

## Useful Docker Commands

Start containers:

```cmd
docker compose up --build
```

Stop containers:

```cmd
docker compose
