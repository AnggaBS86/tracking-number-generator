# Tracking Number Generator API

This is a lightweight Spring Boot application that generates unique tracking numbers for shipping orders based on input parameters like origin/destination countries, order weight, creation time, and customer identity.

---

## Features

- REST API to generate tracking numbers
- Validation for all inputs (UUID, ISO country codes, weight format, kebab-case slug, RFC3339 timestamps)
- Data persistence using PostgreSQL
- Unique tracking number generator logic
- Unit tests using JUnit + Spring Boot test

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- MySQL 8+
- Git

### Clone the Repository

```bash
git clone https://github.com/your-org/numgen.git
cd numgen
```

---

### DB COnfiguration

application.properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/numgen?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

---

## Run The App

```bash
./mvnw spring-boot:run
```
## RUn the Unit Test
```bash
./mvnw test
```

---

# Tracking Number Generator API

This API generates a unique tracking number based on input parameters such as origin, destination, weight, and customer details. The tracking number format can follow customizable rules (e.g. `MYID-RBX-20250721-000001`).

---

## Base URL
```
http://localhost:8080
```


---

## Endpoint

### `GET /next-tracking-number`

Generate a new tracking number.

---

## Query Parameters

| Parameter              | Type      | Required | Description                                                                 |
|------------------------|-----------|----------|-----------------------------------------------------------------------------|
| `origin_country_id`    | `string`  | ✅ Yes   | Origin ISO 3166-1 alpha-2 country code (e.g. `MY`)                          |
| `destination_country_id` | `string` | ✅ Yes   | Destination ISO 3166-1 alpha-2 code (e.g. `ID`)                             |
| `weight`               | `decimal` | ✅ Yes   | Weight of the package (max 3 decimal places, e.g. `1.234`)                  |
| `created_at`           | `string`  | ✅ Yes   | Timestamp in RFC3339 format (e.g. `2025-07-21T10:30:00+07:00`)             |
| `customer_id`          | `UUID`    | ✅ Yes   | Customer UUID (e.g. `de619854-b59b-425e-9db4-943979e1bd49`)                |
| `customer_name`        | `string`  | ✅ Yes   | Customer name (e.g. `RedBox Logistics`)                                     |
| `customer_slug`        | `string`  | ✅ Yes   | Slugified version of customer name in kebab-case (e.g. `redbox-logistics`)  |

---

## Response

### Success Response (`200 OK`)

```json
{
  "tracking_number": "MYID-RBX-20250721-000001"
}
```

### Validation Error 
```json
{
  "error": "Validation failed",
  "details": [
    "Weight can have up to 3 decimal places"
  ]
}
```

### Example Request 
```bash
curl "http://localhost:8080/next-tracking-number?origin_country_id=MY&destination_country_id=ID&weight=1.234&created_at=2025-07-21T10:30:00%2B07:00&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox%20Logistics&customer_slug=redbox-logistics"

```

