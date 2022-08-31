create table products (
    id serial primary key,
    name varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, count, price) VALUES ('milk', 5, 50);
insert into products (name, count, price) VALUES ('sugar', 10, 100);
insert into products (name, count, price) VALUES ('bread', 15, 40);

-- Read Committed
-- Начинаем первую транзакцию
begin;
select * from products;
/*
 id | name  | count | price
----+-------+-------+-------
  1 | milk  |     5 |    50
  2 | sugar |    10 |   100
  3 | bread |    15 |    40
(3 строки)
*/
--редактирование таблицы
insert into products (name, count, price) VALUES ('water', 20, 70);
delete from products where price = 50;
update products set price = 80 where name = 'milk';

select * from products;
--состояние таблицы после редактирования
/*
id | name  | count | price
----+-------+-------+-------
  2 | sugar |    10 |   100
  3 | bread |    15 |    40
  6 | water |    20 |    70
(3 строки)
*/

commit;

--Repeatable read
--первая транзакция
begin transaction isolation level repeatable read;

select * from products;
--состояние таблицы
/*
 id | name  | count | price
----+-------+-------+-------
  2 | sugar |    10 |   100
  3 | bread |    15 |    40
  6 | water |    20 |    70
(3 строки)
*/

--редактирование таблицы
insert into products (name, count, price) VALUES ('milk', 5, 50);
delete from products where price = 40;
update products set price = 80 where name = 'sugar';

select * from products;
--состояние таблицы
/*
 id | name  | count | price
----+-------+-------+-------
  6 | water |    20 |    70
  7 | milk  |     5 |    50
  2 | sugar |    10 |    80
(3 строки)
*/

commit;

--Serializable
--запуск первой транзакции
begin transaction isolation level serializable;
select sum(count) from products;
--полученное значение
/*sum
-----
  29
(1 строка)
*/

update products set count = 24 where name = 'water';

commit;
/*ОШИБКА:  не удалось сериализовать доступ из-за зависимостей чтения/записи между транзакциями
ПОДРОБНОСТИ:  Reason code: Canceled on identification as a pivot, during commit attempt.
ПОДСКАЗКА:  Транзакция может завершиться успешно при следующей попытке.
*/
