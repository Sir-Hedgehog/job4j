create table employee (
  id serial primary key not null,
  name varchar(2000),
  surname varchar(2000),
  taxpayer_number varchar(12),
  hiring_date date
);

create table person (
  id serial primary key not null,
  login varchar(2000),
  password varchar(2000),
  employee_id int references employee(id)
);