select * from employees;

select e.first_name 이름, d.department_name 부서명, c.country_name 국가명
from employees e, departments d, locations l, countries c
where e.department_id = d.department_id and d.location_id = l.location_id and l.country_id = c.country_id
union all
select e.first_name 이름, ' ' 부서명, ' ' 국가명
from employees e
where e.department_id is null;

drop table memidtable;
create table memidtable11(
    memid number(6)
              constraint memidtable11_memid_pk primary key,
    memname varchar2(20)
    );

insert into memidtable11
values (100,'hong');
commit;

insert into memidtable11
values(500,'doyoung');
commit;

insert into memidtable11
values(666,'fullsun');
commit;



--column level 방식. 제약조건을 컬럼에 이어서 쓴다. 
create table memtable11(
    memname varchar2(20)
                constraint memtable11_memname_pk primary key,
    mentel varchar2(20)
                constraint memtable11_memtel_uk  unique,
    memhobby varchar2(30)
                constraint memtable11_memhobby_nn not null,
    membi number(5)
                constraint memtable11_membi_ck check (membi>1000),
    memid number(6)
                constraint memtable11_memid_fk references memidtable11(memid)                
    );
    
    insert into memtable11
   values('hong', '010-000-0000', 'comic',1500,100);
   
--table level 방식. 맨 밑에 몰아서 제약조건을 쓴다.

drop table memtable22;
create table memtable22(
    memname varchar2(20),
    memtel varchar2(20),
    memhobby varchar2(30)
        constraint memtable22_memhobby_nn not null,
    membi number(5),
    memid number(6),
    
    constraint memtable22_memname_pk primary key(memname),
    constraint memtable22_memtel_uk  unique(memtel),
    constraint memtable22_membi_ck check (membi>1000),
    --constraint memtable11_memhobby_nn not null(memhobby),
    constraint memtable22_memid_fk foreign key(memid) references memidtable11(memid)
    );
    
    --constraint memtable22_memtel22_ck check(memtel like '02-7788-%'),
    --constraint memtable22_memname2_pk primary(memname,memtel),
    --constraint memtable22_memtel23_ul primary(memtel),

insert into memtable22
values ( 'doyoung' ,'010-000-0000','comedy',2100,500);
commit;

insert into memtable22
values('haechan','010-5555-6666','romance',6600,666);

create table mem_t(
        bun number(5)
         constraint mem_t_bun_nn not null,
        me_bun varchar2(10),
        me_name varchar2(30),
        jang char(30),
        tel varchar2(20),
        
        constraint memt_t_me_bun_pk primary key(me_bun),
        constraint mem_t_tel_uk  unique(tel)
         );
create table man_t(
         bun number(5)
         constraint man_t_bun_nn not null,
         ma_bun varchar2(10)
         constraint man_t_ma_bun_pk primary key,
         ma_name varchar2(50),
         ipko date,
         damdang varchar2(20)        
        );      
drop table daeyeo_t;

create table daeyeo_t(
         bun number(5)
         constraint daeyeo_t_bun_nn not null,
         m_bun varchar2(10),
         b_bun varchar2(10),
         soo number(3),
         nalja date default sysdate,
         don number(5),
         
         constraint daeyeo_t_m_bun_fk foreign key(m_bun) references mem_t(me_bun),
         constraint daeyeo_t_b_bun_fk foreign key(b_bun) references man_t(ma_bun),
         constraint daeyeo_t_soo_ck check(soo>0)         
         );

    insert into mem_t values(1,'S1','김도영','thriller','010-2222-1111');
    insert into mem_t values(2,'S2','이동혁','comedy','010-6666-6666');
    insert into mem_t values(3,'S3','나재민','romance','010-8888-0013');
    
    
    insert into man_t values(1,'M5','그들의 이야기','2021/06/17','김담당');
    commit;
    delete from man_t where ma_bun = 'M1';
    commit;
    insert into man_t values(2,'M2','맛집 로드','2020/09/20','이담당');
    insert into man_t values(3,'M3','필라테스 자세 모음','2000/06/01','나담당');
    
    insert into daeyeo_t values(1,'S1','M5',12,'2021/06/19',2100);
    insert into daeyeo_t values(2,'S2','M2',6,'2019/11/02',6100);
    insert into daeyeo_t values(3,'S3','M3',8,'2021/06/04',3810);
    
    commit;


