select first_name, last_name from employees;
select employee_id, salary, job_id, phone_number,email from employees;
select "�� ��õ����" from employees;
select first_name as �̸� , '����� �̸�' from employees;

select first_name  �̸�, salary*12*1.2  "���� ����" from employees;

select last_name ||  '  ' || first_name ���� from employees;

select salary ||'�޷�'  as �޿� from employees;

select first_name ||'�� ������ '|| salary ||' ���� ���Դϴ�' from employees;

select department_id �μ����̵�, department_name �μ��� from departments;
select first_name �̸�, salary �޿� from employees where salary >=10000;

select first_name �̸�, hire_date �Ի���, department_id �μ���ȣ from employees where  employee_id = '105';

select first_name �̸�, salary ���� from employees where salary > = 150000;

select first_name �̸�, department_id �μ�id, salary �޿�
from employees
where department_id in (90,80,50);

select distinct department_id as "�μ� ���̵�" from employees;

select first_name �̸�, salary �޿�
from employees
where salary > = 5000 and salary<=10000;

select first_name �̸�, salary �޿�
from employees
where salary between 5000 and 10000;

select first_name �̸�, nvl(to_char(commission_pct),'����') Ŀ�̼�
from employees;

select employee_id �����ȣ, first_name �̸� 
from employees
order by salary asc;

select employee_id �����ȣ, first_name �̸�
from employees
order by employee_id desc;

select first_name �̸�, salary �޿�
from employees
where employee_id <=110
order by salary desc;


select first_name �̸�, salary �޿�, hire_date �Ի���
from employees
where department_id = 80;

--90 or 80�� �μ��� ���������鼭 �޿��� 15000�̻��� ������ �̸�, �޿� ,�μ����̵� ����Ͻÿ�.
select first_name �̸�, salary �޿�, department_id �μ����̵�
from employees
where (department_id = 90 or department_id = 80) and salary>=15000;

--2003�� ���Ŀ� �Ի��� ������ �߿��� �޿��� 15000�̻� 20000������ �������� �̸�, �޿�, �Ի����� ����Ͻÿ�. ���迬���� ���.
select first_name �̸�, salary �޿�, hire_date �Ի���
from employees
where hire_date > '03/12/31' and salary>=15000 and salary <=20000;

--90 or 80�� �μ��� ���������鼭 �޿��� 1500 �̻��� �������� �̸�, �޿�, �Ի����� ����Ͻÿ�. or ���� 
select first_name �̸�, salary �޿�, department_id �μ����̵�
from employees
where department_id in( 90 , 80) and salary>=15000;

--2003�� ���Ŀ� �Ի��� ������ �߿��� �޿��� 15000�̻� 2000������ �������� �̸�, �޿�, �Ի����� ���.salary���� ���迬���� ����
select first_name �̸�, salary �޿�, hire_date �Ի���
from employees
where hire_date > '03/12/31' and salary between 15000 and 20000;

--�̸��� na�� �� ������ �̸�, �޿��� ����Ͻÿ�.
select first_name �̸�, salary �̸�
from employees
where first_name like '%na%';

--�̸��� 3��° ���ڰ� a�� ������ �̸�, �޿��� ����Ͻÿ�.
select first_name �̸�, salary �޿�
from employees
where first_name like '__a%';

--2007�⿡ �Ի�, 30,60,80�� �μ��� ���� �ִ�.
-- �޿��� 500~ 2000���ϸ� �޴´�.
--Ŀ�̼��� �޴� �����̾�� �Ѵ�. ���� �ʴ� ������ ����
--���� �Ի��� ���� ����-> �Ի�� order
--�Ի��� ���� �޿� �� ������������ ���
select first_name �̸�, hire_date �Ի��� , department_id �μ���ȣ, salary �޿�, commission_pct Ŀ�̼��ۼ�Ʈ
from employees
where hire_date like '07%'
and department_id in(30,60,80) 
and salary between 5000 and 20000 
and commission_pct is not null
order by hire_date asc, salary desc;

select first_name "����� �̸�" , last_name ��, salary �޿�
from employees
where lower(last_name) = 'king';

--ù���ڸ� �빮��
select initcap('I will live my life for SQL')
from dual;

--�Ի�⵵�� 05�⵵�� ������ ������ ���
SELECT first_name "��� �̸�", last_name ��, salary �޿�,hire_date �Ի���
FROM employees
WHERE substr(hire_date,1,2) >= '05' and substr(hire_date,1,2)<'06' ;

SELECT first_name "��� �̸�", last_name ��, salary �޿�,hire_date �Ի���
FROM employees
WHERE substr(hire_date,1,2) = '05';

