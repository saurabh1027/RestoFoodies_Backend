create schema RestoFoodies;
use RestoFoodies;

select * from user;
select * from restaurant;
select * from category;
select * from food_item;
select * from orders;
select * from added_items;
select * from list;

create table user(
uid int primary key auto_increment,
username varchar(50) not null unique,
password varchar(50) not null,
fullname varchar(50) not null,
role varchar(50) default "Customer",
email varchar(50),
address text,
profile varchar(50) not null default "user.jpg"
);
create table restaurant(
rid int primary key auto_increment,
name varchar(100) not null,
contact varchar(50),
email varchar(50),
branch varchar(100) not null,
categories text,
latlng varchar(100) not null,
opening_time varchar(50) not null,
closing_time varchar(50) not null,
username varchar(50) not null
);
create table category(
cid int primary key auto_increment,
cname varchar(50) not null unique,
description text
);
create table food_item(
fid int primary key unique not null auto_increment,
fname varchar(50) not null,
price double not null default 0,
pic varchar(50) not null,
quantity int not null default 1,
ingredients text,
description text,
vegeterian boolean not null,
ratings varchar(100) not null default '0/0',
ratio double not null default 0,
keywords text not null,
status varchar(50) not null default 'Available',
cname varchar(50) references category(cname),
rid int references restaurant(rid)
);
create table orders(
oid int primary key auto_increment,
name varchar(50) not null,
status varchar(50) default 'Unsubmitted',
location varchar(50) not null default '0,0',
total_price float not null default 0,
username varchar(50) references user(username)
);
create table added_items(
afid int primary key auto_increment,
quantity int not null default 1,
oid int references orders(oid),
fid int references food_item(fid)
);
create table list(
lid int primary key auto_increment,
oids text,
rid int references restaurant(rid)
);