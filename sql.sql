use bd;
drop table room;
drop table customer;


use bd;
create table customer(
	cus_code varchar(20) primary key,
    pass varchar(10),
	cusfname varchar(50) ,
    cuslname varchar(50),
    cus_birth  char(20),
    cus_reg char(20),
    cus_phone char(20),
    isadmin boolean
);
create table bd.room(
	room_id int primary key,
    cus_code varchar(20),
    r_Date varchar(10),
    price int,
    people int,
    foreign key (cus_code) references customer(cus_code)
);

use bd;
insert into customer values('', '', '', '', '', '', '', false);
insert into customer values ('admin', '2,admin', 'admin', 'admin', '', '', '', true);

use bd;
update customer set pass = '2,admin' where cus_code = 'admin'

select * from bd.customer;
select * from bd.room;