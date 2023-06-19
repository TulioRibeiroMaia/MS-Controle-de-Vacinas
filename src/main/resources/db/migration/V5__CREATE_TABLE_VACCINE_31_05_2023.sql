CREATE TABLE Vaccine (
    id BIGSERIAL NOT NULL,
    manufacturer VARCHAR(255),
    manufacture_date TIMESTAMP,
    expiration_date TIMESTAMP,
    lot_number VARCHAR(255),
    vaccine_name VARCHAR(255),
    PRIMARY KEY(id)
);