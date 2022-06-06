insert into role(name) values ('master');
insert into role(name) values ('slave');

insert into users(name, role_id) values ('Aleksey', 17);
insert into users(name, role_id) values ('Ivan', 18);

insert into rules(name) values ('only check');
insert into rules(name) values ('only read');

insert into role_rules(role_id, rule_id) values (17, 13);
insert into role_rules(role_id, rule_id) values (18, 14);


insert into category(name) values ('high');
insert into category(name) values ('midle');

insert into state(name) values ('wating');
insert into state(name) values ('done');


insert into item(name, user_id, category_id, state_id) values ('aa123', 16, 9, 11);
insert into item(name, user_id, category_id, state_id) values ('bb345', 17, 10, 12);

insert into comments(name, item_id) values ('comment1', 9);
insert into comments(name, item_id) values ('comment2', 10);

insert into attachs(name, item_id) values ('file1', 9);
insert into attachs(name, item_id) values ('file2', 10);
