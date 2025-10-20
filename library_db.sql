Create database library_db;
USE library_db;

Create table book_issue (
issue_id INT auto_increment Primary key,
student_id varchar(10),
student_name varchar(100),
book_name varchar(150),
issue_datatime timestamp default current_timestamp
);