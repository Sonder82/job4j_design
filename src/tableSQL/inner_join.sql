create table customers(
	id serial primary key,
	name varchar(255)
);
create table orders(
	id serial primary key,
	number int,
	customer_id int references customers(id)
);

insert into customers(name) values ('Aleksey');
insert into customers(name) values ('Ivan');
insert into customers(name) values ('Aleksandr');

insert into orders(number, customer_id) values (121, 1);
insert into orders(number, customer_id) values (122, 1);
insert into orders(number, customer_id) values (123, 2);
insert into orders(number, customer_id) values (124, 3);

select * from orders
join customers c
on orders.customer_id = c.id;

select o.number, c.name
from orders as o
join customers as c
on o.customer_id = c.id;

select o.number as номер, c.name as имя
from orders as o
join customers as c
on o.customer_id = c.id; 