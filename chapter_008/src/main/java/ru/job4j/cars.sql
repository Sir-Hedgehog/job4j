create table car (
	id serial primary key,
	name varchar(200),
	engine_id int references engine(id),
	transmission_id int references transmission(id),
	body_id int references body(id)
)

create table engine (
	id serial primary key,
	name varchar(200),
)

create table transmission (
	id serial primary key,
	name varchar(200),
)

create table body (
	id serial primary key,
	name varchar(200),
)

select c.name, b.name, t.name, e.name
from car as c
left outer join body as b on c.body_id = b.id
left outer join transmission as t on c.transmission_id = t.id
left outer join engine as e on c.engine_id = e.id

select b.name from body as b left outer join car as c on c.body_id = b.id where c.id is null

select t.name from transmission as t left outer join car as c on c.transmission_id = t.id where c.id is null

select e.name from engine as e left outer join car as c on c.transmission_id = e.id where c.id is null