#Welcome to certification_nlw_application - JAVA (Spring Boot)!

## Description

certification_nlw_application is a Java Spring Boot application built with Java 17 and managed with Maven. It serves as a robust platform for managing student certifications. The application allows you to maintain a list of students, administer exams, and issue certifications seamlessly.

## Features

- **Student Management:** Easily add, update, or remove students from the system.
- **Exam Administration:** Create and customize exams tailored to your requirements.
- **Automated Grading:** Instantly evaluate student performance and generate detailed reports.
- **Certification Management:** Track student certifications and issue personalized certificates.
- **Security:** Ensures data privacy and confidentiality with robust security measures.

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/Pedrowesley/certification_nlw_application
   ```

2. Navigate to the project directory:

   ```bash
   cd certification_nlw_application
   ```

3. Build the project using Maven:

   ```bash
   mvn clean install
   ```

4. Run the application:

   ```bash
   mvn spring-boot:run
   ```

## Usage

- Access the application at `http://localhost:8085` (or another port if configured differently).
- Follow the on-screen instructions to manage students, administer exams, and issue certifications.

## Configuration

- Configure application properties in `src/main/resources/application.properties` as needed.
- Customize exam templates and certification formats in the respective files.

## Running with Docker

This application uses PostgreSQL as its database. To run the PostgreSQL database in a Docker container, you can use Docker Compose.

1. Ensure Docker and Docker Compose are installed on your machine. If not, you can download them from the [Docker website](https://www.docker.com/get-started).

2. Navigate to the project directory:

   ```bash
   cd certification_nlw_application
   ```

3. Run the Docker Compose command:

   ```bash
   docker-compose up -d
   ```

This will start a PostgreSQL container named `postgres_nlw` running on port 5434. The database

pg_nlw `is created with` admin as both the username and password.

Remember, the application is configured to connect to this database. If you change any details (like the port, username, password, or database name), make sure to update the application's configuration in `src/main/resources/application.properties` accordingly.
