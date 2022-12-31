create database bd
drop table RoomRegister
drop table People
create table People(
	fName varchar(30),
    lName varchar(30),
    birth char(10),
    register char(10),
    phone integer,
    email varchar(30),
    primary key (register)
)

create table RoomRegister(
    roomNumber integer,
    register char(10),
    date char(10),
    primary key (roomNumber),
    foreign key (register) references People(register)
)