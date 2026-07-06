# Architecture & Contribution Guide

This document explains **how the project is structured** and **how to add a feature**.
Read it once before you write any code. Every feature follows the exact same pattern,
so once you understand one, you can build any of them.

---

## 1. The big picture

This is a layered Spring Boot REST API. A request flows through the layers top to bottom:

```
   HTTP request (JSON)
        │
        ▼
   ① Controller   receives the request, returns a response   (uses DTOs)
        │
        ▼
   ② Service      business logic and rules
        │
        ▼
   ③ Repository   reads/writes the database
        │
        ▼
   ④ Entity       a database table (already built ✅)

   ⑤ DTOs         request / response objects — what goes in and out.
                  We never send Entity objects directly to the client.
```

**Why layers?** Each layer has one job. The controller knows nothing about the
database; the repository knows nothing about HTTP. This keeps the code easy to
read, test, and split between teammates.

---

## 2. Full project structure

`✅ done` · `⬜ to build` · `⚠️ needs attention`

```
sit-internship-management-project/
├── pom.xml                                          ✅ done
├── README.md                                        ✅ done (setup + git workflow)
├── docs/
│   └── ARCHITECTURE.md                              ✅ this file
└── src/
    ├── main/
    │   ├── java/org/example/internship_system/
    │   │   ├── InternshipSystemApplication.java     ✅ done — entry point
    │   │   │
    │   │   ├── config/                              shared foundation
    │   │   │   ├── SecurityConfig.java              ✅ done — dev stub (open endpoints + BCrypt)
    │   │   │   └── ModelMapperConfig.java           ⬜ (optional) entity ↔ DTO mapping
    │   │   │
    │   │   ├── entity/                              ✅ DONE
    │   │   │   ├── User.java
    │   │   │   ├── StudentProfile.java
    │   │   │   ├── Company.java
    │   │   │   ├── InternshipOffer.java
    │   │   │   ├── Application.java
    │   │   │   └── enums/  Role, OfferStatus, WorkType, ApplicationStatus
    │   │   │
    │   │   ├── repository/                          ⬜ one interface per entity
    │   │   │   ├── UserRepository.java
    │   │   │   ├── StudentProfileRepository.java
    │   │   │   ├── CompanyRepository.java
    │   │   │   ├── InternshipRepository.java
    │   │   │   └── ApplicationRepository.java
    │   │   │
    │   │   ├── dtos/
    │   │   │   ├── request/                         ⬜ data coming IN from the client
    │   │   │   │   ├── LoginRequest.java
    │   │   │   │   ├── RegisterRequest.java
    │   │   │   │   ├── CompanyRequest.java
    │   │   │   │   ├── InternshipRequest.java
    │   │   │   │   └── ApplicationRequest.java
    │   │   │   └── response/                        ⬜ data going OUT to the client
    │   │   │       ├── AuthResponse.java
    │   │   │       ├── UserResponse.java
    │   │   │       ├── CompanyResponse.java
    │   │   │       ├── InternshipResponse.java
    │   │   │       └── ApplicationResponse.java
    │   │   │
    │   │   ├── service/                             ⬜ business logic
    │   │   │   ├── AuthService.java
    │   │   │   ├── CompanyService.java
    │   │   │   ├── InternshipService.java
    │   │   │   └── ApplicationService.java
    │   │   │
    │   │   ├── controller/                          ⬜ REST endpoints
    │   │   │   ├── AuthController.java
    │   │   │   ├── CompanyController.java
    │   │   │   ├── InternshipController.java
    │   │   │   └── ApplicationController.java
    │   │   │
    │   │   └── exception/                           ⬜ shared error handling
    │   │       ├── GlobalExceptionHandler.java
    │   │       └── ResourceNotFoundException.java
    │   │
    │   └── resources/
    │       ├── application.properties               ✅ done (Postgres)
    │       └── application-local.properties          (gitignored, per-machine override)
    │
    └── test/
        └── java/org/example/internship_system/      ⬜ each owner tests their own feature
```

---

## 3. The entities (already done — your data model)

| Entity | Table | Key relationships |
|---|---|---|
| `User` | `users` | the login account; has a `Role` (ADMIN / STUDENT / COMPANY) |
| `StudentProfile` | `student_profiles` | `@OneToOne` → `User` |
| `Company` | `companies` | `@OneToOne` → `User` |
| `InternshipOffer` | `internship_offers` | `@ManyToOne` → `Company` |
| `Application` | `applications` | `@ManyToOne` → `StudentProfile`, `@ManyToOne` → `InternshipOffer` |

Relationships are **one-directional** (the "many" side holds the link). To find the
"other side", you query the repository — e.g. *"all internships for this company"* is
`internshipRepository.findByCompany(company)`.

---

## 4. How to add a feature — the 5-step recipe

The entity is already built, so for any resource (e.g. `Company`) you create **4 files**:

```
repository/CompanyRepository.java
dtos/request/CompanyRequest.java
dtos/response/CompanyResponse.java
service/CompanyService.java
controller/CompanyController.java
```

Naming rule (follow it exactly so everything stays consistent):

| File | Name pattern | Example |
|---|---|---|
| Repository | `XxxRepository` | `CompanyRepository` |
| Request DTO | `XxxRequest` | `CompanyRequest` |
| Response DTO | `XxxResponse` | `CompanyResponse` |
| Service | `XxxService` | `CompanyService` |
| Controller | `XxxController` | `CompanyController` |

