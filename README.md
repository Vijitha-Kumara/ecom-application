# E-Commerce Application

A small Spring Boot REST application that provides in-memory CRUD operations for users.

## Project structure

```text
ecom-application/
├── .github/                       # GitHub configuration and workflows
├── .mvn/                          # Maven Wrapper support files
├── src/
│   ├── main/
│   │   ├── java/com/app/ecom/
│   │   │   ├── EcomApplication.java  # Spring Boot application entry point
│   │   │   ├── User.java             # User data model
│   │   │   ├── UserController.java  # HTTP endpoints under /api/users
│   │   │   └── UserService.java     # In-memory user operations
│   │   └── resources/
│   │       └── application.properties # App name and server port
│   └── test/java/com/app/ecom/
│       └── EcomApplicationTests.java # Application tests
├── pom.xml                         # Maven dependencies and build configuration
├── mvnw                            # Maven Wrapper (Unix-like systems)
└── mvnw.cmd                        # Maven Wrapper (Windows)
```

## How the application is organized

Requests to `UserController` are handled by `UserService`, which keeps users in a Java list. `User` is the model returned by the API. The service currently stores data only in memory, so users are cleared when the application stops.

## Requirements

- Java 25
- Maven (optional; the included Maven Wrapper can be used instead)

## Run the application

On Windows:



The server listens on `http://localhost:8081`.

## User API

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/api/users` | Return all users |
| `GET` | `/api/users/{id}` | Return one user, or `404` if not found |
| `POST` | `/api/users` | Create a user; the server assigns its ID |
| `PUT` | `/api/users/{id}` | Update a user's first and last name, or return `404` |

Example request body for creating or updating a user:

```json
{
  "firstName": "Ada",
  "lastName": "Lovelace"
}
```

## Build and test

```powershell
./mvnw.cmd test
```

Use `./mvnw test` on macOS or Linux.
