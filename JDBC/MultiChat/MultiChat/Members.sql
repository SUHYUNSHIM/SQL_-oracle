select * from member;

update member
set birthday = '20000423'
where region = 'µø¿€±∏';

alter table member modify(nickname varchar(30));
alter table member modify(region varchar(30));


select count(*) from all_tables where table_name = 'member';   
