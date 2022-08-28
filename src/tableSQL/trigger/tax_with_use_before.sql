create or replace function tax_value_before()
    returns trigger as
$$
    BEGIN
        update products
        set tax = price * 0.10;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_before
    before insert on products
    for each row
    execute procedure tax_value_before();
	
	insert into products (name, producer, count, price) VALUES ('product_3', 'producer_3', 8, 115);
	select * from products;
	
	