CREATE TABLE TOKEN (
    id INTEGER NOT NULL,
    token  VARCHAR(255),
    tokenType VARCHAR(255),
    revoked BOOLEAN,
    expired BOOLEAN,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES USER(id),
    PRIMARY KEY(id)
);