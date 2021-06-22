show user;
grant select ,insert(id,name) on modi_col to babo, hong;

select * from modi_col;


    set serveroutput on
    create or replace procedure inc_salary    
    is
        employee_id varchar2(20);
    begin
        update employees
        set salary = salary * 1.1       
        where employee_id >= 120;        
        dbms_output.put_line(employee_id ,salary);
    end;
    /
    
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
        set keum = :old.dan* :new. soo
        where id = :old.id;
    end;
    /
    
    update panmae_table
    set soo =57
    where id =1;
    commit;
    
    select * from keum_table;
        
        
            