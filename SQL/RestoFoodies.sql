create schema RestoFoodies;
use RestoFoodies;

select * from user;
select * from restaurant;
select * from category;
select * from food_item;
select * from orders;
select * from order1;
select * from added_items;
select * from list;

create table user(
uid int primary key auto_increment,
username varchar(50) not null unique,
password varchar(50) not null,
fullname varchar(50) not null,
role varchar(50) default "Customer",
contact varchar(50) not null,
email varchar(50),
location varchar(100),
latlng varchar(100) not null,
profile varchar(50) not null default "user.jpg"
);
create table restaurant(
rid int primary key auto_increment,
name varchar(100) not null unique,
contact varchar(50) not null,
email varchar(50) not null,
categories text,
profile varchar(100),
uid int references user(uid)
);
create table branch(
bid int primary key auto_increment,
bname varchar(50) not null,
location varchar(100) not null default "0,0",
rid int references restaurant(rid)
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
create table order1(
oid int primary key auto_increment,
recipient_name varchar(50) not null,
source varchar(100) not null,
destination varchar(50) not null,
contact varchar(50) not null,
status varchar(50) not null default 'Placed',
items text,
price float,
bid int references branch(bid),
rname varchar(50) not null,
dname varchar(50)
);