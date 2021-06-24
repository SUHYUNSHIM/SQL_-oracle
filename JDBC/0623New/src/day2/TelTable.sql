create table TelTable(

	id number(5),
	name varchar2(20),
	tel varchar2(20),
	d date
	);

insert into TelTable
values (1,'hong','010-7777-8888','20160201');
insert into TelTable
values (2,'park','010-3333-5555','20160207');

commit;

select * from TelTable;