create table type (
	id serial primary key,
	name varchar(2000)
);

create table product (
	id serial primary key,
	name varchar(2000),
	type_id int references type(id),
	expired_date timestamp,
	price int
);

--Cheese
select * from product as p, type as t 
where p.type_id = t.id and t.name like '%ÑÛÐ%';

--Ice-cream
select * from product as p
where p.name like '%Ìîðîæåíîå%';

--The expiration date of June
select * from product as p
where extract(month from p.expired_date) - extract(month from current_date) = 1;

--The most expensive product
select * from product as p
where p.price = (select max(price) from product);

--Quantity of all products of the certain type
select count(*) from product as p, type as t
where t.id = p.type_id and t.name like '%ÌÎÐÎÆÅÍÎÅ%';

--Cheese and milk
select * from product as p, type as t 
where p.type_id = t.id and t.name ~ ('ÑÛÐ|ÌÎËÎÊÎ');

--Type of products, which are less then 10 points
SELECT t.name FROM product AS p LEFT JOIN type AS t ON t.id = p.type_id
GROUP BY t.name HAVING COUNT(p.id) < 10;

--All products and its types
select t.name, p.name from type as t left outer join product as p on p.type_id = t.id;

