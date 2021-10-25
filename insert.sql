USE users_statement;

INSERT INTO roles(title) VALUES('Admin');
INSERT INTO roles(title) VALUES('User');

INSERT INTO users(name, roles_id) VALUES('Ivan', 1);
INSERT INTO users(name, roles_id) VALUES('Sergey', 2);
INSERT INTO users(name, roles_id) VALUES('Maria', 2);

INSERT INTO rules(rule) VALUES('Create');
INSERT INTO rules(rule) VALUES('Update');
INSERT INTO rules(rule) VALUES('Delete');
INSERT INTO rules(rule) VALUES('Read');

--INSERT INTO comments(comment) VALUES('Good');
--INSERT INTO comments(comment) VALUES('Bad');
--INSERT INTO comments(comment) VALUES('So so');

INSERT INTO attachs(smth) VALUES('One additional');
INSERT INTO attachs(smth) VALUES('Another one additional');

INSERT INTO category(name) VALUES('Books');
INSERT INTO category(name) VALUES('Drinks');
INSERT INTO category(name) VALUES('Soft');
INSERT INTO category(name) VALUES('Clothes');

INSERT INTO state(state) VALUES('NY');
INSERT INTO state(state) VALUES('NJ');
INSERT INTO state(state) VALUES('NO');

INSERT INTO item(description, users_id, comments_id, attachs_id, category_id, state_id) VALUES('Smth interesting', 1, 3, 2, 1, 3);
INSERT INTO item(description, users_id, comments_id, attachs_id, category_id, state_id) VALUES('Exelent thing', 3, 1, 2, 4, 1);
INSERT INTO item(description, users_id, comments_id, attachs_id, category_id, state_id) VALUES('Usefull thing', 2, 3, 1, 1, 2);
INSERT INTO item(description, users_id, comments_id, attachs_id, category_id, state_id) VALUES('Big thing', 2, 2, 1, 4, 2);




