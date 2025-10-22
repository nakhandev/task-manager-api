# Task Manager API

A REST API for managing personal tasks, similar to Todoist, built with Spring Boot, Hibernate, and MySQL.

## Features

- User registration and login with JWT authentication
- CRUD operations for tasks
- Filter tasks by status (PENDING, COMPLETED) and priority (LOW, MEDIUM, HIGH)
- Secure API endpoints with Spring Security

## Tech Stack

- **Framework**: Spring Boot 3.5.6
- **Database**: MySQL 8.0
- **ORM**: Hibernate (JPA)
- **Security**: Spring Security with JWT
- **Build Tool**: Maven
- **Language**: Java 17

## Prerequisites

- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

## Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/nakhandev/task-manager-api.git
   cd task-manager-api
   ```

2. **Set up MySQL database**:
   - Install MySQL and start the service.
   - Create a database named `taskdb`.
   - Update the database credentials in `src/main/resources/application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/taskdb
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```
   The application will start on port 1998 (configurable in `application.properties`).

   Alternatively, you can use the provided `start.sh` script:
   ```bash
   ./start.sh
   ```

## API Endpoints

### Authentication

- **Register a new user**:
  ```
  POST /auth/register
  Content-Type: application/json

  {
    "username": "testuser",
    "email": "test@example.com",
    "password": "password"
  }
  ```

- **Login**:
  ```
  POST /auth/login
  Content-Type: application/json

  {
    "username": "testuser",
    "password": "password"
  }
  ```
  Response: JWT token (e.g., "Bearer <token>")

### Tasks

All task endpoints require JWT authentication in the `Authorization` header.

- **Create a task**:
  ```
  POST /tasks
  Authorization: Bearer <token>
  Content-Type: application/json

  {
    "title": "Test Task",
    "description": "Description",
    "status": "PENDING",
    "priority": "HIGH"
  }
  ```

- **Get tasks**:
  ```
  GET /tasks
  Authorization: Bearer <token>
  ```
  Optional query parameters:
  - `status`: PENDING or COMPLETED
  - `priority`: LOW, MEDIUM, or HIGH

- **Update a task**:
  ```
  PUT /tasks/{id}
  Authorization: Bearer <token>
  Content-Type: application/json

  {
    "title": "Updated Task",
    "description": "Updated Description",
    "status": "COMPLETED",
    "priority": "MEDIUM"
  }
  ```

- **Delete a task**:
  ```
  DELETE /tasks/{id}
  Authorization: Bearer <token>
  ```

## Testing

Run unit tests:
```bash
mvn test
```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── org/nakhan/task_manager_api/
│   │       ├── controller/     # REST controllers
│   │       ├── model/          # Entities and DTOs
│   │       ├── repository/     # JPA repositories
│   │       ├── security/       # Security configuration and JWT utilities
│   │       ├── service/        # Business logic services
│   │       └── TaskManagerApiApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── org/nakhan/task_manager_api/
            └── service/        # Unit tests
```

## Configuration

Key configuration in `application.properties`:
- `server.port`: 1998
- `spring.datasource.url`: MySQL connection URL
- `spring.jpa.hibernate.ddl-auto`: update (creates tables automatically)
- `spring.jpa.show-sql`: true (for debugging)

## Security

- JWT tokens are required for protected endpoints.
- Passwords are hashed using BCrypt.
- CORS is not configured; adjust as needed for frontend integration.

## Contributing

1. Fork the repository.
2. Create a feature branch.
3. Make your changes.
4. Run tests.
5. Submit a pull request.

## License

This project is licensed under the MIT License.

## Contact

For questions or issues, please open an issue in the repository.
