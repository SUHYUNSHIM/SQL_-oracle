--뷰 만드는 방법
--제약 조건. not null. 두 가지 방식. 
--function, procedure
--update, 삼각형, 달
--read only option.. 

--컬럼 레벨 방식 제약조건
create table memtable(
    memname varchar2(20),
    --constraint memtable_memname_pk primary key,
    memtel varchar2(20),
    --constraint memtable_memtel_uk unique,
    memhobby varchar2(20),
    constraint memtable_memhobby_nn not null,
    membi number(5),
    --constraint memtable_membi_ck check(membi>1000),
    memid number(6),
    --constraint memtable_memid_fk references memidtable(memid)
   
     --테이블 레벨 방식
     constraint memtable_memname_pk primary key(memname),
     constraint memtable_memtel_uk unique(memtel),
     constraint memtable_membi_ck check(membi>1000),
     constraint memtable_memid_fk foreign key(memid) references memidtable(memid)
      );
      
    ---check 
    --constraint memtable_memtel_ck check(memtel like '02-7788-%')
    
    --테이블을 join해야 할 때는 fk, pk를 보고 같은지, alias를 통해서 where 조건문에 넣어준다.
    --컬럼 제약 조건 테이블
    select * from user_table;
    select * from user_cons_columns;
    
    --제약 조건, add
    alter table modi_col
    add(dept varchar2(20)
    constraint modi_col_dept_ck check(dept in ('a','b'))
    );
    
    alter table modi_col
    add constraint modi_col_name_nn not null (name);  --> 오류
    
    alter table modi_col
    modify (name varchar2(20) constraint modi_col_name_nn not null);  --> not null은 길이에 관련된 것이다.
    --따라서 modify로 제약 조건을 추가한다.
    
    --alter table 테이블명 rename column a to b;
    --alter table 테이블명 drop column 컬럼명;
    --alter table 테이블명 modify (컬럼명 타입(길이));
    --select constraint_name, constraint_type, search_condition from user_constraints;
    
    --제약 조건 삭제 -> 제약 조건을 삭제하려면 예를 들면 unique를 삭제하는 것이 아니라 
    --unique 에 대해 붙여놓은 제약 조건 이름을 drop으로 지우는 것이다.
    alter table modi_col
    drop constraint modi_col_name_uk; 
    
    --
    create or replace view dept_view (부서명, 최소급여)
    as select d.department_name, min(e.salary)
    from emloyees e, deparment d
    where e.department_id = d.department_id
    group by d.department_name;
    
    --with check option -> 조건문에 있는 칼럼은 view로 변경을 못하게 한다.
    --with read only -> 뷰 수정을 못하게 한다.
    
    --sequence
    create sequence aa_seq
    increment by 1
    start with 10
    maxvalue 500
    nocycle
    nocache;
    --create sequence bunho;
    --insert into tableeasy
    --values (bunho.nextval, 5001);
    
    --인덱스 생성
    create index idx_employees_salary on employees(salary);
    drop index idx_employees_salary;
    
    --select * from user_indexes;
    --select * from user_ind_columns;
    
    --생성 및 접속 권한
    create user babo identified by ondal;  --> 유저명 babo, 비밀번호 ondal 
    grant create session to babo;
    alter user babo identified by baboondal;
    grant create table, creat view to babo;
    alter user babo default tablespace users quota 50m on users;
    revoke create view from babo;
    -- grant a on 테이블명 to b
    grant select, insert(id,name) on hr.modi_col to babo,hong;
    insert into hr.modi_col(id,name) values('1000','suhyun');
    revoke insert on hr.modi_col from babo;
    
    --
    grant select on hr.modi_col to hong with grant option;
    grant select on hr.modi_col to public;
    revoke select on hr.modi_col from hong;
    drop user hong cascade ; -- 
    
    
    --function
    create or replace function nulbi
    (v_mit in number, v_nopi in number)
    return number
    is 
    v_nulbi number;
    begin
    v_nulbi := (v_mit * v_nopi) /2;
    return v_nulbi;
    end;
    /
    select '넓이는' || nulbi(100,20) from dual;
    
    variable result number
    execute :result :=nulbi(100,20)
    print result
    
    start 경로; 
    
    set serveroutput on;
    dbms_output.put_line();
    
    --%type
    --변수명 테이블명.컬럼명%type 
    --%rowtype
    --변수명 테이블명(뷰)%rowtype 
    
    --함수 예제
    create or replace function avgsal
    (d_id in hr.employees,department_id %type)
    return number
    is
        d_avg_sal hr.employees.salary%type;
        begin
            select avg(salary) into d_avg_sal
            from hr.employees
            where department_id = d_id;
            return d_avg_sal;
        end;
        /
        select '부서급여' || avgsal('80') from dual;
    
    --달
    create or replace function moon(v_earth in number)
    return number
    is
    v_moon number;
    begin
    v_moon := v_earth* (1/6);
    return (v_moon);
    end;
    /
    
    --프로시저
    set serveroutput on
    create or replace procedure dal_mom(v_earth in number)
    is
        v_dal number(7,2);
    begin
        v_dal := v_earth * round((1/6),2);
        dbms_output.put_line(v_dal);
    end;
    /
    exec dal_mon(100.24);
    
    ---------
    create or replace procedure inc_salary_10
    (v_employee_id in employees.employee_id%type , v_percent in number)
    is
    begin
        update employees
        set salary = salary *(1+v_percent/100)
        where employee_id >=v_employee_id;
        
        end;
        /
    exec inc_salary_10(120,10)
    
    select * from employees;
 ----------------------------------------------------------------   
    set serveroutput on
    create or replace procedure select_steven
    is
        v_department_id employees.department_id%type;
        v_salary             employees.salary%type;
    begin
        select department_id , salary into v_department_id , v_salary 
        from employees                                                
        where first_name = 'Steven' and last_name = 'King';        
        dbms_output.put_line('Steven의 아이디= ' || v_department_id || '급여 = ' || to_char(v_salary, '$999,999,999'));
    end;
    /
    
    execute select_steven;
    
    ----------------------------
