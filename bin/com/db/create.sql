--create database petworld;

use petworld;

create table petworld.logintbl(
ID varchar(30) NOT NULL, 
비밀번호 varchar(30)NOT NULL,
이름 varchar(50)NOT NULL,
전화번호 varchar(30) NOT NULL,

PRIMARY KEY(ID)
);


create table petworld.dogtbl(
품종 varchar(30) primary key,
국적 varchar(30),
크기 varchar(30),
털빠짐 varchar(30),
성격 varchar(30),
분양가 varchar(30)

);


create table petworld.Shoptbl(
애견샵 varchar(30) primary key,
지역 varchar(30),
주소 varchar(50),
연락처 varchar(30),
사이트 varchar(50)
);



---------------------------------------------
drop table petworld.logintbl; 
drop table petworld.dogtbl; 
drop table petworld.shoptbl; 

select * from petworld.logintbl;
select * from petworld.dogtbl;
select * from petworld.Shoptbl;


