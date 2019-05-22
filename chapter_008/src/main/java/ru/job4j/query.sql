create table type (
	id serial primary key,
	name varchar(2000)
)

create table product (
	id serial primary key,
	name varchar(2000),
	type_id int references type(id),
	expired_date varchar(2000),
	price int,
	quantity int
)

--��� (��� 2)
select * from product as p
where p.type_id = 2

--���������
select * from product as p
where p.name like '%���������%'

--���� �������� �� ����
select * from product as p
where p.expired_date like '%06.2019%';

--����� ������� �������
select * from product as p
where p.price = (select max(price) from product);

--���������� ��������� ������������� ���� (��������, ������(��� 2))
select * from product as p
where p.type_id = 2

--���������� ���� ��������� ������������� ����
select * from product as p
where p.type_id = 2 and quantity < 11

--��� � ������ (��� 1 � 2, ��������������)
select * from product as p
where p.type_id in (1, 2)

--��� ���������, ���� ������� ������ 350 ���.
select * from product as p
where p.price < 100 

--��� �������� � �� ���
select * from product 