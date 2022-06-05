insert into role(name) values ('master');
insert into role(name) values ('slave');

insert into rules(name) values ('only check');
insert into rules(name) values ('only read');

insert into role_rules(role_id, rule_id) values (1, 2);
insert into role_rules(role_id, rule_id) values (2, 1);

insert into item(name, user_id, category_id, state_id) values ('aa123', 1, 1, 1);
insert into item(name, user_id, category_id, state_id) values ('bb345', 2, 2, 2);

insert into comments(name, item_id) values ('comment1', 1);
insert into comments(name, item_id) values ('comment2', 2);

insert into attachs(name, item_id) values ('file1', 1);
insert into attachs(name, item_id) values ('file2', 2);

insert into category(name) values ('high');
insert into state(name) values ('wating');

