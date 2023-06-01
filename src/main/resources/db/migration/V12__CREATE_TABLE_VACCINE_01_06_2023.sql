CREATE TABLE Vaccine (
    id INT NOT NULL,
    manufacturer VARCHAR NOT NULL,
    manufactureDate DATE NOT NULL,
    expirationDate DATE NOT NULL,
    lotNumber VARCHAR NOT NULL,
    PRIMARY KEY(id)
);