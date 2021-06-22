select *
from
    (
    select substr(hire_date, 4,2) �Ի��, count(*) �Ի��ڼ�
    from employees
    group by substr(hire_date,4,2)
    order by count(*) asc
    )
 where rownum <=8;
 
 select first_name �̸�, salary �޿�, department_name �μ���
 from employees, departments
 where employees.department_id = departments.department_id;

select e.first_name �̸�, e.manager_id �Ŵ������̵�, d.location_id �������̵�
from employees e, departments d
where e.department_id = d.department_id
order by e.first_name;

select e.first_name �̸�, e.manager_id �Ŵ������̵�, d.location_id �������̵�
from employees e , departments d
where e.department_id = d.department_id and e.manager_id >=120
order by e.first_name;

select e.first_name �̸�, j.job_title ��å
from employees e, jobs j
where e.job_id = j.job_id;

select e.first_name �����̸�, d.department_id �μ���,  l.city ����
from employees e, locations l, departments d
where e.department_id = d.department_id and d.location_id = l.location_id
order by city desc;

select e.first_name �̸�, c.country_id ������
from employees e, departments d,  locations l, countries c
where e.department_id = d.department_id and d.location_id = l.location_id
and l.country_id = c.country_id
order by first_name ;

select l.city �����̸�, count(*) "���ú� ������"
from employees  e, locations l, departments d
where e.department_id = d.department_id and l.location_id = d.location_id
group by city;

select d.department_id �μ��̸�, l.city ���ø�
from departments d, locations l 
where d.location_id = l.location_id;

select e.first_name �̸�, j.job_title ��å , d.department_id �μ���
from employees e, jobs j , departments d
where e.department_id = d.department_id and e.job_id = j.job_id and lower(j.job_title) like '%clerk%' ;

--and lower(j.job_title) ='clerk'

select e.first_name �̸�, j.job_title ��å , d.department_id �μ���
from employees e, jobs j , departments d
where e.department_id = d.department_id and e.job_id = j.job_id and lower(j.job_title) like '%representative%' ;

select e.first_name �̸�, e.last_name ��, to_char(e.salary*12, '$999,999,999') ����, j.job_title ��å
from employees e , jobs j 
where e.job_id = j.job_id and salary between 10000 and 25000
order by salary desc;

select l.city ���ø�, d.department_name �μ���, count(*) �ο���
from locations l, employees e , departments d
where e.department_id = d.department_id and d.location_id = l.location_id and substr(hire_date,1,2)<07
group by l.city, d.department_name
order by l.city, count(*) desc;

select e.first_name �̸�, e.salary �޿� , d.department_name �μ���
from employees e, departments d
where e.department_id(+) = d.department_id;

select e.employee_id ���, e.first_name �̸�, e.last_name ��, e.hire_date �Ի��� , nvl(d.department_name ,'�׷��μ�����') �μ���
from employees e left outer join  departments d
on (e.department_id = d.department_id)
where substr(e.hire_date,1,2) =07
order by last_name;

select e.employee_id ������̵�, e.manager_id �Ŵ������̵�, m.first_name �Ŵ����̸�
from employees e , employees m
where e.manager_id(+) = m.employee_id
order by 1;

select m.first_name �Ŵ����̸�, count(e.first_name) ��������
from employees m , employees e
where e.manager_id = m.employee_id  
group by m.first_name
having count(e.first_name)>=5;

select e.employee_id ���, e.first_name �����, m.first_name �Ŵ����̸�
from employees e, employees m
where e.manager_id = m.employee_id
union
select employee_id ���, first_name �����, nvl(to_char(manager_id), '0') �Ŵ����̸�
from employees
where manager_id is null;

select employee_id ���, first_name �����,job_id ����̵�
from employees 
minus
select employee_id ���, first_name �����, job_id ����̵�
from employees 
where lower( job_id) like '%clerk%';

select * from salgrade;
select grade from salgrade
where salary between lsal and hsal;

select grade from salgrade where 3500 between lsal and hsal;

