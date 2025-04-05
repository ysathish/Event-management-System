#  Event Management System

A full-stack event management application built with **Spring Boot**, and **PostgreSQL**. It is the CRUD operations.

## Technologies Used

- Spring Boot
- PostgreSQL
- JPA / Hibernate
- Swagger (OpenAPI)
- Lombok
- Maven

## Database Schema

### PostgreSQL Table: `event`

```sql
CREATE TABLE event (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    date DATE NOT NULL,
    location VARCHAR(255)
);

Getting Started
Prerequisites
Java 17+
Maven
PostgreSQL (set up and running)
