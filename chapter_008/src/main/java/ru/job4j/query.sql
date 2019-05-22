create table type (
	id serial primary key,
	name varchar(2000)
)

create table product (
	id serial primary key,
	name varchar(2000),
	type_id int references type(id),
	expired_date varchar(2000),
	price int,
	quantity int
)

--Сыр (тип 2)
select * from product as p
where p.type_id = 2

--Мороженое
select * from product as p
where p.name like '%Мороженое%'

--Срок годности за июнь
select * from product as p
where p.expired_date like '%06.2019%';

--Самый дорогой продукт
select * from product as p
where p.price = (select max(price) from product);

--Количество продуктов определенного типа (например, молоко(тип 2))
select * from product as p
where p.type_id = 2

--Количество всех продуктов определенного типа
select * from product as p
where p.type_id = 2 and quantity < 11

--Сыр и молоко (тип 1 и 2, соответственно)
select * from product as p
where p.type_id in (1, 2)

--Тип продуктов, цена которых меньше 350 руб.
select * from product as p
where p.price < 100 

--Все продукты и их тип
select * from product 