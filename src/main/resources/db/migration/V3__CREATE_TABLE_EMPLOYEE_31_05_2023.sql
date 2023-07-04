CREATE TABLE Employee (
    id BIGSERIAL NOT NULL,
    cpf  VARCHAR(255),
    full_name VARCHAR(255),
    birthdate TIMESTAMP,
    user_role VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    health_centerid INT,
--    FOREIGN KEY (health_centerid) REFERENCES HealthCenter(id),
    PRIMARY KEY(id)
);