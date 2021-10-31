CREATE TABLE type(
id serial primary key,
name varchar(255)
);

CREATE TABLE product(
id serial primary key,
name varchar(255),
type_id int REFERENCES type (id),
expired_date date,
price float
);

INSERT INTO type(name) values('СЫР'), ('МОЛОКО'), ('ХЛЕБ'), ('КОЛБАСНЫЕ ИЗДЕЛИЯ');
INSERT INTO product(name, type_id, expired_date, price) values('СЫР МААСДАМ', 1, '15.01.2022', 388.90),
('СЫР ПОШЕХОНСКИЙ', 1, '10.03.2022', 288.70), ('СЫР КОЛБАСНЫЙ', 1, '28.10.2022', 99.40),
('СЫРОК ПЛАВЛЕННЫЙ ДРУЖБА', 1, '20.10.2021', 44.90 ), ('МОЛОКО 2,8%', 2, '25.10.2021', 62.40),
('МОЛОКО 3,5%', 2, '05.11.2021', 77.50), ('МОЛОКО ТОПЛЕНОЕ', 2, '11.03.2022', 95.20),
('МОРОЖЕНОЕ ПЛОМБИР 250гр', 2, '10.01.2022', 75.90), ('МОРОЖЕНОЕ ПЛОМБИР 500гр', 2, '12.01.2022', 147.00),
('МОРОЖЕНОЕ КРЕМ-БРЮЛЕ 250гр', 2, '11.02.2022', 86.40),
('БАТОН НАРЕЗНОЙ', 3, '29.10.2021', 43.90), ('ХЛЕБ БОРОДИНСКИЙ', 3, '14.11.2021', 92.80),
('ХЛЕБ РЖАНОЙ', 3, '08.11.2021', 39.50), ('КОЛБАСА ДОКТОРСКАЯ', 4, '17.11.2021', 230.90),
('КОЛБАСА ВАРЕНАЯ', 4, '21.11.2021', 210.00), ('СЕРВЕЛАТ', 4, '24.01.2022', 482.70),
('БУЖЕНИНА', 4, '14.11.2021', 444.88), ('ОКОРОК СВИНОЙ', 4, '19.11.2021', 399.00);

SELECT * FROM product
WHERE type_id=1;

SELECT p.name, p.expired_date, p.price
FROM product p
JOIN type t ON p.type_id=t.id
WHERE t.name='СЫР';

SELECT p.name, p.expired_date, p.price
FROM product p
JOIN type t ON p.type_id=t.id
WHERE p.name LIKE '%МОРОЖЕНОЕ%';

SELECT p.name, p.expired_date, p.price
FROM product p
JOIN type t ON p.type_id=t.id
WHERE p.expired_date<current_date;

SELECT max(p.price)
FROM product p
JOIN type t ON p.type_id=t.id;

SELECT name, price, expired_date
FROM product
WHERE price = (SELECT max(price) FROM product);

SELECT p.name, p.expired_date, p.price
FROM product p
JOIN type t ON p.type_id=t.id
WHERE t.name='СЫР' OR t.name='МОЛОКО';

SELECT t.name, count(p.name)
FROM product p
JOIN type t ON p.type_id=t.id group by t.name;

SELECT p.name, t.name
FROM product p
JOIN type t ON p.type_id=t.id;
