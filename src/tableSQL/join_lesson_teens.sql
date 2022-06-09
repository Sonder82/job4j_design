create table teens(
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);
insert into teens(name, gender)
values ('Pavel', 'male'), ('Aleksandr', 'male'), ('Irina', 'female'), ('Marina', 'female')

select n1.name, n2.name, concat(n1.gender, n2.gender) as Marriage
from teens n1 cross join teens n2
where n1.gender != n2.gender;