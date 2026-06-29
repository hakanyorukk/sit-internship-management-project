# Student Internship Management System

A Spring Boot REST API that manages the full internship process: companies publish
internship offers, students browse and apply, and companies review applications and
update their status. Administrators manage the platform. A separate **React** frontend
consumes the REST API.

> University coursework — Technical University of Varna, Software and Internet
> Technologies (SIT), Year 3. Team of 3.
> Full requirements: [docs/Internship_Project_Brief.pdf](docs/Internship_Project_Brief.pdf)

---

## Team & responsibilities

| Member | GitHub | Modules / responsibility |
|---|---|---|
| _Name (lead)_ | @_handle_ | **Application** feature, **React frontend**, shared security & exception handling |
| _Name_ | @_handle_ | **Internship offer** feature |
| _Name_ | @_handle_ | **Company** feature + **Admin/statistics** |

> Each member also keeps a short statement in [CONTRIBUTIONS.md](CONTRIBUTIONS.md)
> describing their modules, key commits, and technical decisions (required by the brief, §10).

---

## Tech stack

| Area | Technology |
|---|---|
| Language | Java 21 |
| Backend | Spring Boot 4.0.5, Spring Web MVC |
| Persistence | Spring Data JPA + PostgreSQL 18 |
| Validation | Jakarta Bean Validation |
| Security | Spring Security with roles (ADMIN / STUDENT / COMPANY) |
| API docs | Swagger / OpenAPI (springdoc) — `/swagger-ui.html` |
| Frontend | React (separate app, consumes this REST API) |
| Build | Maven |
| IDE | IntelliJ IDEA |

---

## Prerequisites

Each teammate needs, once:

- **JDK 21+**
- **PostgreSQL 18** — [download for Windows](https://www.postgresql.org/download/windows/);
  set the `postgres` password to `postgres`, keep port `5432`
- **IntelliJ IDEA**
- **Node.js 18+** (only for the React frontend)

---

## Backend setup (do this once)

### 1. Clone
```bash
git clone <repository-url>
cd sit-internship-management-project
```

### 2. Create the database
```bash
psql -U postgres -c "CREATE DATABASE internship_db;"
```
> Windows, if `psql` isn't recognized:
> `& "C:\Program Files\PostgreSQL\18\bin\psql.exe" -U postgres -c "CREATE DATABASE internship_db;"`

### 3. Configure credentials
The committed `application.properties` does **not** contain real passwords. Copy the
example and fill in your own (the real file is gitignored):

```bash
cp src/main/resources/application.properties.example src/main/resources/application-local.properties
```

**Database configuration example** (`application.properties.example`):
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/internship_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
```

### 4. Run
Open `InternshipSystemApplication` in IntelliJ → click **▶ Run**.
The API starts on **http://localhost:8080**; Hibernate auto-creates the tables.

---

## API documentation

Once the app is running, interactive API docs are available at:

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- A Postman collection is also kept in `docs/` as a backup.

---

## Frontend (React)

The frontend is a **separate React app** that calls this REST API. It runs on its own
dev server (e.g. http://localhost:5173) and talks to the backend at http://localhost:8080.

> The backend must enable **CORS** for the frontend origin (a `config/CorsConfig` or
> CORS settings inside `SecurityConfig`) — added when frontend integration starts.

---

## Project structure

Full guide: **[docs/ARCHITECTURE.md](docs/ARCHITECTURE.md)**. Layered design:

```
controller/  →  service/  →  repository/  →  entity/
        (DTOs in/out)            (database tables, auto-generated)
```
Business logic lives in the **service** layer. Controllers only receive requests,
validate input, call services, and return responses.

---

## Roadmap (3 weeks — from the project brief)

| Week | Deliverable |
|---|---|
| **1** | Data layer & basic CRUD — entities, relations, repositories, schema, initial endpoints |
| **2** | Service layer & validation (DTOs, mapping, validation, central exception handling) + Authentication & permissions (roles, access restrictions) |
| **3** | Applications & UI/demo (core workflow, Swagger and/or React UI) + Testing, documentation & final defense |

---

## How we work with Git

One feature = one branch = one Pull Request. **`main` must always stay runnable.**

```bash
git checkout main && git pull
git checkout -b feature/company        # descriptive name
# ...build the whole feature...
git add .
git commit -m "feat: add company CRUD endpoints"
git push -u origin feature/company
# open a Pull Request -> main, get one teammate to review, then merge
```

**Rules (from the brief, §4):**
- Descriptive branch names: `feature/...`, `fix/...`
- Conventional commit messages: `feat:`, `fix:`, `docs:`, `test:`, `refactor:` —
  never "update", "work", "final", "asdf"
- Small, logical commits; **~8–10 meaningful commits per person**
- Review before merge; resolve conflicts together — never delete a teammate's work
- **Never commit** passwords, secrets, DB credentials, or `target/` `.idea/` `node_modules/`
- Repository name should follow `sit-internship-management-team-XX`; add the supervisor
  as a collaborator

---

## Roles & access

| Role | Can do |
|---|---|
| **STUDENT** | Create profile, browse/search offers, apply (once per offer), track own applications |
| **COMPANY** | Create/edit/close own offers, review applications for own offers, change status |
| **ADMIN** | Manage companies/users/offers, view platform statistics |

---

## Known limitations & optional features

- [ ] _List anything not finished here_
- [ ] Optional (bonus) features implemented: CV upload, email notifications, JWT,
      pagination/sorting, audit log, Docker Compose, automated tests — _tick what you do_

---

## Verifying it works

After running, check **pgAdmin → internship_db → Schemas → public → Tables**:
`users`, `student_profiles`, `companies`, `internship_offers`, `applications`.
Then exercise the main flow: register → login → publish offer → apply → change status.
