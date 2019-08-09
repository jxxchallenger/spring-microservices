CREATE TABLE IF NOT EXISTS organizations (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    contact_name VARCHAR(50) NOT NULL,
    contact_email VARCHAR(50) NOT NULL,
    contact_phone VARCHAR(20) NOT NULL
);