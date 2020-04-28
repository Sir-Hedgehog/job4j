create table car (
	id serial PRIMARY KEY,
	engine_id int not null references engine(id),
	model varchar(20),
	year_of_release varchar(10),
	body_type varchar(20)
);

select * from car;

delete from car;

drop table car;