--create table cars (
--id serial primary key,
--model varchar (70),
--year date,
--cost text
--);
insert into cars(model, year, cost) values('audi', '01-01-2010', '350000');
select * from cars;
update cars set cost = '400000';
select * from cars;
delete from cars;
select * from cars;