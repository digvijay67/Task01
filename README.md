   Student (table name)
  -----------------------
   student_id (PK)
   student_name
   email
   phone
       |
       | 1:N
       |
  Book_Issue(table name)
   ------------------------
   issue_id (PK)
   student_id (FK)
   book_id (FK)
   issue_datetime
       |
       | N:1
       |
     Book  ( table name)
  ----------------------
   book_id (PK)
   book_name
   author
   price

Primary Keys: student_id, book_id, issue_id
Foreign Keys: student_id, book_id
Normalized Tables (no redundancy)


# Library Management System (Java + MySQL)

## 📌 Project Overview
This is a simple **Library Management System** implemented using **Java** and **MySQL**.  
The project allows you to:  

1. Add new students  
2. Add new books  
3. Issue books to students (only existing students and books)  
4. Display live issued book details with **student name, book name, and issue date/time**  

The system ensures database integrity with **foreign keys** and **auto-increment IDs**.

---

## 🛠️ Tools and Technologies

- **Java JDK 11+**
- **MySQL 8+**
- **MySQL Workbench** (or any MySQL client)
- JDBC Connector (MySQL Connector/J)
- IDE (VS Code insider)  
