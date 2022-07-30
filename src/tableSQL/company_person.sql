create TABLE company(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

create TABLE person(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values (1, 'Mars'), (2, 'Venera'), (3, 'Sun'), (4, 'Pluton'), (5, 'World');
insert into person(id, name, company_id) values (1, 'Nik', 1), (2, 'Mikl', 1), (3, 'Alex', 2),
 (4, 'Rose', 2), (5, 'Dick', 2), (6, 'Pam', 3), (7, 'Stas', 3), (8, 'Anton', 4),
 (9, 'Oliver', 5), (10, 'Oliver', 5) ;


select c.name, p.name
from person p
join company c
on p.company_id = c.id
where company_id != 5 ;

select c.name as company_name, COUNT(p.company_id) as count_person
from person p
join company c
on p.company_id = c.id
group by c.name
having count(p.company_id) = (
select max(maxcount)
from (
select c.name, count(p.company_id) as maxcount
from person p
	join company c
	on p.company_id = c.id
group by c.name) foo);
