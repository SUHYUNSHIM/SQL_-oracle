create or replace function avgsal_dept_func
(d_id in hr.employees.department_id%type)
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
     select '부서의 평균 급여는' || avgsal_dept_func('80') from dual;
   --
  variable aaa number
  execute :aaa := avgsal_dept_func(80);
  print aaa
    
    
       
        