# API Foro

This is a Spring Boot API for user authentication using JWT.

## Requirements

- Java 11 or higher
- Maven
- Spring Boot
- H2 Database
- Spring Security
- JWT (Java JWT)

## Setup

1. Clone the repository:
    ```sh
    git clone <repository-url>
    cd <repository-directory>
    ```

2. Build the project:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn spring-boot:run
    ```

## Endpoints

### Authentication

#### POST /login

Authenticate a user and return a JWT token.

- **URL:** `/login`
- **Method:** `POST`
- **Consumes:** `application/json`
- **Produces:** `application/json`
- **Request Body:**
    ```json
    {
        "nombre": "username",
        "contrasena": "password"
    }
    ```
- **Response:**
    - **Success (200 OK):**
        ```json
        {
            "token": "jwt-token"
        }
        ```
    - **Unauthorized (401 Unauthorized):**
        ```json
        {
            "message": "Unauthorized"
        }
        ```

## Example Request

Using `curl`:
```sh
curl -X POST http://localhost:8080/login \
    -H "Content-Type: application/json" \
    -d '{"nombre": "username", "contrasena": "password"}'
