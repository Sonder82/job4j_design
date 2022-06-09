create table departments(
	id serial primary key,
	name varchar(255)
);

create table employees(
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);

insert into departments(name)
values ('Commercial'), ('Financial'), ('Service'), ('Law');

insert into employees(name, department_id)
values ('Aleksey', 1), ('Sergey', 1), ('Valentina', 2), ('Anna', 2), ('Vitaliy',3);

select d.name as Название_департамента, e.name as Имя_работника
from departments d
left join employees e
on e.department_id = d.id
where e.name is null;

select d.name as Название_департамента, e.name as Имя_работника
from departments d
left join employees e
on e.department_id = d.id;

select d.name as Название_департамента, e.name as Имя_работника
from employees e
right join departments d
on e.department_id = d.id;