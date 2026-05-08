CREATE DATABASE hospital_system;
USE hospital_system;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fullname VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100),
    role VARCHAR(20)
);

CREATE TABLE doctors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    doctor_name VARCHAR(100),
    specialization VARCHAR(100)
);

CREATE TABLE appointments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    patient_name VARCHAR(100),
    doctor_name VARCHAR(100),
    appointment_date DATE,
    status VARCHAR(20)
);

INSERT INTO users(fullname,email,password,role)
VALUES('Admin','admin@gmail.com','admin123','admin');
