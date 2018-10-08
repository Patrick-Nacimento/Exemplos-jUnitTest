/**
* Author: Patrick
* Created: 08/10/2018
*/
DROP DATABASE IF EXISTS Kleitom;
CREATE DATABASE kleitom;
USE kleitom;

CREATE TABLE macarroes(
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(100),
    marca VARCHAR(100),
    peso DOUBLE,
    aldente BOOLEAN
);

