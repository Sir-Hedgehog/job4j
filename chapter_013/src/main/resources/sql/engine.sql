create table engine (
	id serial primary key,
	power int,
	volume int
);

select * from engine;

delete from engine;

drop table engine;