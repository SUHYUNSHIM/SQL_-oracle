--문제1
--employees 테이블에서 급여가 15000 이상인 사원들의 이름과 급여만을 
--저장하는 view를 만드시오.
--단 view 이름이 있으면 지금 만든 뷰로 이름이 대체 가능해야 하고, 만일 급여가 해당 조건이
--아닌 경우를 insert하려 할 경우 처리가 안되어야 한다.
grant insert on sal_15_view  to hr;


create or replace view sal_15_view 
as select first_name, salary
from hr.employees
where salary >=15000
with check option;

commit;
show user;
select * from sal_15_view;
insert into sal_15_view(salary) values(15000);

--2. 주어진 테이블에 table-level 방식의 제약조건을 넣어 전체 테이블을 완성하시오
-- 제약 조건, name은 기본키로 만든다. 
--tel은 null을 제외하면 같은 번호가 있으면 안된다.
--hobby는 null이 되면 안된다.
--dept_id는 dept_table의 기본키 d_id를 참조한다. 

create table memtable2 (
name varchar2(20),
tel varchar2(20),
hobby varchar2(30),
dept_id number(5)
);

alter table memtable2 
add constraint memtable2_name_pk primary key(name);

alter table memtable2
add constraint memtable2_tel_uk unique(tel);

alter table memtable2
modify (hobby varchar2(20) constraint memtable2_hobby_nn not null);

alter table memtable2
add constraint memtable2_dept_id_fk references dept_table(d_id);

commit;

--3.  다음 프로시저를 완성하시오 .
-- 사원 아이디와 인상율을 입력받아 해당 사원의 급여를 인상율 만큼 인상하는 
--update 문장이 들어간 프로시저를 완성하시오.
--단 입력으로 110과 10이 들어온 경우라면 110번 사원의 급여를 10%로 인상하는 경우이다.

grant update on employees to hr;

create or replace procedure inc_salary_pc
(v_e_id in employees.employee_id%type, v_percent in number)
is   
begin
    update employees
    set salary= salary * (1+v_percent/100)
    where employee_id = v_e_id;
end;
/
commit;
select salary from employees where employee_id = 110;   --24000, 17000, 17000

exec inc_salary_pc(110,10);
commit;
select salary from employees where employee_id = 110;  

rollback;



