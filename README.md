# Library Management System

A secure Library Management System REST API built with Spring Boot, PostgreSQL, JWT authentication, Swagger/OpenAPI, and Docker.

## Features

- User signup and login
- JWT-based authentication and authorization
- Book management APIs
- PostgreSQL database integration
- JPA/Hibernate ORM
- DTO validation and global exception handling
- Swagger UI API documentation
- Logging with SLF4J/Logback
- Dockerized Spring Boot application and PostgreSQL database

## Tech Stack

- Java 21
- Spring Boot
- Spring Security + JWT
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- Swagger / OpenAPI
- Docker and Docker Compose

## Run with Docker

### Prerequisites

- Docker Desktop
- Java 21 and Maven are only needed if building the JAR locally

### 1. Build the application JAR

On Windows:

```cmd
mvnw.cmd package -DskipTests
```

### 2. Create a `.env` file

Create a `.env` file in the project root:

```text
POSTGRES_DB=LibrarySystem
POSTGRES_USER=postgres
POSTGRES_PASSWORD=your_database_password
JWT_SECRET=your_base64_jwt_secret
```

Do not upload `.env` to GitHub.

### 3. Start the application and database

```cmd
docker compose up --build
```

This starts:

- Spring Boot application on port `8080`
- PostgreSQL database on port `5433`

### 4. Open Swagger UI

```text
http://localhost:8080/swagger-ui.html
```

Alternative URL:

```text
http://localhost:8080/swagger-ui/index.html
```

## Docker Architecture

```text
Browser
   |
   | localhost:8080
   v
Spring Boot Container
   |
   | Docker network: postgres:5432
   v
PostgreSQL Container
```

## Useful Docker Commands

Start containers:

```cmd
docker compose up --build
```

Stop containers:

```cmd
docker compose down
```

Check running containers:

```cmd
docker compose ps
```

View logs:

```cmd
docker compose logs -f
```

## Security Notes

- Never commit `.env` files.
- Never commit database passwords or JWT secrets.
- Use environment variables for sensitive configuration.
- Keep `.env` included in `.gitignore`.

## API Testing Flow

1. Open Swagger UI.
2. Call `POST /api/auth/signup`.
3. Call `POST /api/auth/login`.
4. Copy the JWT token from the login response.
5. Use the token to test protected Book APIs.

## Author

Anuj
