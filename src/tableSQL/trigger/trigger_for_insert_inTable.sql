create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

CREATE OR REPLACE FUNCTION insert_anotherTable()
  RETURNS trigger AS
$$
BEGIN
         INSERT INTO history_of_price(name,price,date)
         VALUES(NEW.name,NEW.price,current_date);
 
    RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';

create trigger insertTable
    after insert
    on products
    for each row
    execute procedure insert_anotherTable();
	
	insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
	
	select * from history_of_price;
	
	