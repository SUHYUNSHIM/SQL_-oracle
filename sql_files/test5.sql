select * from modi_col;
select salary, department_name 
from employees e, departments d
where e.department_id = d.department_id  and salary >2000;