create or replace function tax_value()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.20
        where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';	

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax_value();
	
	insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
	