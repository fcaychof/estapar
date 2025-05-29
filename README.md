## Estapar - Desafio Java

## API & System Technical Documentation

### Autor: Fernando Miguel Caycho Feria

- [OCJP](https://www.youracclaim.com/badges/fdad74d8-20a3-4a61-8e60-2fe273ea12ff/linked_in_profile)
- [OCJWS](https://www.credly.com/badges/843dabc3-06cc-4e5e-af98-fde6132ae9fb/linked_in_profile)
- [AWS](https://www.credly.com/badges/b2cd8608-12d2-4f5d-b6d7-476a1f9c5342/public_url)

## Requirements

For building and running the application you need:

- [JDK 24](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3](https://maven.apache.org)

### Overview

Estapar is a Spring Boot REST API for managing garages, parking spots, and vehicles, supporting geolocation and dynamic
pricing.

### Architecture

- **Backend:** Java 24, Spring Boot, Spring Data JPA
- **Database:** JPA-compatible (e.g., H2, PostgreSQL)
- **Build:** Maven

### Main Entities

- **Sector:** Represents a garage sector.
- **Spot:** Parking spot with latitude, longitude, occupancy, and sector.
- **Vehicle:** Vehicle with license plate, entry/exit times, spot, and sector.

### API Endpoints

#### Vehicle

- `POST /webhook/event`
    - Handles vehicle ENTRY, PARKED, and EXIT events.
    - **Request:**
      ```json
      {
        "eventType": "ENTRY|PARKED|EXIT",
        "licensePlate": "ABC1234",
        "lat": -23.5,
        "lng": -46.6
      }
      ```
    - **Response:** 200 OK or error

- `GET /vehicle/status/{licensePlate}`
    - Returns vehicle status, entry time, time parked, and spot location.

#### Spot

- `GET /spot/status?lat={lat}&lng={lng}`
    - Returns spot status (occupied, vehicle info).

#### Revenue

- `GET /revenue?date={yyyy-MM-dd}&sector={sectorName}`
    - Returns total revenue for a sector and date.

### Data Model

- **Spot:**
    - `id: Long`
    - `sector: Sector`
    - `lat: double`
    - `lng: double`
    - `occupied: boolean`
    - `vehicle: Vehicle`

- **Vehicle:**
    - `licensePlate: String`
    - `entryTime: LocalDateTime`
    - `exitTime: LocalDateTime`
    - `totalPrice: double`
    - `spot: Spot`
    - `sector: Sector`

### Business Logic

- **Entry:** Registers vehicle entry.
- **Parked:** Updates spot as occupied.
- **Exit:** Updates spot as free, calculates price.

### Error Handling

- Returns 400 for invalid input or missing vehicle/spot.

### Running & Testing

- Build: `mvn clean install`
- Run: `mvn spring-boot:run`
- Test: `mvn test`

---
**Estapar** is a Java Spring Boot application for managing garages and parking spots. It uses Maven for build automation
and JPA for data persistence.

### Features

- Garage management
- Parking spot management with geolocation (latitude and longitude)
- RESTful API structure

### Project Structure

- `service/`: Business logic layer
- `repository/`: Data access layer (JPA repositories)
- `repository/model/`: Entity models

### Getting Started

1. Clone the repository.
2. Run `mvn clean install` to build the project.
3. Start the application with `mvn spring-boot:run`.

---
