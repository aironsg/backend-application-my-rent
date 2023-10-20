CREATE TABLE IF NOT EXISTS property
(
    id SERIAL PRIMARY KEY NOT NULL,
    description TEXT NOT NULL,
    quantity_rooms INT NOT NULL,
    rent_value DECIMAL(10, 2) NOT NULL,
    address_id INT,
    occupation_status TEXT,
    UNIQUE (id),
    FOREIGN KEY (address_id) REFERENCES address(id)
    );