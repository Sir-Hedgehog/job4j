create table history_owner (
	  id serial PRIMARY KEY,
    driver_id int not null references driver(id),
   	car_id int not null references car(id)
);

select * from history_owner;

delete from history_owner;

drop table history_owner;
