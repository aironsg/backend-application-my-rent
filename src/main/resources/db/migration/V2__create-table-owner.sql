CREATE TABLE  IF NOT EXISTS owner
(
    id SERIAL PRIMARY KEY NOT NULL,
    name TEXT NOT NULL,
    phone_number TEXT NOT NULL,
    email TEXT,
    cpf TEXT NOT NULL,
    address_id INT,
    type TEXT,
    type_plan TEXT,
    UNIQUE (cpf),
    FOREIGN KEY (address_id) REFERENCES address(id)
    );