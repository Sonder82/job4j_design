CREATE TABLE customers(
    id SERIAL PRIMARY KEY,
    first_name text,
    last_name text,
    age int,
    country text
);

INSERT INTO customers(first_name, last_name, age, country)
VALUES ('Ivan', 'Ivanov', 30, 'Moscow'),
('Petr', 'Petrov', 33, 'Omsk'),
('Aleksey', 'Novoselov', 35, 'Ekaterinburg');

SELECT * FROM customers
WHERE age = (SELECT min(age) FROM customers);

CREATE TABLE orders(
id SERIAL PRIMARY KEY,
amount int,
customer_id int REFERENCES customers(id)
);

INSERT INTO orders(amount, customer_id)
VALUES (30, 1),
(33, 2),
(35, 2);

SELECT * FROM customers
WHERE id NOT IN(SELECT customer_id FROM orders);

