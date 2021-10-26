USE users_statement;

CREATE TABLE roles(
id serial primary key,
title varchar(255)
);

CREATE TABLE users(
id serial primary key,
name varchar(255),
roles_id int REFERENCES roles(id)
);

CREATE TABLE rules(
id serial primary key,
rule varchar(20)
);

CREATE TABLE roles_rules(
id serial primary key,
roles_id int REFERENCES rules(id),
rules_id int REFERENCES roles(id)
);

CREATE TABLE comments(
id serial primary key,
comment text
);

CREATE TABLE attachs(
id serial primary key,
smth text
);

CREATE TABLE category(
id serial primary key,
name varchar(255)
);

CREATE TABLE state(
id serial primary key,
state varchar(70)
);

CREATE TABLE item(
id serial primary key,
description text,
users_id int REFERENCES users(id),
comments_id int REFERENCES comments(id),
attachs_id int REFERENCES attachs(id),
category_id int REFERENCES category(id),
state_id int REFERENCES state(id)
);

