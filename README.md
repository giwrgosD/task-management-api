# Task Management API

A Spring Boot REST API for managing tasks, built as a backend portfolio project.

## Features

- CRUD operations for tasks
- Pagination and filtering
- Request/response DTOs
- Bean Validation
- Global exception handling
- PostgreSQL with Docker
- Swagger / OpenAPI documentation
- Spring Security with JWT authentication

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA / Hibernate
- Spring Security
- JWT
- PostgreSQL
- Docker / Docker Compose
- Maven
- Swagger / springdoc-openapi

## API Endpoints

### Auth
- `POST /api/v1/auth/register`
- `POST /api/v1/auth/login`

### Tasks
- `GET /api/v1/tasks`
- `GET /api/v1/tasks/{id}`
- `POST /api/v1/tasks`
- `PUT /api/v1/tasks/{id}`
- `DELETE /api/v1/tasks/{id}`

## Filtering and Pagination

Examples:

- `GET /api/v1/tasks?page=0&size=5`
- `GET /api/v1/tasks?status=TODO`
- `GET /api/v1/tasks?priority=HIGH`
- `GET /api/v1/tasks?status=TODO&priority=HIGH&page=0&size=5`

## Authentication

This API uses JWT authentication.

1. Register a user via `/api/v1/auth/register`
2. Login via `/api/v1/auth/login`
3. Copy the returned token
4. Use it as:

```text
Authorization: Bearer <your-token>
```



## Running the Application

### Run with Docker

Build and start the entire stack (API + PostgreSQL):

```bash
docker compose up --build

---Database Configuration

Configured in application.properties:

1. database: taskmanagement
2. username: taskuser
3. password: taskpass


---Project Structure


src/main/java/com/giwrgos/taskmanagement
├── auth
├── common
├── config
├── security
├── task
└── user
```
### The API will be available at: 
```bash
http://localhost:8080
```
### Swagger UI:
```bash
http://localhost:8080/swagger-ui/index.html
```

## Future Improvements
- Add automated tests
- Improve role-based authorization
- Add refresh tokens
