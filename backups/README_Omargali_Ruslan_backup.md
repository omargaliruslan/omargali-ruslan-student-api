# omargali-ruslan-student-api

Spring Boot CRUD API for students — DevOps midterm project.

Author: Ruslan Omargali (ID 37858)

Status: in development

## Stack
Java 17, Spring Boot 3, Gradle, PostgreSQL 16, Docker Compose, Jenkins.

## Run
```bash
cp .env.example .env
docker compose up -d --build
curl http://localhost:8080/api/info
```

## Endpoints
| Method | URL | Description |
|---|---|---|
| GET | /api/info | Student name and app version |
| POST | /api/students | Create student |
| GET | /api/students | Get all students |
| GET | /api/students/{id} | Get student by id |
| PUT | /api/students/{id} | Update student |
| DELETE | /api/students/{id} | Delete student |
