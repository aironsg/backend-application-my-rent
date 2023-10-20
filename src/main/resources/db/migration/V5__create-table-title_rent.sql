CREATE TABLE IF NOT EXISTS title_rent
(
    id SERIAL PRIMARY KEY NOT NULL,
    tenant_id INT,
    owner_id INT,
    property_id INT,
    value_rent DECIMAL(10, 2) NOT NULL,
    contract_length_in_months INT NOT NULL,
    active BOOLEAN NOT NULL,
    contract_start_date DATE NOT NULL,
    contract_end_date DATE NOT NULL,
    FOREIGN KEY (tenant_id) REFERENCES tenant(id),
    FOREIGN KEY (owner_id) REFERENCES owner(id),
    FOREIGN KEY (property_id) REFERENCES property(id)
    );