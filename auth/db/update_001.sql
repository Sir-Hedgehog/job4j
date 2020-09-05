create table person (
  id serial primary key not null,
  login varchar(2000),
  password varchar(2000)
);

insert into person (login, password) values ('potap', '123');
insert into person (login, password) values ('shredder', '123');
insert into person (login, password) values ('tyler', '123');