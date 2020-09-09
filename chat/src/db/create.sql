CREATE TABLE roles (
  id serial PRIMARY KEY not null,
  role VARCHAR(50) not null unique
);

create table rooms (
  id serial PRIMARY KEY not null,
  theme varchar(500),
  description varchar(100000)
);

create table persons (
  id serial PRIMARY KEY not null,
  name varchar(100),
  sex varchar(10),
  age int,
  login varchar(100),
  password varchar(100),
  role_id int not null references roles(id)
);

create table person_to_room (
  id serial PRIMARY KEY not null,
  person_id int not null references persons(id),
  room_id int not null references rooms(id)
);

create table messages (
  id serial PRIMARY KEY not null,
  comment varchar(100000),
  created timestamp without time zone not null default now(),
  room_id int not null references rooms(id)
);


