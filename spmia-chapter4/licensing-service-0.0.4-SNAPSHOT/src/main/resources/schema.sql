CREATE TABLE IF NOT EXISTS licenses (
    license_id VARCHAR(50) PRIMARY KEY,
    organization_id VARCHAR(50) NOT NULL,
    product_name VARCHAR(50) NOT NULL,
    license_type VARCHAR(10) NOT NULL
);