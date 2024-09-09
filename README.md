# Attendance System API

## Project Description

The Attendance Management System is a web application designed for managing student attendance in a university setting. Built with Java Spring Boot, it provides a robust platform for tracking attendance, managing courses, and controlling access through role-based security.

## Create a Spring Boot Project Using Spring Initializr

1. **Go to Spring Initializr**:
   - Navigate to [Spring Initializr](https://start.spring.io/).

2. **Project Configuration**:
   - **Project Type**: Maven Project
   - **Language**: Java
   - **Spring Boot Version**: 3.2.7
   - **Packaging**: Jar
   - **Java Version**: 17

3. **Dependencies**:
   - Spring Web
   - Spring Data JPA
   - MySQL Driver
   - Lombok
   - Spring Security
   - Spring AOP
   - Spring Validation
   - SpringDoc OpenApi
  
   ## Configuration

### Application Properties

This project uses the following configuration settings in the `application.properties` file:

```properties
# Application settings
spring.application.name=Attendance-management-system
spring.main.banner-mode=off
server.servlet.context-path=/api/v1

# Database connection properties
spring.datasource.url=jdbc:mysql://localhost:3306/attendance_management?createDatabaseIfNotExist=true
spring.datasource.username=springstudent
spring.datasource.password=springstudent

# JPA and Hibernate properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.defer-datasource-initialization=true

# Security
# Uncomment and set these properties to enable basic authentication
#spring.security.user.name=alaaapuelsoad
#spring.security.user.password=alaa123

# Logging
logging.level.org.springframework.security=DEBUG

```

## Features

- **Admin Management**: CRUD operations for managing admins.
- **Instructor Management**: CRUD operations for managing instructors and assign course to instructors.
- **Student Management**: CRUD operations for managing students.
- **Course Management**: CRUD operations for managing course.
- **Enrollment Management**: Assign courses to students.
- **Attendance Records**: Mark attendance for students with the status.
- **Validation and Error Handling**: Input validation and graceful error handling.
- **Logging**: Logging of method calls, exceptions, and unique checker service using AOP.
- **Transaction Management**: Ensure data integrity with Spring's `@Transactional`.

## API Endpoints

### Admin Endpoints

- **GET /api/v1/admins**
  - Retrieve a list of all admins.

- **GET /api/v1/admins/{id}**
  - Retrieve details of a specific admin by ID.

- **POST /api/v1/admins**
  - Create a new admin.

- **PATCH /api/v1/admins/{id}**
  - Update details of a specific admin by ID.

- **DELETE /api/v1/admins/{id}**
  - Delete a specific admin by ID.

### Student Endpoints

- **GET /api/v1/students**
  - Retrieve a list of all students.

- **GET /api/v1/students/{id}**
  - Retrieve details of a specific student by ID.

- **POST /api/v1/students**
  - Create a new student.

- **PATCH /api/v1/students/{id}**
  - Update details of a specific student by ID.

- **DELETE /api/v1/students/{id}**
  - Delete a specific student by ID.

### Instructor Endpoints

- **GET /api/v1/instructors**
  - Retrieve a list of all instructors.

- **GET /api/v1/instructors/{id}**
  - Retrieve details of a specific instructor by ID.

- **POST /api/v1/instructors**
  - Create a new instructor.

- **PATCH /api/v1/instructors/{id}**
  - Update details of a specific instructor by ID.

- **DELETE /api/v1/instructors/{id}**
  - Delete a specific instructor by ID.

### Enroll a Student

- **POST /api/v1/enrollments**
  - **Description**: Enroll a student in a course.
  

- **GET /api/v1/enrollments/courses/{id}**
  - **Description**: Retrieve details of a student's enrollment in a specific course.
 

### Record Attendance

- **POST /api/v1/attendance**
  - **Description**: Record attendance for a student.

- **GET /api/v1/attendance/data**
  - **Description**: Retrieve a list of all attendance records.


### Roles Endpoints

- **POST /api/v1/roles**
  - **Description**: Create a new role.

- **GET /api/v1/roles/{roleName}**

- **GET /api/v1/roles**
  - **Description**: Retrieve a list of all roles.

- **DELETE /api/v1/roles/{id}**
  - **Description**: Delete a role by its ID.
  
## Data Storage

- **Database**: MySQL
- **Database Schema**:
![Database Schema](https://github.com/AlaaApuelsoad/Attendance_Management_System_V1/blob/main/Database%20Schema.png)


## Validation and Error Handling

- Input validation is implemented for all API requests.
- Exceptions are handled gracefully, returning appropriate HTTP status codes and error messages.


`@RestControllerAdvice`: Marks the class as a global exception handler for all `@RestController` components in the application.

Exception Handlers: Methods annotated with `@ExceptionHandler` to catch specific exceptions and return a standardized error response.

Error Response Structure: Uses the ErrorResponse class to structure the error messages and statuses.

Response Status: Each method is annotated with `@ResponseStatus` to set.


## Logging

- Logging is implemented using Aspect-Oriented Programming (AOP) to track method calls, exceptions.


## Transaction Management

- Declarative transaction management is implemented using Spring's `@Transactional` to ensure data integrity during critical operations.

# Project Version 1 API Documentation

You can access the API documentation for this project at the following URL:

[API Documentation](http://localhost:8080/api/v1/swagger-ui/index.html)

To view detailed information about the request bodies and responses for each endpoint, please ensure the application is running locally on port 8080 and navigate to the Swagger UI. There, you can explore the API endpoints, view their descriptions, and see example requests and responses.


