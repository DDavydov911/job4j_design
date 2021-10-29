create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values('iPhone 11', 70000), ('iPhone 12', 90000),
('iPhone 13', 120000), ('OnePlus', 60000), ('Poco', 44000), ('Samsung', 80000),
('Xiaomi', 37000), ('TV Sony', 100000), ('Flesh Card 64Gb', 1100), ('PC Dell', 80000),
('NoteBook Lenovo', 65000), ('Apple watch', 35000);

insert into people(name) values('Ivan'), ('Fedor'), ('Maria'), ('Semen'), ('Olga'),
('Mihail'), ('Anna');
insert into devices_people(device_id, people_id) values(1, 1), (2, 2), (3, 3), (4, 4),
(5, 5), (6, 6), (7, 7), (12, 1), (12, 2), (12, 3), (8, 4), (9, 5), (10, 6), (11, 7);

select avg(d.price) from devices d group by d.price;

select p.name, avg(d.price) from devices_people dp join people p on
p.id = dp.people_id join devices d on d.id=dp.device_id group by p.name;

select p.name, avg(d.price) from devices_people dp join people p on
p.id = dp.people_id join devices d on d.id=dp.device_id group by p.name
having avg(d.price) >= 80000;