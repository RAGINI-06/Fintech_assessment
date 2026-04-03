# 🚀 Finance Data Processing Backend

## 📌 Overview

This project is a backend system for managing financial records with role-based access control.  
It allows different users to perform actions based on their roles and provides dashboard-level insights.

The system is designed with clean architecture, proper validation, and scalable backend practices.

---

## 🧠 Features

- 👥 User Management (Admin, Analyst, Viewer)
- 🔐 Role-Based Access Control (RBAC)
- 💰 Financial Records CRUD (Create, Read, Update, Delete)
- 🔍 Filtering by type and category
- 📊 Dashboard Summary (Global & User-wise)
- 📄 Pagination support
- 🔎 Search functionality
- ✅ Input validation and error handling

---

## 👥 Roles & Permissions

| Role    | Permissions |
|---------|------------|
| Viewer  | Can only view records |
| Analyst | Can view records + dashboard |
| Admin   | Full access (create, update, delete, manage users) |

---

## 🏗️ Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Lombok

---

## 🗄️ Database Schema

### User Table
- id
- name
- email
- password
- role (ADMIN / ANALYST / VIEWER)
- active

### Financial Record Table
- id
- amount
- type (INCOME / EXPENSE)
- category
- date
- description
- user_id (Foreign Key)

---

## 🔐 Access Control

Role-based access control is implemented at the service layer using utility methods.

- Admin → Full access
- Analyst → Read + dashboard
- Viewer → Read only

---
## ⚙️ Assumptions

- Authentication is simplified using `userId` in requests  
- Focus is on backend logic and system architecture  
- Can be extended with JWT or session-based authentication  

---

## 🚀 Future Improvements

- JWT Authentication  
- API Documentation using Swagger  
- Pagination enhancements  
- Advanced filtering and search  
- Unit and Integration Testing  

---

## 📌 How to Run

1. Clone the repository  
2. Configure the database in `application.properties`  
3. Run the Spring Boot application  
4. Test APIs using Postman or any API client  

---

## 💼 Project Highlights

- Clean architecture with a well-structured service layer  
- Proper validation and global error handling  
- Role-based authorization implemented  
- Scalable and maintainable design  

## 📊 API ENDPOINTS

---

# 🔹 USER APIs

### ➤ Create User
POST /users
Body:
```json
{
  "name": "Ragini",
  "email": "ragini@gmail.com",
  "password": "1234",
  "role": "ADMIN"
}
🔹 FINANCIAL RECORD APIs
➤ Create Record (Admin only)
POST /records?userId=1

Body:

{
  "amount": 5000,
  "type": "INCOME",
  "category": "Salary",
  "date": "2025-04-05",
  "description": "Monthly salary"
}
➤ Get All Records
GET /records?userId=1
➤ Get Record by ID
GET /records/{id}?userId=1
➤ Update Record (Admin only)
PUT /records/{id}?userId=1

Body:

{
  "amount": 3000,
  "type": "EXPENSE",
  "category": "Food",
  "date": "2025-04-05",
  "description": "Dinner"
}
➤ Delete Record (Admin only)
DELETE /records/{id}?userId=1
🔹 FILTER APIs
➤ Filter by Type
GET /records/filter/type?type=INCOME&userId=1
➤ Filter by Category
GET /records/filter/category?category=Food&userId=1
🔹 SEARCH API
➤ Search by Keyword
GET /records/search?keyword=food&userId=1
🔹 PAGINATION API
➤ Paginated Records
GET /records/paginated?page=0&size=5&userId=1
🔹 DASHBOARD APIs
➤ Global Summary
GET /dashboard/summary?userId=1

Response:

{
  "totalIncome": 10000,
  "totalExpense": 4000,
  "netBalance": 6000
}
➤ User-wise Summary
GET /dashboard/user/{userId}


