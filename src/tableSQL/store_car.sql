create table body(
	id serial primary key,
	color varchar(255)
);

create table engine(
	id serial primary key,
	engine_code varchar(255)
);

create table transmission(
	id serial primary key,
	transmission_code varchar(255)
);

create table car(
	id serial primary key,
	model varchar(255),
	body_id int references body(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into body(color)
values ('White'), ('Black'), ('Green'), ('Red'), ('Blue');

insert into engine(engine_code)
values ('ALT'), ('CDNC'), ('CASA'), ('CJEB'), ('BUG'), ('CVMD');

insert into transmission(transmission_code)
values ('DQ200'), ('DL500'), ('6HP19'), ('0AW'), ('DQ380'), ('8HP50');

insert into car(model, body_id, engine_id, transmission_id)
values ('Audi A4', 2, 1, 4), ('Audi A6', 5, 2, 3), ('Audi Q3', 2, 2, 5),
('Audi Q5', 3, 2, 2), ('Audi Q7', 4, 5, 5);

select c.model, b.color, e.engine_code, t.transmission_code
from car c
left join body b
on c.body_id = b.id
left join engine e
on c.engine_id = e.id
left join transmission t
on c.transmission_id = t.id;

-- Цвет кузова (body), которого нет ни в одном автомобиле(car)
select b.color
from body b
left join car c
on c.body_id = b.id
where c.model is null;

-- Модель двигателя (engine_code), которого нет ни в одном автомобиле(car)
select e.engine_code
from engine e
left join car c
on c.engine_id = e.id
where c.engine_id is null;

-- Модель трансмиссии (transmission_code), которого нет ни в одном автомобиле(car)
select t.transmission_code
from transmission t
left join car c
on c.transmission_id = t.id
where c.transmission_id is null;