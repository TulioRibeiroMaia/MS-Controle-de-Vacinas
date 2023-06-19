CREATE TABLE HealthCenter (
    id BIGSERIAL NOT NULL,
    cnes VARCHAR(255) UNIQUE,
    name VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    PRIMARY KEY(id)
);