set serveroutput on    
create or replace procedure emp_info(v_d_id in employees.department_id%type,   v_name in employees.first_name%type)
is
 sawon_data employees%rowtype;
begin 
    select department_id, first_name, salary into sawon_data.department_id, sawon_data.first_name, sawon_data.salary
    from employees
    where department_id = v_d_id and first_name = v_name;
    dbms_output.put_line('부서 아이디= ' || sawon_data.department_id);
    dbms_output.put_line('이름 = ' || sawon_data.first_name);
    dbms_output.put_line('급여 = ' || sawon_data.salary);
   end;
   /
    
    execute emp_info(90,'Steven')   
  --------------------------------------------------------------
    --cursor    
    set serveroutput on
    create or replace procedure sawon_sal_gt(v_in_salary employees.salary%type)
    is
        v_first_name employees.first_name%type;
        v_salary        employees.salary%type;
        v_hire_date   employees.hire_date%type;
        v_cnt number(5) :=0;
        
     cursor salary_gt is 
         select first_name, salary, hire_date 
         from employees
         where  salary>v_in_salary;
    begin
        open salary_gt;             
         loop
         --select salary into v_salary
        fetch salary_gt into v_first_name , v_salary, v_hire_date;
        exit when salary_gt%notfound;
        dbms_output.put_line('사원이름=' || v_first_name || chr(10) || '급여=' || v_salary || chr(10) || '입사일' || v_hire_date);
        v_cnt := v_cnt+1;
        end loop; 
        dbms_output.put_line ('사원인원수=' || to_char(v_cnt)); 
        close salary_gt;
    end;
    /
    
    exec sawon_sal_gt(21000);
  -------------------------------------------------------------trigger  
    drop table panmae_table;
    create table panmae_table(
    id number(3),
    dan number(5),
    soo number(5)
    );
    commit;
    
    drop table keum_table;
    create table keum_table(
    id number(3),
    keum number(10)
    );
    commit;
    
    insert into keum_table
    values (1,200000);
    
    insert into keum_table
    values (2,25000);
    
    commit;
    
    create or replace trigger kyesan_trigger
    after update of soo on panmae_table
    for each row 
    begin
         update keum_table
    set keum=:old.dan*:new.soo 
    where id=:old.id;
    end;
    /
   
    update panmae_table
    set soo =57
    where id =1;
    commit;
    
    select * from keum_table;
    
    
    
    
    
    
    
    
    
    
    
    
  
    