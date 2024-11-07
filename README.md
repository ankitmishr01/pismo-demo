# Transaction Service Application

## Description
This a sample application that provides REST endpoints for creating and managing accounts and transactions. It uses MySQL as the database and Lombok for reducing boilerplate code.

## Features
- Create accounts and transactions.
- Retrieve account details.
- Exception handling with custom error messages.
- Logging with Log4j2.

## Prerequisites
- Java 17
- Maven
- MySQL
- Docker (optional)

## How to Run
1. Configure MySQL in `application.properties`.
2. Run the application: `mvn spring-boot:run`
3. Or use Docker:
   ```bash
   docker build -t transaction-app .
   docker run -p 8080:8080 transaction-app
