

-- Read Committed
-- Начинаем вторую транзакцию
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

select * from products;
--состояние таблицы после выполнения редактирования в первой транзакции
/*
 id | name  | count | price
----+-------+-------+-------
  1 | milk  |     5 |    50
  2 | sugar |    10 |   100
  3 | bread |    15 |    40
(3 строки)
*/

select * from products;
--состояние таблицы после коммита первой транзакции
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
--вторая транзакция
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

--выполняем редактирование данных при запущенной первой транзакции
update products set price = 80 where name = 'sugar';
--получили LOCK

commit;
--rollback, транзакция не исполнилась

select * from products;
--состояние таблицы не изменилось
/*
 id | name  | count | price
----+-------+-------+-------
  2 | sugar |    10 |   100
  3 | bread |    15 |    40
  6 | water |    20 |    70
(3 строки)
*/

--Serializable

--запуск второй транзакции
begin transaction isolation level serializable;
select sum(count) from products;
--полученное значение после выполнения обновления в первой транзакции не изменилось
/*sum
-----
  29
(1 строка)
*/

update products set count = 24 where name = 'milk';

commit;