> **Code style:** plain JPA / Spring — **no Lombok**. Write getters/setters by hand,
> just like the entities and like the other lab projects. Use constructor injection
> (a constructor that receives the repository/service), not `@Autowired` on fields.

---

## 5. Worked example: the `Company` feature

Use this as your template. Copy the shape, swap the names for your own resource.

### ① Repository — `repository/CompanyRepository.java`
```java
package org.example.internship_system.repository;

import org.example.internship_system.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    // JpaRepository already gives you: save, findById, findAll, deleteById, ...
    // Add custom finders by just declaring them, e.g.:
    // Optional<Company> findByName(String name);
}
```
That's it — Spring Data writes the implementation for you.

### ② Request DTO — `dtos/request/CompanyRequest.java`
Only the fields the client is allowed to send when creating/updating a company.
```java
package org.example.internship_system.dtos.request;

public class CompanyRequest {
    private String name;
    private String description;
    private String website;
    private String contactEmail;
    private String city;

    // default constructor + getters + setters
}
```

### ③ Response DTO — `dtos/response/CompanyResponse.java`
Only the fields you want to expose back to the client (never the password, etc.).
```java
package org.example.internship_system.dtos.response;

public class CompanyResponse {
    private Long id;
    private String name;
    private String description;
    private String website;
    private String contactEmail;
    private String city;

    // default constructor + getters + setters
}
```

### ④ Service — `service/CompanyService.java`
The business logic. Converts DTO ↔ Entity and calls the repository.

```java
package org.example.internship_system.service;

import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // CompanyResponse create(CompanyRequest request) { ... }
    // List<CompanyResponse> getAll() { ... }
    // CompanyResponse getById(Long id) { ... }
    // CompanyResponse update(Long id, CompanyRequest request) { ... }
    // void delete(Long id) { ... }
}
```

### ⑤ Controller — `controller/CompanyController.java`
Maps URLs to service methods.

```java
package org.example.internship_system.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // @PostMapping            create(@RequestBody CompanyRequest request)
    // @GetMapping             getAll()
    // @GetMapping("/{id}")    getById(@PathVariable Long id)
    // @PutMapping("/{id}")    update(@PathVariable Long id, @RequestBody CompanyRequest request)
    // @DeleteMapping("/{id}") delete(@PathVariable Long id)
}
```

---

## 6. Suggested REST endpoints per feature

| Feature | Endpoints (base path) |
|---|---|
| **Company** | `POST /api/companies`, `GET /api/companies`, `GET /api/companies/{id}`, `PUT /api/companies/{id}`, `DELETE /api/companies/{id}` |
| **Internship** | `POST /api/internships`, `GET /api/internships`, `GET /api/internships/{id}`, `PUT /api/internships/{id}`, `DELETE /api/internships/{id}` (+ filter by company) |
| **Application** | `POST /api/applications` (a student applies), `GET /api/applications`, `GET /api/applications/{id}`, `PUT /api/applications/{id}/status` (company accepts/rejects) |

---

## 7. Team ownership (3 people)

| Owner | Feature | Files to create |
|---|---|---|
| Person 1 (lead) | **Application** (most complex — links students ↔ internships, accept/reject) + shared `config/SecurityConfig` (dev stub) and `exception/` | `ApplicationRepository`, `ApplicationRequest/Response`, `ApplicationService`, `ApplicationController` |
| Person 2 | **Internship offer** | `InternshipOfferRepository`, `InternshipOfferRequest/Response`, `InternshipOfferService`, `InternshipController` |
| Person 3 | **Company** | `CompanyRepository`, `CompanyRequest/Response`, `CompanyService`, `CompanyController` |

**Authentication / login** is built last by the lead, after the features exist.

---

## 8. Security: build it LAST

Spring Security currently locks every endpoint behind a generated password.
For development, the lead adds a **permit-all** config so everyone can test freely:

```java
// config/SecurityConfig.java  — DEVELOPMENT ONLY
// Allows every request without login while we build the features.
// Replace with real authentication (login, BCrypt, roles) at the end.
```

When all features work, replace it with real authentication: register/login
endpoints, BCrypt password hashing, and role-based access (`STUDENT` vs `COMPANY`).

---

## 9. Using AI tools to build your feature

You can ask an AI assistant to generate your feature. Give it this context so the
output matches our project:

> "I'm working on a Spring Boot 4 project. Code style is **plain JPA, no Lombok**,
> hand-written getters/setters, constructor injection. Using the `Company` feature in
> `docs/ARCHITECTURE.md` as the template, generate the **Internship** feature: the
> repository, request/response DTOs, service (with create/getAll/getById/update/delete),
> and controller with REST endpoints under `/api/internships`. The `Internship` entity
> already exists in `entity/Internship.java` and has a `@ManyToOne` link to `Company`."

Always **read and understand** what the AI produces, run it, and make sure it matches
the pattern in this document before committing.

---

## 10. Definition of done (per feature)

Before you open a Pull Request, check:

- [ ] All 4–5 files created and named per the convention
- [ ] App still starts (`InternshipSystemApplication` runs with no errors)
- [ ] Your endpoints work (test in browser / Postman / `curl`)
- [ ] Hibernate creates/uses your table with no errors in the console
- [ ] No Lombok, constructor injection used
- [ ] Branch named `feature/<your-feature>` and one clear commit/PR
