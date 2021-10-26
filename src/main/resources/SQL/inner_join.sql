CREATE TABLE people(
id serial primary key,
full_name varchar(255)
);

CREATE TABLE students(
id serial primary key,
speciality varchar(255),
group_number int,
level int,
people_id int REFERENCES people(id) unique
);

INSERT INTO people(full_name) VALUES('Ivanov Ivan');
INSERT INTO people(full_name) VALUES('Petrov Petr');
INSERT INTO people(full_name) VALUES('Kudinova Maria');
INSERT INTO people(full_name) VALUES('Afanasieva Elena');
INSERT INTO people(full_name) VALUES('Safonova Elena');
INSERT INTO people(full_name) VALUES('Malysheva Lubov');
INSERT INTO people(full_name) VALUES('Tretiak Aleksandr');
INSERT INTO people(full_name) VALUES('Kirillov Sergey');

INSERT INTO students(speciality, group_number, level, people_id) VALUES('IT', 222, 2, 1);
INSERT INTO students(speciality, group_number, level, people_id) VALUES('Economics', 111, 1, 2);
INSERT INTO students(speciality, group_number, level, people_id) VALUES('Mathematics', 101, 1, 3);
INSERT INTO students(speciality, group_number, level, people_id) VALUES('Mathematics', 102, 1, 4);
INSERT INTO students(speciality, group_number, level, people_id) VALUES('IT', 122, 1, 5);
INSERT INTO students(speciality, group_number, level, people_id) VALUES('Mathematics', 101, 1, 6);
INSERT INTO students(speciality, group_number, level, people_id) VALUES('Economics', 211, 2, 7);
INSERT INTO students(speciality, group_number, level, people_id) VALUES('IT', 122, 1, 8);

select p.id, p.full_name, s.speciality, s.level, s.group_number
from people as p inner join students as s on p.id=s.people_id;

select p.full_name, s.speciality, s.level, s.group_number
from people as p inner join students as s on p.id=s.people_id where level = 2;

select p.full_name, s.group_number, s.level
from people p join students s on p.id=s.people_id where speciality='Mathematics';