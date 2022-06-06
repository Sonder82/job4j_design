create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('tiger', 30, date '1797-06-22');
insert into fauna(name, avg_age, discovery_date)
values ('wolf', 25, date '1421-03-12');
insert into fauna(name, avg_age, discovery_date)
values ('bear', 54, date '878-11-11');
insert into fauna(name, avg_age, discovery_date)
values ('fish', 11547, null);

select * from fauna where name like '%fish%';
select avg_age from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select name,discovery_date from fauna where discovery_date < '1950-01-01';



