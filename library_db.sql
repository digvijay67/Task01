CREATE DATABASE library_db;
USE library_db;

CREATE TABLE Student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    email VARCHAR(50) UNIQUE,
    phone VARCHAR(10)
);

CREATE TABLE Book (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    book_name VARCHAR(100) NOT NULL,
    author VARCHAR(50) NOT NULL,
    price DECIMAL(10,2)
);

CREATE TABLE Book_Issue (
    issue_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    book_id INT,
    issue_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES Student(student_id),
    FOREIGN KEY (book_id) REFERENCES Book(book_id)
);

select * from Student;
select * from Book_Issue;


DROP TABLE IF EXISTS Book_Issue;
DROP TABLE IF EXISTS Book;
DROP TABLE IF EXISTS Student;

