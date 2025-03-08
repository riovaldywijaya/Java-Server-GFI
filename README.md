# 📚 Library Java Service Documentation

## 📌 Prerequisites

Before running this application, ensure that you have started the required Node.js service:

[NodeJS Server GFI](https://github.com/riovaldywijaya/NodeJS-Server-GFI)

This service must be running before starting the Java application to create the `users` table and generate authentication tokens.

## 🔍 Overview

This application is a library management system that allows users to browse books and administrators to manage book borrowings. The system provides role-based access control where:

- **Users** can view books.
- **Admins** can manage books and borrowings.

## ⚙️ System Requirements

- **Java Version:** 17
- **Database:** Oracle
- **Port:** 8082
- **Build Tool:** Gradle
- **Framework:** Spring Boot

## 📦 Dependencies

The application requires the following dependencies:

- **Spring Boot**
- **Spring Security**
- **Flyway**
- **Oracle Database Driver**
- **Gradle**
- **JWT Authentication**

## 🚀 Running the Application

1. **Configure the database:** Ensure `application.yml` contains the correct database credentials.
2. **Flyway Migration:** Flyway will automatically create the `books` and `borrowings` tables and seed the initial data.
3. **Start the application:**
   ```sh
   ./gradlew bootRun   # Run the Spring Boot application (Flyway will run automatically)
   ```
   Alternatively, you can start the application directly from your IDE.

## 🗄 Database Schema

The application uses the following database tables:

- **📖 Books Table:** Stores information about books such as title, author, category, published year, and availability status.
- **📑 Borrowings Table:** Keeps track of book borrowings, including user ID, book ID, borrow date, due date, returned date, and status.

### 🔑 Authorization Header

All requests must include the following header:

```http
Authorization: Bearer <access_token>
```

## 🔐 Role-based Access Control

- **ADMIN**: Can access all endpoints (Books & Borrowings)
- **USER**: Can only access Books endpoints

## 📚 Book API

### 1️⃣ Get Books

**Endpoint:** `GET /v1/books`

**Access:** `ADMIN`, `USER`

**Headers:**

```http
Authorization: Bearer <access_token>
```

**Description:** Retrieve a list of available books.

**Response:**

```json
[
  {
    "id": "string",
    "name": "string",
    "author": "string",
    "category": "string",
    "publishedYear": 2024,
    "isAvailable": true
  }
]
```

---

### 2️⃣ Get Book by ID

**Endpoint:** `GET /v1/books/{id}`

**Access:** `ADMIN`, `USER`

**Headers:**

```http
Authorization: Bearer <access_token>
```

**Description:** Retrieve details of a specific book by its ID.

**Response:**

```json
{
  "id": "string",
  "name": "string",
  "author": "string",
  "category": "string",
  "publishedYear": 2024,
  "isAvailable": true
}
```

---

## 🔄 Borrowing API

### 1️⃣ Get Borrowings

**Endpoint:** `GET /v1/borrowings`

**Access:** `ADMIN`

**Headers:**

```http
Authorization: Bearer <access_token>
```

**Query Parameters:**

- `status` (optional, default: `borrowed`)
    - `overdue`: Retrieves books that are overdue.
    - `borrowed`: Retrieves all books currently borrowed.
    - `returned`: Retrieves all books that have been returned.

**Description:** Retrieve a list of all borrowings based on their status.

**Response:**

```json
[
  {
    "id": "string",
    "bookName": "string",
    "userName": "string",
    "status": "BORROWED",
    "borrowingDate": "2024-03-08T10:00:00",
    "dueDate": "2024-03-15T10:00:00",
    "returnedDate": null
  }
]
```

---

### 2️⃣ Add Borrowing

**Endpoint:** `POST /v1/borrowings`

**Access:** `ADMIN`

**Headers:**

```http
Authorization: Bearer <access_token>
```

**Description:** Create a new borrowing request.

**Request:**

```json
{
  "bookId": "string",
  "userId": "string"
}
```

**Validation Rules:**

- **`bookId`** (required)
- **`userId`** (required)
- **Each user can only borrow one book at a time.**

**Response:**

```json
{
  "id": "string",
  "bookName": "string",
  "userName": "string",
  "status": "BORROWED",
  "borrowingDate": "2024-03-08T10:00:00",
  "dueDate": "2024-03-15T10:00:00",
  "returnedDate": null
}
```

---

### 3️⃣ Update Borrowing

**Endpoint:** `PUT /v1/borrowings/{id}`

**Access:** `ADMIN`

**Headers:**

```http
Authorization: Bearer <access_token>
```

**Description:** Update the status of a borrowing.

**Request:**

```json
{
  "borrowingId": "string",
  "status": "RETURNED"
}
```

**Validation Rules:**

- **`borrowingId`** (required)
- **`status`** (required, must be `RETURNED`)

**Response:**

```json
{
  "id": "string",
  "bookName": "string",
  "userName": "string",
  "status": "RETURNED",
  "borrowingDate": "2024-03-08T10:00:00",
  "dueDate": "2024-03-15T10:00:00",
  "returnedDate": "2024-03-16T12:00:00"
}
```

---