select * from mem_t;
select * from man_t;
select * from daeyeo_t;

--대여테이블의 s1 회원의 수량을 2개로, 대여료 2000으로 변경


update daeyeo_t
set soo =2 , don = 2000
where m_bun = 'S1';
commit;

select * from daeyeo_t;

select * from daeyeo_t
where nalja = '2021/06/19';

delete from daeyeo_t
where bun =3;
commit;

select * from daeyeo_t;
select * from mem_t;

 insert into daeyeo_t values(1,'S1','M5',12,'2021/06/19',1400);
 commit;
 
 select me_name, ma_name, soo, don
 from mem_t, man_t, daeyeo_t
 where mem_t.me_bun = daeyeo_t.m_bun and man_t.ma_bun = daeyeo_t.b_bun and daeyeo_t.don<=1500;
 
 select * from dictionary
 where lower(table_name) like 'user_%';
 
 select * from user_objects;
 
 select constraint_name, constraint_type, search_condition, r_constraint_name from user_constraints
 where table_name = 'MEMTABLE11';

select * from user_tables;
select * from user_cons_columns
where table_name  = 'MEMTABLE22';

create table modi_col(
    id number(5),
    name varchar2(20),
    ipsail date default sysdate
    );
commit;

alter table modi_col
    add( buseo varchar2(20)
        constraint modi_col_buseo_ck check( buseo in('insa','jajae'))
        );
desc modi_col;

alter table modi_col
    rename column name to irum;
    
alter table modi_col 
    drop column irum;
    
alter table modi_col
modify (id number(7));

    
alter table modi_col
modify (id number(5));

desc modi_col;

insert into modi_col
values (10000, sysdate,'jajae');

insert into modi_col
values (12700, sysdate,'insa');

alter table modi_col
modify (id number(7));

select * from modi_col;

alter table modi_col
add name varchar(20) constraint modi_col_uk unique;

ALTER TABLE modi_col
ADD CONSTRAINT  modi_col_name_uk UNIQUE(name);

select constraint_name, constraint_type, search_condition
from user_constraints
where table_name ='MODI_COL';

select * from modi_col;
--이렇게 확인이 되면 만들 제약조건을 확인하기 쉽다.

alter table modi_col
drop constraint modi_col_name_uk;

select * from mem_t;
select * from man_t;
select * from daeyeo_t;

update modi_col
set name = 'choi'
where id = 10000;

select * from modi_col;

alter table modi_col
modify (name varchar2(20) 
            constraint modi_col_name_nn not null);
            
create view dept_100_view
as select first_name , phone_number  
from employees
where department_id =100;

select * from dept_100_view;

select view_name, text from user_views;
select text from user_views
where view_name = 'DEPT_100_VIEW';

create or replace view dept_100_view
as select first_name, phone_number
from employees
where department_id =80;

select * from dept_100_view;

create view dept_info_view
as select d.department_name 부서명 ,
min(e.salary) 최소급여, max(e.salary) 최대급여,
avg(e.salary) 평균급여
from employees e, departments d
where e.department_id = d.department_id
group by d.department_name;
    
create or replace view dept_info_view(부서명, 최소급여, 최대급여, 평균급여)
as select d.department_name, min(e.salary) , max(e.salary) , avg(e.salary) 
from employees e, departments d
where e.department_id = d.department_id
group by d.department_name;

create or replace view manager_121_view 
as select first_name 이름,manager_id 매니저번호, employee_id 사원번호
from employees
where manager_id =121
with check option;

select * from manager_121_view;

update manager_121_view 
set 매니저번호 =150
where 사원번호 =131; 

create or replace view salary_view
as select employee_id , salary 
from employees
where salary <=10000
with check option;

select * from salary_view;

update salary_view 
set salary =20000
where salary <=5000;

update salary_view
set salary = salary* 1.2
where salary <=10000;

select * from employees
where manager_id =122;

create or replace view emp_v_mid_122
as select * from employees
where manager_id =122
with read only;

update emp_v_mid_122
set manager_id =121
where employee_id =135;

create sequence aa_seq2;
select aa_seq2.nextval from dual;

select aa_seq2.currval from dual;

create table seq_numbering(
    bun number(7),
    sabun varchar2(5)
    );
commit;

create sequence nums;
insert into seq_numbering(bun)
values(nums.nextval);

select * from seq_numbering;






