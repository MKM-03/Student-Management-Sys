# Student Management System

A backend REST API for managing students, courses, enrollments, and academic progression. Built with Spring Boot and JPA as a portfolio project to demonstrate backend development skills.

---

## Tech Stack

- **Java 21**
- **Spring Boot 3.5**
- **Spring Data JPA / Hibernate**
- **H2 Database** (in-memory, for development)
- **Maven**

---

## Features

- Student registration, lookup, and status management
- Course creation and department-based filtering
- Semester enrollment with credit hour validation (min 12, max 24)
- Duplicate enrollment prevention
- Grade assignment and GPA calculation
- Transcript generation per student
- Year level promotion

---

## Project Structure

```
com.mkm.sms
├── controller      # REST API endpoints
├── entity          # JPA entities (Student, Course, Enrollment, etc.)
├── enums           # Status and type enumerations
├── repository      # Spring Data JPA repositories
└── service         # Business logic
```

---

## API Endpoints

### Students
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/students` | Register a new student |
| GET | `/api/students` | Get all students |
| GET | `/api/students/{id}` | Get student by ID |
| GET | `/api/students/name/{name}` | Get student by name |
| GET | `/api/students/department/{department}` | Get students by department |
| PUT | `/api/students/{id}/status?newStatus=` | Update student status |
| PUT | `/api/students/{id}/promote` | Promote student to next year |
| PUT | `/api/students/{id}/gpa?newGpa=` | Update student GPA |
| DELETE | `/api/students/{id}` | Delete a student |

### Courses
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/courses` | Create a new course |
| GET | `/api/courses` | Get all courses |
| GET | `/api/courses/{id}` | Get course by ID |
| GET | `/api/courses/code/{code}` | Get course by code |
| GET | `/api/courses/department/{department}` | Get courses by department |
| DELETE | `/api/courses/{id}` | Delete a course |

### Enrollments
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/enrollments` | Enroll student in a course |
| POST | `/api/enrollments/finalize` | Finalize semester enrollment |
| POST | `/api/enrollments/grade` | Assign grade to a course |
| GET | `/api/enrollments/{studentId}` | Get all semester records for a student |
| GET | `/api/enrollments/transcript/{studentId}` | Generate student transcript |

---

## Running the Project

```bash
# Clone the repository
git clone https://github.com/MKM-03/Student-Management-Sys.git

# Switch to the Spring Boot branch
git checkout spring

# Run the application
./mvnw spring-boot:run
```

The app runs on `http://localhost:8080` by default.
H2 console is available at `http://localhost:8080/h2-console` for inspecting the database.

---

## Design Decisions

- **Layered architecture** — strict separation between controllers, services, and repositories
- **Repository pattern** — plain Java in-memory repositories were built first, then migrated to Spring Data JPA to demonstrate the pattern
- **Constructor injection** — used throughout instead of field injection for better testability
- **Enum-based status management** — all status transitions are type-safe via enums

---

## Roadmap

- [ ] MySQL integration for production
- [ ] Input validation with `@Valid` and Bean Validation
- [ ] Exception handling with `@ControllerAdvice`
- [ ] Unit tests with JUnit 5 and Mockito
- [ ] API documentation with Swagger / OpenAPI
