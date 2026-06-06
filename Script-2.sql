CREATE DATABASE tecnostore_db;
 
 
 
CREATE TABLE celulares(
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50),
    modelo VARCHAR(50),
    sistema_operativo VARCHAR(30),
    gama VARCHAR(20),
    precio DECIMAL(10,2),
    stock INT
);
 
CREATE TABLE clientes(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    identificacion VARCHAR(30) UNIQUE,
    correo VARCHAR(100),
    telefono VARCHAR(20)
);
 
CREATE TABLE ventas(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    fecha DATETIME,
    total DECIMAL(10,2),
    FOREIGN KEY(id_cliente)
    REFERENCES clientes(id)
);
CREATE TABLE detalle_ventas(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT,
    id_celular INT,
    cantidad INT,
    subtotal DECIMAL(10,2),
    FOREIGN KEY(id_venta)
    REFERENCES ventas(id),
    FOREIGN KEY(id_celular)
    REFERENCES celulares(id)
);
