--1��
select first_name �̸�, hire_date �Ի���
from employees
where hire_date like '%17';
--2��
select first_name �̸�, hire_date �Ի���
from employees 
where hire_date >= '03/01/01' and hire_date <= '03/12/31';
where hire_date like '03/%/%';
where hire_date like '03%';

--2005�⿡ �Ի��� ��� ���
select first_name �̸�, hire_date �Ի���
from employees
where to_char(hire_date, 'yy') ='05';
--3��
select first_name �̸�
from employees
where department_id is null;
--4��
select employee_id �����ȣ, first_name �̸�
from employees
where employee_id != 105;
where employee_id <> 105;

--5��
select initcal('Nct iN the HOUse')
from dual;

--6��
select round(36.754,1) from dual;
--7��
select months_between(sysdate,'17/11/18') from dual;
select trunc(months_between(sysdate,'17/11/18')) from dual;

--8��
select next_day(sysdate, '��') from dual;
select next_day(sysdate, 5) from dual;

select to_char(1234567, '$999,999,999') from dual;

--9��
select department_id �μ�id, max(salary) �ִ�޿�, min(salary) �ּұ޿�, avg(salary) ��ձ޿�
,sum(salary) �հ�޿�, count(*) �ο���
from employees
group by department_id
having max(salary) != min(salary)
order by 1;
--having max(salary) <> min(salary)

--10��.
select *
from (
    select substr(hire_date,4,2) �Ի��, count(*) �Ի��ڼ�
    from employees 
    group by substr(hire_date, 4,2)
    order by count(*) asc
    )
    where rownum <=8;

--11.
select e.first_name �̸�, e.salary �޿�, d.department_name �μ���
from employees e, departments d
where e.department_id(+) = d.department_id;

select e.first_name �̸�, e.salary �޿�, d.department_name �μ���
from employees e left outer join departments d
on (e.department_id = d.department_id);

--12.
select e.employee_id ������̵�, e.manager_id �Ŵ������̵�, m.first_name �Ŵ����̸�
from employees e, employees m
where e.manager_id = m.employee_id(+)
order by 1;

select a.*
from(select substr(hire_date,4,2) || '��' �Ի��, count(*) || '��' �Ի��ڼ�
        from employees
        group by substr(hire_date, 4,2)
        order by count(*) asc) a
        where rownum <=8;

--13��.
select avg(salary) from emlpoyees
where department_id = (select department_id 
                                from employees 
                                where lower(first_name) ='daniel');

--14��.
select first_name �����, department_id �μ����̵�, salary �޿�
from employees
where (department_id, salary) in (select department_id, min(salary) 
                                            from employees
                                            group by department_id)
order by department_id;

--15.
select e.first_name �̸�, j.job_title ��å, salary �޿�,
case when substr(job_title,-7) = 'Manager' then salary *1.1
       when substr(job_title, -5) = 'Clerk' then salary *1.2
       else salary *1.3
end as �λ�� �޿�
from employees e, jobs j
where e.job_id = j.job_id;

--16.
select e.first_name �̸�, d.department_name �μ���, e.salary �޿�
from employees e, departments d
where e.department_id = d.department_id
and (d.department_id,e.salary) in (select department_id, max(salary)
                                            from employees
                                            group by department_id
                                            );
                                            
--17.
select first_name �̸�, department_id �μ����̵�, salary �޿�,
(select min(salary) from employees where department_id = e.department_id) �ּұ޿�
from employees e
order by department_id;

--18.
select e.first_name �����, e.salary �޿�, a.avgsal ��ձ޿�
from employees e, (select department_id, round(avg(salary)) avgsal
                            from employees
                            group by department_id) a
where e.department_id =a.department_id
and e.salary > a.avgsal
order by 2 desc;

--19.
select first_name �̸�, commission_pct Ŀ�̼�
from (select first_name, commission_pct 
        from employees
        where commission_pct is not null
        order by commission_pct desc)
where rownum <=3; 

--20.
select a.*
from (select first_name �̸�, salary �޿�, rank() over (order by salary desc) ��� from employees) a;

--21.
select job_id , avg(salary)
from employees
group by job_id
having avg(salary) = (select max(avg(salary))
                             from employees
                            group by job_id);
--22.
create table employees_s
as select first_name �̸�, phone_number ��ȭ��ȣ, department_id �μ����̵�
    from employees;


--23.
update employees
set department_id =(select department_id
                            from employees
                            where first_name = 'Daniel')
where first_name = 'Alexandar';































