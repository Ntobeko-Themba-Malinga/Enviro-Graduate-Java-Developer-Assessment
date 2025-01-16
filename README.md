# Enviro365 Waste Sorting Application

## Project Overview
Enviro365 is a waste sorting mobile application aimed at promoting sustainable waste management practices. This project involves building a **Spring Boot** backend that facilitates communication with the frontend through REST APIs. The backend supports functionalities like waste category lookups, disposal guideline retrieval, and recycling tips.

---

## Features
- RESTful APIs for:
  - Retrieving and managing waste categories.
  - Displaying disposal guidelines.
  - Sharing recycling tips.
- **JSON-formatted responses** for structured data exchange.
- Input validation using Spring Boot annotations.
- In-memory database integration using **H2 Database** for testing and prototyping.
- Structured and scalable code adhering to **Object-Oriented Design Principles**.

---

## Getting Started

### Prerequisites
- Java 17 or later
- Maven 3.6+
- IDE (e.g., IntelliJ IDEA)
- Git

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Ntobeko-Themba-Malinga/Ntobeko-Themba-Malinga-Enviro-Graduate-Java-Developer-Assessment.git
2. Open the project in IntelliJ, install the required packages, and run the project.

### API Documentation
After installing the packages and running the application, open your browser and go to the following link for the API endpoints documentation: http://localhost:8080/swagger-ui/index.html.

### Code Structure
The project follows a modular design with the following packages:

- `com.enviro.assessment.grad001.ntobekomalinga`
  - `controller`: REST controllers for handling API requests.
  - `service`: Business logic implementations.
  - `repository`: Data access layer with Spring Data JPA.
  - `model`: Entity classes representing database tables.

### Database ERD
![enviro365_db_erd drawio](https://github.com/user-attachments/assets/ec2b88f2-c50b-42d5-b6ef-47e6f4c23d57)
