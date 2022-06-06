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

insert into devices(name, price) values ('Samsung', 7500),('Sony', 4800),('LG', 5700);
insert into people(name) values ('Aleksey'), ('Sergey'), ('Vitaliy');
insert into devices_people(device_id, people_id) values (7, 7), (7, 8), (8, 8), (9, 7), (9, 9);

select avg(price) from devices;

select p.name, avg(d.price)
from people as p
join devices_people as dp
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.name

select p.name, avg(d.price)
from people as p
join devices_people as dp
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;