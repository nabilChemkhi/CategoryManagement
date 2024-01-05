CREATE DATABASE spring_boot_project;

USE spring_boot_project;

CREATE TABLE categories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE subcategory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(255),
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

