create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_data  timestamp,
	price float
);

insert into type(name) values('Сыр'),('Молоко');
insert into product(name, type_id, expired_data, price) values ('Сыр плавленный', 1, '2022-08-01', 75),
('Сыр пармезан', 1, '2023-02-01', 450),('Сырные шарики', 1, '2022-07-21', 180);
insert into product(name, type_id, expired_data, price) values ('Молоко топленое', 2, '2022-07-11', 65),
('Сливочное мороженое', 2, '2022-12-21', 150),('Сливки', 2, '2022-09-01', 110),('Молочный коктейль', 2, '2022-06-01', 100);

select t.name, p.name
from product as p 
join type t
on p.type_id = t.id
where t.name = 'Сыр';

select * from product where name like '%мороженое%';

select * from product where expired_data < current_date;

select name, price
from product  
where price = (select max(price) from product);

select t.name, count(p.type_id)
from product as p
join type t
on p.type_id = t.id
group by t.name;

select t.name, p.name
from product as p 
join type t
on p.type_id = t.id
where t.name = 'Сыр' or t.name = 'Молоко';

select t.name, count(p.type_id)
from product as p
join type t
on p.type_id = t.id
group by t.name
having count(p.type_id) < 10;

select p.name, t.name
from product as p
join type t
on p.type_id = t.id;

