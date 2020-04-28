create table car (
  id serial PRIMARY KEY,
  seller_id int not null references seller(id),
  status varchar(10) not null,
  image varchar(50),
  model varchar(20) not null,
  year_of_release varchar(20),
  body_type varchar(20),
  power varchar(20),
  volume varchar(20),
  price varchar(20) not null,
  mileage varchar(20) not null
);

select * from car;

delete from car;

drop table car;