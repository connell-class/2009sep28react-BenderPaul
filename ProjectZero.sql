create database projectzero

create user admin with password 'admin';

grant all privileges on schema projectzeo to admin;

-------------------- Drop Tables / Select from Tables  ------------------------------------

drop table cars;
drop table offers;
drop table customers;
drop table employees;
drop table sold_cars;

select * from cars;
select * from offers;
select * from customers;
select * from employees; 
select * from sold_cars;

-------------------------- Construct Tables -------------------------------------

create table sold_cars(
	tag_id serial primary key,
	model_year numeric,
	make varchar,
	model varchar,
	color varchar,
	sold_price numeric,
	sold_to_first_name varchar,
	sold_to_last_name varchar,
	down_payment numeric,
	monthly_payment numeric,
	remaining_balance numeric
)

create table cars(
	tag_id serial primary key,
	model_year numeric,
	make varchar,
	model varchar,
	color varchar,
	default_price numeric,
	highest_current_offer numeric
)

create table customers(
	customer_id serial primary key,
	first_name varchar,
	last_name varchar,
	username varchar,
	pass varchar
)

create table employees(
	employee_id serial primary key,
	first_name varchar,
	last_name varchar,
	username varchar,
	pass varchar,
	access_level int
)

insert into employees values(default, 'Herbert', 'Powell', 'Mr', 'Manager', 10);

create table offers( 
	tag_id int,
	price numeric,
	offer_amount numeric,
	first_name varchar,
	last_name varchar
)

----------------------- Some Table Population  --------------------------------------------

insert into cars values(default,2002,'Hyundai','Elantra','silver',5000,null);
insert into cars values(default,1991,'Powell','The Homer','Green?',82000,null);
insert into cars values(default,1982,'Knight Rider', 'Kitt', 'Black', 50000, null )

update offers set offer_amount = 2500 where make='Hyundai';

--------------------------------- Procedures -----------------------------------------------------------------------------------------

---- add car to lot

drop procedure add_new_car_to_lot(x numeric,y varchar,z varchar,a varchar, b numeric, c boolean);

create or replace procedure add_new_car_to_lot(x numeric, y varchar, z varchar, a varchar, b numeric)
	language sql as $$
	insert into cars (tag_id, model_year, make, model, color, default_price) values (default, x, y, z, a, b);
$$;

---- sell a car

create or replace procedure sell_car(i int, sell_price numeric, sold_to_first varchar, sold_to_last varchar, down_p numeric, monthly_p numeric, remaining_b numeric)
	language sql as $$
	insert into sold_cars
	select tag_id, model_year, make, model, color from cars where i = tag_id;
	
	delete from offers where tag_id = i;
	delete from cars where tag_id = i;
	
	update sold_cars set sold_price = sell_price, sold_to_first_name = sold_to_first, sold_to_last_name = sold_to_last, down_payment = down_p, monthly_payment = monthly_p, remaining_balance = remaining_b where i = tag_id;
$$;

call sell_car(6,8000,'paul','bender',1000,500,7000);

---- make offer on a car

create or replace procedure add_existing_car_to_offer_table(id int, offer numeric, first_n varchar, last_n varchar)
	language sql as $$
	insert into offers 
	select tag_id from cars
	where id = tag_id;
	
	update offers set price = default_price from cars where cars.tag_id = id;
	
	update offers set offer_amount = offer, first_name = first_n, last_name = last_n where tag_id = id;
$$;

call add_existing_car_to_offer_table(7,2200,'paul','bender'); 

---- Register new customer

create or replace procedure register_new_customer(cust_first varchar, cust_last varchar, login_name varchar, login_pass varchar)
	language sql as $$
	insert into customers (first_name, last_name, username, pass) values (cust_first, cust_last, login_name, login_pass);
$$

---- Pull owned cars
drop function pull_a_customers_cars;


create or replace function pull_a_customers_cars(custname varchar, custlast varchar)
	returns table(tag_id integer, model_year numeric,make varchar,model varchar,color varchar,sold_price numeric,sold_to_first_name varchar,sold_to_last_name varchar,down_payment numeric,monthly_payment numeric,remaining_balance numeric) as $$
	begin
	return query select * from "sold_cars" where sold_cars.sold_to_first_name = custname and sold_cars.sold_to_last_name = custlast;
	end
$$ language plpgsql;



	
