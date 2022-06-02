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
insert into orders(number, customer_id) VALUES ('878', 1);

select * from orders;
select * from customers where id in(select id from orders);


