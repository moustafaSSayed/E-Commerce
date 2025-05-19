# E-Commerce Spring Boot Application

## Overview
This is a Spring Boot-based e-commerce backend application that provides RESTful APIs for managing products, categories, and user carts. It implements secure authentication and authorization using JWT tokens and Spring Security. The project also leverages advanced JPA mappings for entity relationships, validation, exception handling, and structured logging for robust and maintainable code.

---

## Features

- **User Management:** Registration and login endpoints with JWT-based authentication.
- **Role-Based Access Control:** 
  - Admin users can create, update, and delete products and categories.
  - Regular users can browse products, view categories, and manage their carts.
- **REST API Endpoints:**
  - `/api/v1/products/**` for product operations.
  - `/api/v1/categories/**` for category operations.
  - `/api/v1/cart/**` for user cart operations.
  - Public endpoints for user registration and login.
- **Advanced JPA Mappings:**
  - `@OneToOne`: For example, linking product details or user profiles.
  - `@OneToMany`: For example, one category having multiple products.
  - `@ManyToMany`: For example, products tagged with multiple categories or tags.
- **Validation:** Input validation using Spring Validation annotations (e.g., `@NotNull`, `@Size`).
- **Exception Handling:** Centralized exception handling with `@ControllerAdvice` for consistent API error responses.
- **Structured Logging:** Application-wide logging with context using frameworks such as SLF4J and Logback or Log4j2.
- **Security Configuration:** Uses a custom `JwtFilter` to intercept and validate JWT tokens in requests.

--

## Dependencies

This project uses Maven with the following main dependencies (defined in `pom.xml`):

- **Spring Boot Starter Web**: Facilitates REST API development.
- **Spring Boot Starter Security**: Provides authentication and authorization mechanisms.
- **Spring Security JWT**: Supports JWT token authentication.
- **Spring Boot Starter Data JPA**: Enables Object-Relational Mapping (ORM) and database interactions.
- **Hibernate Validator**: Provides input validation annotations for data integrity.
- **Lombok**: Reduces boilerplate code with annotations like `@RequiredArgsConstructor`, `@Getter`, and `@Setter`.
- **Database Driver**: Supports databases such as PostgreSQL or H2, depending on the environment.
- **Logging Frameworks**: Utilizes SLF4J with Logback or Log4j2 for structured logging.

--

## API Endpoints Summary

The following table summarizes the API endpoints, their methods, roles required, and descriptions:

| Endpoint                     | Method | Role        | Description                |
|------------------------------|--------|-------------|----------------------------|
| `/user/register`             | POST   | Public      | Register new users         |
| `/user/login`                | POST   | Public      | User login                 |
| `/api/v1/products/all`       | GET    | USER, ADMIN | List all products          |
| `/api/v1/products/{id}`      | GET    | USER, ADMIN | Get product details        |
| `/api/v1/products/create`    | POST   | ADMIN       | Create new product         |
| `/api/v1/products/update/{id}`| PUT   | ADMIN       | Update product             |
| `/api/v1/products/delete/{id}`| DELETE| ADMIN       | Delete product             |
| `/api/v1/categories/all`     | GET    | USER, ADMIN | List all categories        |
| `/api/v1/categories/{id}`    | GET    | USER, ADMIN | Get category details       |
| `/api/v1/categories/create`  | POST   | ADMIN       | Create new category        |
| `/api/v1/categories/update/{id}`| PUT  | ADMIN       | Update category           |
| `/api/v1/categories/delete/{id}`| DELETE| ADMIN      | Delete category           |
| `/api/v1/cart/**`            | Various| USER, ADMIN | Cart management            |

--

# How to Run

This guide outlines the steps to run the Spring Boot application.

## Prerequisites

* **Git:** Ensure Git is installed on your system. You can check its installation by running `git --version` in your terminal.
* **Maven:** This project uses Maven for building and managing dependencies. Download and install Maven from the official Apache Maven website (https://maven.apache.org/download.cgi). Verify your installation by running `mvn -v`.
* **Java Development Kit (JDK):** A compatible JDK is required to build and run the Spring Boot application. Make sure you have a suitable version installed and configured. You can check your Java version by running `java -version`.

## Steps

1.  **Clone the repository:**

    Open your terminal and navigate to the directory where you want to clone the project. Then, execute the following command:

    ```bash
    git clone [https://github.com/yourusername/your-repo.git](https://github.com/yourusername/your-repo.git)
    ```

    Replace `https://github.com/yourusername/your-repo.git` with the actual URL of the repository.

2.  **Navigate to the project directory:**

    After cloning, change your current directory in the terminal to the newly created project folder:

    ```bash
    cd your-repo
    ```

3.  **Build the project:**

    Use Maven to clean and build the project. This will download dependencies and compile the code:

    ```bash
    mvn clean install
    ```

    This command might take a few minutes depending on your internet connection and project size. Ensure the build is successful before proceeding.

4.  **Run the Spring Boot application:**

    Once the build is complete, you can run the Spring Boot application using the Maven Spring Boot plugin:

    ```bash
    mvn spring-boot:run
    ```

    This command will start the embedded Tomcat server and run your application. You should see log messages in the terminal indicating the application has started successfully.

5.  **Access the API:**

    After the application has started, the API will be accessible at the following URL in your web browser or using an API testing tool:

    ```
    http://localhost:8080
    ```

    You can now interact with the endpoints defined in this Spring Boot application.
