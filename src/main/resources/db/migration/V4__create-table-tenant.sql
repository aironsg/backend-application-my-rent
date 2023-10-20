CREATE TABLE  IF NOT EXISTS tenant
(
    id SERIAL PRIMARY KEY NOT NULL,
    name TEXT NOT NULL,
    phone_number TEXT NOT NULL,
    email TEXT,
    cpf TEXT NOT NULL,
    rent_date DATE NOT NULL,
    quantity_dependents INT NOT NULL,
    address_id INT,
    FOREIGN KEY (address_id) REFERENCES address(id)
    );