select * from member;

update member
set birthday = '20000423'
where region = '동작구';

select count(*) from all_tables where table_name = 'member';
