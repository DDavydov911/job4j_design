--create table fauna (
--    id serial primary key,
--    name text,
--    avg_age int,
--    discovery_date date
--);

insert into fauna (name, avg_age, discovery_date) values('fish', 20000, '01.01.1800');
insert into fauna (name, avg_age, discovery_date) values('horse', 30000, '10.01.1920');
insert into fauna (name, avg_age, discovery_date) values('snake', 10000, '15.01.2020');
insert into fauna (name, avg_age, discovery_date) values('bird', 20000, '20.01.1980');
insert into fauna (name, avg_age, discovery_date) values('mouse', 10000, '25.01.2021');
insert into fauna (name, avg_age) values('engry bird', 10000);
insert into fauna (name, avg_age) values('fly', 10000);

select * from fauna where name like '%fish%';
select * from fauna where 10000 < avg_age and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';