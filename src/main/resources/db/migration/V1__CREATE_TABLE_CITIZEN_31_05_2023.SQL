CREATE TABLE Citizen (
    id BIGSERIAL NOT NULL,
    cpf VARCHAR(255) UNIQUE,
    full_name VARCHAR(255) NOT NULL,
    birthdate TIMESTAMP,
    user_role VARCHAR(255) NOT NULL,
    cns VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);