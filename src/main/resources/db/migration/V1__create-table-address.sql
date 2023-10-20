CREATE TABLE IF NOT EXISTS address
(
    id SERIAL PRIMARY KEY NOT NULL,
    zip_code TEXT,
    state TEXT,
    city TEXT,
    neighborhood TEXT,
    street TEXT,
    number TEXT
);
