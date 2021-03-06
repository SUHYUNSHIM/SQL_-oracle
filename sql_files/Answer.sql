--1번
select first_name 이름, hire_date 입사일
from employees
where hire_date like '%17';
--2번
select first_name 이름, hire_date 입사일
from employees 
where hire_date >= '03/01/01' and hire_date <= '03/12/31';
where hire_date like '03/%/%';
where hire_date like '03%';

--2005년에 입사한 사원 출력
select first_name 이름, hire_date 입사일
from employees
where to_char(hire_date, 'yy') ='05';
--3번
select first_name 이름
from employees
where department_id is null;
--4번
select employee_id 사원번호, first_name 이름
from employees
where employee_id != 105;
where employee_id <> 105;

--5번
select initcal('Nct iN the HOUse')
from dual;

--6번
select round(36.754,1) from dual;
--7번
select months_between(sysdate,'17/11/18') from dual;
select trunc(months_between(sysdate,'17/11/18')) from dual;

--8번
select next_day(sysdate, '목') from dual;
select next_day(sysdate, 5) from dual;

select to_char(1234567, '$999,999,999') from dual;

--9번
select department_id 부서id, max(salary) 최대급여, min(salary) 최소급여, avg(salary) 평균급여
,sum(salary) 합계급여, count(*) 인원수
from employees
group by department_id
having max(salary) != min(salary)
order by 1;
--having max(salary) <> min(salary)

--10번.
select *
from (
    select substr(hire_date,4,2) 입사월, count(*) 입사자수
    from employees 
    group by substr(hire_date, 4,2)
    order by count(*) asc
    )
    where rownum <=8;

--11.
select e.first_name 이름, e.salary 급여, d.department_name 부서명
from employees e, departments d
where e.department_id(+) = d.department_id;

select e.first_name 이름, e.salary 급여, d.department_name 부서명
from employees e left outer join departments d
on (e.department_id = d.department_id);

--12.
select e.employee_id 사번아이디, e.manager_id 매니저아이디, m.first_name 매니저이름
from employees e, employees m
where e.manager_id = m.employee_id(+)
order by 1;

select a.*
from(select substr(hire_date,4,2) || '월' 입사월, count(*) || '명' 입사자수
        from employees
        group by substr(hire_date, 4,2)
        order by count(*) asc) a
        where rownum <=8;

--13번.
select avg(salary) from emlpoyees
where department_id = (select department_id 
                                from employees 
                                where lower(first_name) ='daniel');

--14번.
select first_name 사원명, department_id 부서아이디, salary 급여
from employees
where (department_id, salary) in (select department_id, min(salary) 
                                            from employees
                                            group by department_id)
order by department_id;

--15.
select e.first_name 이름, j.job_title 직책, salary 급여,
case when substr(job_title,-7) = 'Manager' then salary *1.1
       when substr(job_title, -5) = 'Clerk' then salary *1.2
       else salary *1.3
end as 인상된 급여
from employees e, jobs j
where e.job_id = j.job_id;

--16.
select e.first_name 이름, d.department_name 부서명, e.salary 급여
from employees e, departments d
where e.department_id = d.department_id
and (d.department_id,e.salary) in (select department_id, max(salary)
                                            from employees
                                            group by department_id
                                            );
                                            
--17.
select first_name 이름, department_id 부서아이디, salary 급여,
(select min(salary) from employees where department_id = e.department_id) 최소급여
from employees e
order by department_id;

--18.
select e.first_name 사원명, e.salary 급여, a.avgsal 평균급여
from employees e, (select department_id, round(avg(salary)) avgsal
                            from employees
                            group by department_id) a
where e.department_id =a.department_id
and e.salary > a.avgsal
order by 2 desc;

--19.
select first_name 이름, commission_pct 커미션
from (select first_name, commission_pct 
        from employees
        where commission_pct is not null
        order by commission_pct desc)
where rownum <=3; 

--20.
select a.*
from (select first_name 이름, salary 급여, rank() over (order by salary desc) 등수 from employees) a;

--21.
select job_id , avg(salary)
from employees
group by job_id
having avg(salary) = (select max(avg(salary))
                             from employees
                            group by job_id);
--22.
create table employees_s
as select first_name 이름, phone_number 전화번호, department_id 부서아이디
    from employees;


--23.
update employees
set department_id =(select department_id
                            from employees
                            where first_name = 'Daniel')
where first_name = 'Alexandar';































