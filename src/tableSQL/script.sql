create table person(
	id serial primary key,
	name varchar(255),
	region text,
	age int,
	permission bool
);
insert into person(name, region, age, permission) values('Aleksey', 'Ekaterinburg', '40', 'true');
update person set region = 'Moscow';
delete from person;
select * from person;