# School Application - Teacher Management System

A secure, production-ready Spring Boot application for managing teachers and users with role-based access control and comprehensive authentication.

## Technology Stack

- Java 21 (Amazon Corretto)
- Spring Boot 3.5.3
- Spring Security 6
- Spring Data JPA
- Thymeleaf + Thymeleaf Security Extras
- MySQL 8.x
- Gradle 8.14.2
- Lombok
- BCrypt password encoding

## Architecture Overview

### Application Structure
```
schoolapp2/
├── authentication/     # Security configuration and custom auth providers
├── controller/         # MVC controllers for web endpoints
├── service/           # Business logic layer
├── repository/        # Data access layer (JPA repositories)
├── model/             # Entity models and domain objects
├── dto/               # Data transfer objects
├── mapper/            # Entity-DTO conversion utilities
├── validator/         # Custom validation logic
└── core/              # Exceptions and enums
```

### Security Model
- Role-based access control (RBAC) with capability-based permissions
- BCrypt password hashing (strength: 12)
- Session-based authentication with CSRF protection disabled
- Granular endpoint protection via Spring Security

### Database Schema
- Users with role assignments
- Teachers with regional associations
- Roles with capability mappings
- Static regional data for Greece

## Prerequisites

- Java 21 or higher
- MySQL 8.x running locally or accessible remotely
- Gradle 8.14.2 (included via wrapper)

## Environment Configuration

All sensitive credentials are externalized to environment variables. **No hardcoded secrets exist in the codebase.**

### Required Environment Variables

Create a `.env` file in the project root (see `.env.example` for template):

```bash
MYSQL_HOST=localhost
MYSQL_PORT=3306
MYSQL_DB=schoolappssr9db
MYSQL_USER=your_database_user
MYSQL_PASSWORD=your_secure_password
```

### Automatic .env Loading

The application uses **spring-dotenv** library to automatically load `.env` files on startup. Simply create your `.env` file in the project root and the variables will be available to Spring Boot.

**Quick Setup:**
```bash
# Copy the example template
cp .env.example .env

# Edit with your actual credentials
nano .env

# Run the application - .env loads automatically
./gradlew bootRun
```

### Alternative: System Environment Variables (Production)

For production deployments, use system environment variables instead of `.env` files:

```bash
export MYSQL_HOST=your_db_host
export MYSQL_PORT=3306
export MYSQL_DB=schoolappssr9db
export MYSQL_USER=your_db_user
export MYSQL_PASSWORD=your_secure_password
```

## Database Setup

1. Create the MySQL database:
```sql
CREATE DATABASE schoolappssr9db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. Create a database user and grant privileges:
```sql
CREATE USER 'your_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON schoolappssr9db.* TO 'your_user'@'localhost';
FLUSH PRIVILEGES;
```

3. The application will auto-create tables via Hibernate DDL (ddl-auto=update in dev profile).

4. To populate regional data, uncomment in `application-dev.properties`:
```properties
spring.sql.init.mode=always
spring.sql.init.data-locations=classpath:sql/regions.sql
```

## Build and Run

### Build the Application
```bash
# Clean and build (skip tests)
./gradlew clean build -x test

# Build with tests
./gradlew clean build
```

### Run the Application
```bash
# Using Gradle
./gradlew bootRun

# Using JAR
java -jar ./build/libs/schoolapp.jar
```

### Access the Application
- URL: `http://localhost:8080`
- Login page: `http://localhost:8080/login`

## Application Profiles

- **dev** (default): Development mode with SQL logging and auto-DDL
- **staging**: Staging environment configuration
- **prod**: Production environment configuration

Switch profiles:
```bash
java -jar schoolapp.jar --spring.profiles.active=prod
```

## API Endpoints

### Public Endpoints
- `GET /` - Home page
- `GET /login` - Login page
- `POST /school/users/register` - User registration

### Protected Endpoints (Authenticated)
- `GET /school/teachers` - List teachers (paginated)
- `POST /school/teachers/insert` - Create teacher (requires `EDIT_TEACHERS` capability)
- `GET /school/teachers/edit/{uuid}` - Edit teacher form (requires `EDIT_TEACHERS`)
- `POST /school/teachers/edit` - Update teacher (requires `EDIT_TEACHERS`)
- `GET /school/teachers/delete/{uuid}` - Delete teacher (requires `EDIT_TEACHERS`)

### Admin Endpoints
- `GET /school/admin/**` - Admin panel (requires `ADMIN` role)

## Security Features

### Implemented Protections
- Environment-based secrets management (no hardcoded credentials)
- BCrypt password hashing with high work factor
- Role-based access control with fine-grained capabilities
- Session management with secure logout
- Input validation via Jakarta Validation
- SQL injection protection via JPA/Hibernate parameterized queries
- XSS protection via Thymeleaf auto-escaping

### Security Best Practices
- All database credentials externalized to environment variables
- `.env` files excluded from version control
- HTTPS recommended for production (configure via Spring Boot)
- Regular dependency updates via Gradle

## Development Notes

### Database Migrations
The application uses Hibernate's `ddl-auto=update` in development. For production, consider using Flyway or Liquibase for controlled schema migrations.

### Logging
- SQL queries logged in dev profile
- Structured logging with SLF4J/Logback
- Security-related logs suppressed for cleaner output

### Testing
Run tests with:
```bash
./gradlew test
```

## Deployment

### Production Checklist
1. Set `spring.profiles.active=prod`
2. Configure all environment variables in production environment
3. Set `spring.jpa.hibernate.ddl-auto=validate` or `none`
4. Enable HTTPS and configure SSL certificates
5. Configure production database with connection pooling
6. Set up monitoring and logging aggregation
7. Review and harden `SecurityConfig` settings
8. Enable CSRF protection for production

### Docker Deployment (Optional)
```dockerfile
FROM amazoncorretto:21-alpine
WORKDIR /app
COPY build/libs/schoolapp.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Build and run:
```bash
docker build -t schoolapp .
docker run -p 8080:8080 \
  -e MYSQL_HOST=host.docker.internal \
  -e MYSQL_PORT=3306 \
  -e MYSQL_DB=schoolappssr9db \
  -e MYSQL_USER=user \
  -e MYSQL_PASSWORD=password \
  schoolapp
```

## Future Improvements

- Implement RESTful API alongside web interface
- Add comprehensive integration tests
- Implement Flyway/Liquibase for database migrations
- Add API documentation with OpenAPI/Swagger
- Implement rate limiting and brute-force protection
- Add audit logging for sensitive operations
- Implement email verification for user registration
- Add forgot password functionality
- Implement refresh tokens for API access
- Add Docker Compose for local development stack
- Implement caching layer (Redis/Caffeine)
- Add CI/CD pipeline configuration

