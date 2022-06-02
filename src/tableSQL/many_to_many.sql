create table customers(
     id serial primary key,
     name varchar(255)
 );
 
 create table currency(
     id serial primary key,
     name varchar(255)
 );
 
 create table customers_currency(
     id serial primary key,
     customer_id int references currency(id),
     currency_id int references customer(id)
 );
 
insert into customers(name) values ('Aleksey');
insert into customers(name) values ('Ivan');
insert into customers(name) values ('Roman');

insert into currency(name) values ('Dollar');
insert into currency(name) values ('Euro');
insert into currency(name) values ('Frank');

insert into students_courses(student_id, course_id) values (1, 1);
insert into students_courses(student_id, course_id) values (1, 2);
insert into students_courses(student_id, course_id) values (2, 1);
insert into students_courses(student_id, course_id) values (2, 2);
insert into students_courses(student_id, course_id) values (3, 2);
insert into students_courses(student_id, course_id) values (3, 3);