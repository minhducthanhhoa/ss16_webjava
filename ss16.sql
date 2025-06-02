CREATE DATABASE ss16_db;
USE ss16_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role ENUM('ADMIN', 'USER') DEFAULT 'USER',
    status BOOLEAN DEFAULT TRUE
);

INSERT INTO users (username, password, email, role)
VALUES ('admin', 'admin123', 'admin@gmail.com', 'ADMIN');

CREATE TABLE trip (
    id INT AUTO_INCREMENT PRIMARY KEY,
    departure VARCHAR(100),
    destination VARCHAR(100),
    time VARCHAR(50),
    price DOUBLE
);

INSERT INTO trip (departure, destination, time, price) VALUES
                ('Hà Nội', 'Đà Nẵng', '08:00', 500000),
                ('Hà Nội', 'Hải Phòng', '09:00', 200000),
                ('Hồ Chí Minh', 'Đà Lạt', '07:30', 350000),
                ('Hà Nội', 'Hạ Long', '06:45', 250000),
                ('Đà Nẵng', 'Huế', '10:00', 150000),
                ('Hồ Chí Minh', 'Nha Trang', '11:30', 450000);

CREATE TABLE bus (
    id INT AUTO_INCREMENT PRIMARY KEY,
    license_plate VARCHAR(20) NOT NULL UNIQUE,
    bus_type ENUM('NORMAL', 'VIP', 'LUXURY') NOT NULL,
    row_seat INT NOT NULL CHECK (row_seat > 0),
    col_seat INT NOT NULL CHECK (col_seat > 0),
    total_seat INT NOT NULL,
    image VARCHAR(255)
);

CREATE TABLE seat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name_seat VARCHAR(10) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    status BOOLEAN DEFAULT TRUE,
    bus_id INT NOT NULL,
    FOREIGN KEY (bus_id) REFERENCES bus(id) ON DELETE CASCADE
);

INSERT INTO bus (license_plate, bus_type, row_seat, col_seat, total_seat, image)
VALUES
    ('29A-12345', 'NORMAL', 5, 4, 20, 'https://example.com/images/bus1.jpg'),
    ('30B-67890', 'VIP', 4, 5, 20, 'https://example.com/images/bus2.jpg');

INSERT INTO seat (name_seat, price, status, bus_id)
VALUES
    ('A1', 100000, TRUE, 1),
    ('A2', 100000, TRUE, 1),
    ('B1', 100000, TRUE, 1),
    ('B2', 100000, TRUE, 1);


