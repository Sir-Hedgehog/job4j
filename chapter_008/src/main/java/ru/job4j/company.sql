CREATE TABLE company (
	id integer NOT NULL,
	name varchar(200),
	CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person (
	id integer NOT NULL,
	name varchar(200),
	company_id integer,
	CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into person(id, name, company_id) values(1, 'Иванов', 3);
insert into person(id, name, company_id) values(2, 'Петров', 5);
insert into person(id, name, company_id) values(3, 'Сидоров', 5);
insert into person(id, name, company_id) values(4, 'Комличенко', 1);
insert into person(id, name, company_id) values(5, 'Романова', 5);
insert into person(id, name, company_id) values(6, 'Скрабова', 3);
insert into person(id, name, company_id) values(7, 'Крот', 3);
insert into person(id, name, company_id) values(8, 'Аршавин', 1);

insert into company(id, name) values(1, 'Apple');
insert into company(id, name) values(3, 'Google');
insert into company(id, name) values(5, 'Яндекс');

select p.name, c.name from person as p left outer join company as c on p.company_id = c.id where c.id != 5;

with g as (select company.name, count(person) as c from company inner join person on (company.id = person.company_id) group by company.name)
select * from g where g.c = (select max(g.c) from g)