SELECT first_name "��� �̸�", last_name ��, salary �޿�,hire_date �Ի���
FROM employees
WHERE hire_date >= '2005/01/01' and hire_date <='2005/12/31' ;

--�̸��� nancy�� ������ �޿��� �Ի���
SELECT salary �޿� , hire_date �Ի��� 
FROM employees
WHERE lower(first_name) ='nancy';

--�������� ~ �� ������ �����ڵ�� ������
select country_id �����ڵ�, country_name ������
from countries
where country_name = initcap( 'united States oF america'); 

select initcap('united States oF america')
from dual;

select first_name �̸� , salary �޿�
from employees
where length(first_name)<=7;

select first_name �̸�, lpad(salary,10,'*')�޿�
from employees;

select replace('2021-06-07','-' ,'/') from dual;
select translate('apple','p','b') from dual;

select replace('apple1234','abcdefghijklmnopqrstuvwxyz0123456789','wwwwwwwwwwwwwwwwwwwwwwwwww0000000000')
from dual; -- �ش� ���ڿ��� �״�� ���� ������, apple1234�� �״�� ��µȴ�.
select translate('apple1234','abcdefghijklmnopqrstuvwxyz0123456789','wwwwwwwwwwwwwwwwwwwwwwwwww0000000000')
from dual; --���� ����. �ϳ��ϳ� �����Ǿ ��ü�ȴ�. wwwww0000�� ��µȴ�.

select first_name �̸�, email �̸���
from employees
where translate(email,'0123456789', 'pppppppppp') <>email; --�̸��Ͽ� ���ڰ� ������ p�� �ٲٰ� ������ �Ͱ� ������ ����.
--�� ���̺��� �̸��Ͽ��� ���ڰ� �����Ƿ� ���� �̸��ϰ� �޶��� ���� ���� ������, �ƹ��͵� ��µ��� �ʴ´�. 

select first_name �̸�, email �̸���
from employees
where translate(email,'0123456789', 'pppppppppp') =email;

select to_char(sysdate,'yyyy/mm/dd') from dual;

select first_name ����̸�, manager_id "�Ŵ��� ���̵�" from employees;
select first_name ����̸�, nvl(to_char(manager_id),'�Ŵ�������')
from employees;

select avg(salary) from employees; -- max, min

select  department_id �μ����̵�, min(salary) �ּұ޿�
 from employees
 where department_id >= 30 
 group by department_id
 having avg(salary)<=10000
 order by 1;
 
 select department_id �μ����̵�, count(first_name) ������, round(avg(salary)) ��ձ޿�
 from employees
 group by department_id
 having count(first_name) >=20
 order by 1;
 
 select  department_id �μ����̵�, count(first_name)
 from employees
 group by department_id;
-------------------------------------���� 
select department_id �μ����̵�, count(*)
from employees
group by department_id
having count(*)>=3;

select department_id �μ����̵�, avg(salary)
from employees
where department_id in (30,90) and salary between 5000 and 12000
group by department_id
having avg(salary) >=7500
order by avg(salary) desc;

select department_id �μ����̵�, max(salary)*12 �ִ뿬��,  round(avg(salary) *12) ��տ��� 
from employees
group by department_id
having sum(salary) >=20000;

 SELECT nvl(to_char(department_id),'����') �μ����̵�, MAX(salary) �ִ�޿�, MIN(salary) �ּұ޿�,
round(AVG(salary)) ��ձ޿�, SUM(salary) �޿��հ�, COUNT(*) ������
FROM employees
GROUP BY department_id
ORDER BY 1;

select department_id �μ�id, max(salary) �ִ�޿�,
min(salary) �ּұ޿�, avg(salary) ��ձ޿�,
sum(salary) �հ�޿�, count(*) �ο���
from employees
group by department_id
having max(salary) != min(salary)
order by 1;

select substr(hire_date,1,2) ||'��' �Ի�⵵, count(*) ||'��' ������
from employees
group by substr(hire_date,1,2)
having count(*) >=8
order by count(*) desc;

select substr(hire_date,4,2) �Ի��, count(first_name) ��
from employees 
where rownum <=8
group by substr(hire_date,4,2)
order by count(first_name) asc;



select substr(hire_date,4,2) �Ի�� , count(first_name) "�Ի��� ��"
from employees
where rownum <=8
group by substr(hire_date,4,2)
order by count(*) asc ;

SELECT TO_CHAR(hire_date, 'mm') || '��' "�� ��", hire_date �Ի���, employee_id ���, first_name �̸�, department_id �μ���, count(*) || '��' �Ի��ڼ�
FROM employees
WHERE rownum <= 8
GROUP BY TO_CHAR(hire_date, 'mm'), hire_date, employee_id, first_name, department_id
ORDER BY �Ի��ڼ�;















