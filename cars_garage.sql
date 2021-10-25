--create table cars_owners(
--id serial primary key,
--name varchar(255)
--);
--
--create table cars_passports(
--id serial primary key,
--reg_number varchar(9) unique,
--description text,
--pas_number int unique
--);
--
--create table cars_garage(
--id serial primary key,
--owner_id int references cars_owners(id),
--passport_id int references cars_passports(id) unique,
--model varchar(255),
--reg_number varchar(9) references cars_passports(reg_number) unique
--);
--
--create table cars_drivers(
--id serial primary key,
--name varchar(255),
--licence int,
--cars_id int references cars_garage(id)
--);

insert into cars_owners(name) values('Ivanov Ivan Ivanovich');
insert into cars_passports(reg_number, pas_number) values('A100AA177', 1234567);
insert into cars_garage(owner_id, passport_id, model, reg_number) values(1, 1, 'Audi100', 'A100AA177');
insert into cars_drivers(name, licence, cars_id) values ('Petrov Sergey Alekseevich', 77111111, 1);
select * from cars_owners;
select * from cars_passports;
select * from cars_garage;
select * from cars_drivers;