select e.first_name �̸�, s.grade �޿����
from employees e, salgrade s
where e.salary between s.lsal and s.hsal
order by e.salary desc, e.first_name;

--100�� �μ��� ��� �޿�
select  avg(salary) "��� �޿�"
from employees
where department_id = 100;


select  avg(salary) from employees 
where department_id = (select department_id 
                                from employees
                                where lower(first_name) = 'daniel'
                                );
select department_id �μ����̵�, department_name �μ��� 
from departments 
where department_id = (select department_id
                                from employees
                                where salary = 8800);
                                
select department_id �μ����̵�, department_name �μ���
from departments
where department_id in (select department_id 
                                 from employees 
                                 where salary =8200);

select department_id �μ����̵�, min(salary) from employees group by department_id
order by department_id; --�μ��� ���� �ݿ�
                                    
select department_id �μ����̵�, first_name �̸�, salary �޿�
from employees 
where salary in (select min(salary) from employees group by department_id )
order by department_id;

SELECT first_name �����, department_id �μ����̵�, salary �޿�
FROM employees
WHERE (salary ,department_id) IN (SELECT MIN(salary),department_id
      FROM employees
      GROUP BY department_id)
ORDER BY department_id;

select e.first_name �̸�, j.job_title ��å, e.salary �޿�
from employees e, jobs j
where e.job_id = j.job_id;

select * from jobs;

select e.first_name �̸�, j.job_title ��å, e.salary �޿�
from employees e, jobs j
where e.job_id = j.job_id;

select e.first_name �̸�, j.job_title ��å, salary �޿�, decode(substr(j.job_title, -7) ,  'Manager', salary *1.1, salary * 1.3) "�λ�� �޿�"
from employees e, jobs j
where e.job_id = j.job_id;

select e.first_name �̸�, j.job_title ��å, salary �޿�, decode(j.job_title, '%Manager', salary *1.1, '%Clerk', salary *1.2, salary*1.3) "�λ�� �޿�"
from employees e, jobs j
where e.job_id = j.job_id;

select e.first_name �̸�, j.job_title ��å, salary �޿�, decode(substr(j.job_title,-7), 'Manager', salary *1.1,
                                                                decode(substr(j.job_title,-4), 'Clerk',salary *1.2, salary*1.3) )"�λ�� �޿�"
from employees e, jobs j
where e.job_id = j.job_id;
   
select e.first_name �̸�, j.job_title ��å, salary �޿�, decode(job_title, 'Accountant', salary*1.1, salary*1.2 ) "�λ�� �޿�"
from employees e, jobs j
where e.job_id = j.job_id;

SELECT e.first_name �̸�, j.job_title ��å, e.salary �޿�, DECODE(substr(j.job_title, -7), 'Manager', salary*1.1, 
DECODE(substr(j.job_title, -5), 'Clerk', salary*1.2, salary*1.3)) "�λ�� �޿�"
FROM employees e, jobs j
WHERE j.job_id = e.job_id;

select e.first_name �̸�, j.job_title ��å, e.salary �޿�, decode(substr(j.job_title,-7),'Manager',salary*1.1,
                decode(substr(j.job_title,-5),'Clerk', salary*1.2, salary*1.3)) "�λ�� �޿�"
from employees e, jobs j
where j.job_id = e.job_id;

select e.first_name �̸�, j.job_title ��å, salary �޿�,
case when substr(job_title, -7) = 'Manager' then salary *1.1
        when substr(job_title,-5) = 'Clerk' then salary*1.2
        else salary *1.3
    end as "�λ�� �޿�"
from employees e, jobs j
where e.job_id = j.job_id;

select e.first_name �̸�, j.job_title ��å, salary �޿�,
case when job_title like '%Manager' then salary *1.1
        when job_title like '%Clerk' then salary*1.2
        else salary *1.3
    end as "�λ�� �޿�"
from employees e, jobs j
where e.job_id = j.job_id;

select first_name �̸�, salary �޿�, hire_date �Ի��� , d.department_name �μ���
from employees e, departments d
where department_name = 'IT' and e.department_id = d.department_id;
    










