select first_name �̸�, hire_date �Ի���
from employees
where hire_date  like '%17'; 

select months_between (sysdate,'2017/11/18') from dual;

select a.*
from (select max(salary) �ִ�޿�, min(salary) �ּұ޿�, avg(salary) ��ձ޿� ,sum(salary) "�޿� �հ�", count(*) �ο���
        from employees
        group by department_id) a;
        
select e.first_name �̸�, e.salary �޿�, d.department_name �μ���
from employees e left outer join departments d
on (e.department_id = d.department_id);

select e.first_name �̸�, d.department_name �μ���, e.salary �޿�
from employees e, departments d 
where (department_name, salary) in (select d.department_name �μ���, max(e.salary) �ִ�޿� 
                                                from employees e, departments d 
                                                where e.department_id = d.department_id
                                                group by department_name);


--����1
select first_name �̸�, nvl(to_char(department_id),'�μ�����') �μ����̵�, salary �޿�
from employees
where salary between 5000 and 10000 and commission_pct is not null
order by salary desc;

--����2
select substr(hire_date,1,2) ||'��' �Ի�⵵, count(*)  ||'��' �����
from employees
group by substr(hire_date,1,2)
order by count(*) desc;

--����3
select first_name �̸�, salary �޿�, job_title ��å
from employees e, jobs j
where e.job_id = j.job_id and job_title like '%Ma%'
and salary >=10000
order by salary;

--����4
select e.first_name �̸�, d.department_name �μ���, c.country_name ������
from employees e , departments d, locations l, countries c
where  e.department_id = d.department_id and d.location_id = l.location_id and l.country_id = c.country_id
union all
select e.first_name �̸�, ' '�μ���, ' '������
from employees e
where e.department_id is null;

select department_id from employees where first_name = 'Kimberely';
select * from departments;

--e.department_id = d.department_id(+) and

select e.first_name �̸�, d.department_name �μ���, c.country_name ������
from employees e , departments d, locations l, countries c
where  e.department_id= d.department_id(+) and d.location_id = l.location_id and l.country_id = c.country_id
order by department_name, country_name  desc nulls last;


