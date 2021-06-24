select first_name 이름, hire_date 입사일
from employees
where hire_date  like '%17'; 

select months_between (sysdate,'2017/11/18') from dual;

select a.*
from (select max(salary) 최대급여, min(salary) 최소급여, avg(salary) 평균급여 ,sum(salary) "급여 합계", count(*) 인원수
        from employees
        group by department_id) a;
        
select e.first_name 이름, e.salary 급여, d.department_name 부서명
from employees e left outer join departments d
on (e.department_id = d.department_id);

select e.first_name 이름, d.department_name 부서명, e.salary 급여
from employees e, departments d 
where (department_name, salary) in (select d.department_name 부서명, max(e.salary) 최대급여 
                                                from employees e, departments d 
                                                where e.department_id = d.department_id
                                                group by department_name);


--문제1
select first_name 이름, nvl(to_char(department_id),'부서없음') 부서아이디, salary 급여
from employees
where salary between 5000 and 10000 and commission_pct is not null
order by salary desc;

--문제2
select substr(hire_date,1,2) ||'년' 입사년도, count(*)  ||'명' 사원수
from employees
group by substr(hire_date,1,2)
order by count(*) desc;

--문제3
select first_name 이름, salary 급여, job_title 직책
from employees e, jobs j
where e.job_id = j.job_id and job_title like '%Ma%'
and salary >=10000
order by salary;

--문제4
select e.first_name 이름, d.department_name 부서명, c.country_name 국가명
from employees e , departments d, locations l, countries c
where  e.department_id = d.department_id and d.location_id = l.location_id and l.country_id = c.country_id
union all
select e.first_name 이름, ' '부서명, ' '국가명
from employees e
where e.department_id is null;

select department_id from employees where first_name = 'Kimberely';
select * from departments;

--e.department_id = d.department_id(+) and

select e.first_name 이름, d.department_name 부서명, c.country_name 국가명
from employees e , departments d, locations l, countries c
where  e.department_id= d.department_id(+) and d.location_id = l.location_id and l.country_id = c.country_id
order by department_name, country_name  desc nulls last;


