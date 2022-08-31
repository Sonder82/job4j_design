create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);
insert into products (name, producer, count, price) VALUES ('milk', 'Irbit', 5, 50);
insert into products (name, producer, count, price) VALUES ('sugar', 'Rafinad', 10, 100);
insert into products (name, producer, count, price) VALUES ('bread', 'Rezj', 15, 40);
select * from products;
/*
 id | name  | producer | count | price
----+-------+----------+-------+-------
  1 | milk  | Irbit    |     5 |    50
  2 | sugar | Rafinad  |    10 |   100
  3 | bread | Rezj     |    15 |    40
(3 строки)
*/

begin;
insert into products (name, producer, count, price) VALUES ('tea', 'India', 8, 150);
update products set price = 80 where name = 'sugar';
select * from products;
/*
 id | name  | producer | count | price
----+-------+----------+-------+-------
  1 | milk  | Irbit    |     5 |    50
  3 | bread | Rezj     |    15 |    40
  4 | tea   | India    |     8 |   150
  2 | sugar | Rafinad  |    10 |    80
(4 строки)
*/
--создаем первую точку восстановления
savepoint first_savepoint;

delete from products where count > 8;
insert into products (name, producer, count, price) VALUES ('cofee', 'Columbia', 15, 250);
--создаем вторую точку восстановления
savepoint second_savepoint;

select * from products;
/*
 id | name  | producer | count | price
----+-------+----------+-------+-------
  1 | milk  | Irbit    |     5 |    50
  4 | tea   | India    |     8 |   150
  5 | cofee | Columbia |    15 |   250
(3 строки)
*/

insert into products (name, producer, count, price) VALUES ('water', 'Baikal', 85, 10);
select * from products;
/*
 id | name  | producer | count | price
----+-------+----------+-------+-------
  1 | milk  | Irbit    |     5 |    50
  4 | tea   | India    |     8 |   150
  5 | cofee | Columbia |    15 |   250
  6 | water | Baikal   |    85 |    10
(4 строки)
*/
--создаем третью точку восстановления
savepoint third_savepoint;

insert into products (name, producer, count, price) VALUES ('sugar', 'Rafinad', 15, 40);
select * from products;
/*
 id | name  | producer | count | price
----+-------+----------+-------+-------
  1 | milk  | Irbit    |     5 |    50
  4 | tea   | India    |     8 |   150
  5 | cofee | Columbia |    15 |   250
  6 | water | Baikal   |    85 |    10
  7 | sugar | Rafinad  |    15 |    40
(5 строк)
*/

--откат к третьей точке и последующий просмотр данных таблицы
rollback to third_savepoint;
select * from products;
 id | name  | producer | count | price
----+-------+----------+-------+-------
  1 | milk  | Irbit    |     5 |    50
  4 | tea   | India    |     8 |   150
  5 | cofee | Columbia |    15 |   250
  6 | water | Baikal   |    85 |    10
(4 строки)

--откат к второй точке и последующий просмотр данных таблицы
rollback to second_savepoint;
select * from products;
 id | name  | producer | count | price
----+-------+----------+-------+-------
  1 | milk  | Irbit    |     5 |    50
  4 | tea   | India    |     8 |   150
  5 | cofee | Columbia |    15 |   250
(3 строки)

--демонстрация случая когда был откат к второй точке и, соответственно,
--точка отката три перестала существовать
rollback to third_savepoint;
/*
ОШИБКА:  точка сохранения "third_savepoint" не существует
*/

--откат к первой точке и последующий просмотр данных таблицы
rollback to first_savepoint;
select * from products;
 id | name  | producer | count | price
----+-------+----------+-------+-------
  1 | milk  | Irbit    |     5 |    50
  3 | bread | Rezj     |    15 |    40
  4 | tea   | India    |     8 |   150
  2 | sugar | Rafinad  |    10 |    80
(4 строки)

commit;
