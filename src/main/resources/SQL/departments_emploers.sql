CREATE TABLE departments(
id serial primary key,
name varchar(70)
);

CREATE TABLE emploers(
id serial primary key,
name varchar(255),
departments_id int REFERENCES departments(id)
);

INSERT INTO departments(name)
VALUES('Human Reserses'), ('Information Technologies' ),
('Economics'), ('Development');

INSERT INTO emploers(name, departments_id)
VALUES('Ivanov Ivan', 1),
('Vasil''ev Vasiliy', 4), ('Kostin Kostantin', 2), ('Sergeev Sergey', null),
('Danilov Danil', 4), ('Petrov Petr', 4), ('Maksimov Maksim', 4),
('Mashkova Maria', 4), ('Isaeva Svetlana', 4), ('Trunova Elizaveta', 4),
('Haritonova Aleksandra', 2), ('Malahov Nikita', 4);

SELECT e.name, d.name
FROM departments d
LEFT JOIN emploers e
ON d.id=e.departments_id
WHERE e.departments_id IS NULL;

SELECT e.name, d.name
FROM emploers e
RIGHT JOIN departments d
ON d.id=e.departments_id
WHERE e.departments_id IS NULL;


CREATE TABLE teens(
id serial primary key,
name varchar(255),
gender char(1)
);

INSERT INTO teens(name, gender)
VALUES('Lukianova Elena', 'F'), ('Soshkin Ruslan', 'M'),
('Stroikov Mihail', 'M'), ('Sotnikova Elena', 'F'),
('Stroganov Konstantin', 'M'), ('Esipova Marina', 'F');

SELECT t1.name, t2.name
FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender='M' AND t2.gender='F';

