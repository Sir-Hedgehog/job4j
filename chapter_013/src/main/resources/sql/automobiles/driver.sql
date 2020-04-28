create table driver (
	id serial PRIMARY KEY,
	name varchar(100),
	sex varchar(10),
	age int
);

select * from driver;

delete from driver;

drop table driver;