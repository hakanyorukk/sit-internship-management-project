# Internship Management System

A Spring Boot REST API for managing student internship applications.
Students apply to internships posted by companies; companies review applications
and accept or reject them.

> University coursework project — TU Varna.

## Tech stack

- **Backend:** Java 21, Spring Boot 4.0.5, Spring Web MVC, Spring Data JPA, Spring Security
- **Database:** PostgreSQL
- **Frontend:** React (separate repository — TBD)
- **Build:** Maven

## Prerequisites

You need installed:

- JDK 21 (with `javac`, not just the JRE) — on Fedora: `sudo dnf install java-21-openjdk-devel`
- Maven 3.9+
- PostgreSQL 14+
- IntelliJ IDEA (recommended) or any IDE with Spring support

## Local setup

### 1. Create the database

```bash
sudo -u postgres psql -c "CREATE DATABASE internship_db;"
sudo -u postgres psql -c "ALTER USER postgres WITH PASSWORD 'postgres';"
```

### 2. (Optional) Override DB credentials locally

Create `src/main/resources/application-local.properties` (already in `.gitignore`):

```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

Then run with profile `local`: `--spring.profiles.active=local`.

### 3. Build and run

```bash
./mvnw spring-boot:run
```

Or open the project in IntelliJ and run `InternshipSystemApplication`.

The app starts on **http://localhost:8080**.

## Project structure

```
src/main/java/org/example/internship_system/
├── InternshipSystemApplication.java   # entry point
├── model/         # JPA entities
├── repository/    # Spring Data repositories
├── service/       # business logic
├── controller/    # REST endpoints
├── dto/           # request/response DTOs
└── config/        # security, CORS, etc.
```

## Contributing

1. Create a feature branch: `git checkout -b feature/your-feature`
2. Commit your changes
3. Push and open a Pull Request against `main`
4. Wait for review before merging
