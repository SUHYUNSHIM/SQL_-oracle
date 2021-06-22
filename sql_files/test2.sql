select first_name �̸�, salary �޿�, hire_date �Ի��� , d.department_name �μ���
from employees e, departments d
where department_name = 'IT' and e.department_id = d.department_id;

----���� ����
select e.first_name �̸�, d.department_name �μ���, e.salary �޿�
from employees e, departments d
where e.department_id = d.department_id and
(salary, d.department_id) in (select max(salary), department_id from employees
group by department_id);

SELECT e.first_name �̸�, d.department_name �μ���, e.salary �޿�
FROM employees e, departments d
WHERE d.department_id = e.department_id
AND (e.department_id, e.salary) IN (SELECT department_id, max(salary)
                FROM employees
                GROUP BY department_id);
--����           
select e.first_name �̸�, e.salary �޿�, d.department_name �μ���
from employees e, departments d
where e.department_id = d.department_id and 
salary > (select avg(salary) from employees where department_id = 30) and
salary < (select max(e.salary) from employees e, departments d, locations l 
             where e.department_id = d.department_id and d.location_id = l.location_id and l.city='Seattle')
order by salary desc;

-----------------------------------------------
select e.first_name �̸�,d.department_name �μ���,e.salary �޿�
from employees e, departments d
where e.department_id=d.department_id
and salary > (select avg(salary)
                    from employees e 
                    where department_id=30)
and salary < (select max(salary)
                    from employees e, departments d , locations l
                    where e.department_id=d.department_id
                    and d.location_id=l.location_id
                    and l.city like 'Seattle')
order by 3 desc;

----------------------------
select first_name �̸�, department_id �μ�id, (select min(salary), department_id from employees group by department_id)
�ּұ޿� 
from employees;

select first_name �̸�, department_id �μ����̵�, salary �޿�, 
(select min(salary) from employees where department_id = e.department_id) �ּұ޿�
from employees e
order by department_id;

--���� ����, ���̺�ó��
select e.first_name �����, e.salary �޿�, a.avgsal ��ձ޿�
from employees e, (select department_id, round(avg(salary)) avgsal
                            from employees
                            group by department_id) a
where e.department_id = a.department_id
and e.salary >a.avgsal
order by 2 desc;

--
select e.first_name �̸�, e.salary �޿�
from employees e, (select salary from employees order by salary desc) s
where e.salary = s.salary;

select rownum, first_name �̸�, salary �޿�
from (select first_name, salary from employees order by salary desc);

--Ŀ�̼��� ���� ���� �޴� ���� 3���� �̸��� Ŀ�̼��� ��ȸ��� �Ͻÿ�.
select first_name �̸�, commission_pct Ŀ�̼�
from (select first_name, commission_pct from employees where commission_pct is not null order by commission_pct desc)
where rownum <=3;

select first_name �̸�, salary �޿�, rownum ���
from (select first_name , salary  from employees order by salary desc)
where rownum =1 ;

select a.* , rownum ���
from (select first_name, salary from employees order by salary_desc) a
where rownum =1;

select a.*,rownum ��� 
from (select first_name, salary from employees order by salary desc) a;

select a.*, dense_rank() over (order by salary desc) ���
from (select first_name, salary from employees order by salary desc) a;

select a.*
from (Select first_name �̸�, salary �޿�, rank() over (order by salary desc ) ���
        from employees )a
where rownum <=10;

SELECT A.*
FROM (SELECT department_id �μ����̵�, round(avg(salary)) "�μ��� ��ձ޿�"
        FROM employees
        GROUP BY department_id) A
WHERE A."�μ��� ��ձ޿�"< (SELECT round(avg(salary))
                        FROM employees
                        WHERE department_id = 80);
                        
select department_id �μ����̵�, round(avg(salary)) ��ձ޿�
from employees
group by department_id
having avg(salary) < (select round(avg(salary))
                            from employees
                            where department_id =80)
order by department_id; 
    
--��å�� ���� ��� �޿� �� ���� ū ��ձ޿��� ���� ��å�� �ش� ��� �޿��� ��ȸ����Ͻÿ�.
select j.job_title ��å, round(avg(e.salary)) �޿�
from employees e, jobs j
where e.job_id = j.job_id
group by j.job_title
having (round(avg(salary))) in (select max(avg(salary)) from employees  group by job_id);

--100�� �μ��� ��� �޿����� ū �μ��� ��� �޿��� �μ� ���̵� ���
select  department_id �μ����̵�, round(avg(salary)) ��ձ޿� 
from employees
group by department_id
having avg(salary) >  (select avg(salary) department_id 
                            from employees
                            where department_id =100); 
--job_id ���� �ִ� ������ �޴� ����� �̸�, ����, �μ����� ���
select e.first_name �̸�  , e.salary ����, d.department_name �μ��� 
from employees e, deparements d 
where e.department_id = d.department_id 
and (salary, job_id) in (select max(salary) from employees group by job_id);




                








