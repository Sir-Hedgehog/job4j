create table seller (
  id serial PRIMARY KEY,
  name varchar(50) not null,
  number_of_phone varchar(20) not null
);

select * from seller;

delete from seller;

drop table seller;