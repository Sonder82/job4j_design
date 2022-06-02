create table vehicle_identification  (
    id serial primary key,
    vin int
);

create table car(
    id serial primary key,
    model varchar(255),
    vin_id int references vehicle_identification (id) unique
);

insert into vehicle_identification (vin) values ('789456');
insert into car(model, vin_id) VALUES ('BMW', 1);

select * from car;
