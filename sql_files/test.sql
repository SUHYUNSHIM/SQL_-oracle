select *
from
    (
    select substr(hire_date, 4,2) 입사월, count(*) 입사자수
    from employees
    group by substr(hire_date,4,2)
    order by count(*) asc
    )
 where rownum <=8;
 
 select first_name 이름, salary 급여, department_name 부서명
 from employees, departments
 where employees.department_id = departments.department_id;

select e.first_name 이름, e.manager_id 매니저아이디, d.location_id 지역아이디
from employees e, departments d
where e.department_id = d.department_id
order by e.first_name;

select e.first_name 이름, e.manager_id 매니저아이디, d.location_id 지역아이디
from employees e , departments d
where e.department_id = d.department_id and e.manager_id >=120
order by e.first_name;

select e.first_name 이름, j.job_title 직책
from employees e, jobs j
where e.job_id = j.job_id;

select e.first_name 직원이름, d.department_id 부서명,  l.city 도시
from employees e, locations l, departments d
where e.department_id = d.department_id and d.location_id = l.location_id
order by city desc;

select e.first_name 이름, c.country_id 국가명
from employees e, departments d,  locations l, countries c
where e.department_id = d.department_id and d.location_id = l.location_id
and l.country_id = c.country_id
order by first_name ;

select l.city 도시이름, count(*) "도시별 직원수"
from employees  e, locations l, departments d
where e.department_id = d.department_id and l.location_id = d.location_id
group by city;

select d.department_id 부서이름, l.city 도시명
from departments d, locations l 
where d.location_id = l.location_id;

select e.first_name 이름, j.job_title 직책 , d.department_id 부서명
from employees e, jobs j , departments d
where e.department_id = d.department_id and e.job_id = j.job_id and lower(j.job_title) like '%clerk%' ;

--and lower(j.job_title) ='clerk'

select e.first_name 이름, j.job_title 직책 , d.department_id 부서명
from employees e, jobs j , departments d
where e.department_id = d.department_id and e.job_id = j.job_id and lower(j.job_title) like '%representative%' ;

select e.first_name 이름, e.last_name 성, to_char(e.salary*12, '$999,999,999') 연봉, j.job_title 직책
from employees e , jobs j 
where e.job_id = j.job_id and salary between 10000 and 25000
order by salary desc;

select l.city 도시명, d.department_name 부서명, count(*) 인원수
from locations l, employees e , departments d
where e.department_id = d.department_id and d.location_id = l.location_id and substr(hire_date,1,2)<07
group by l.city, d.department_name
order by l.city, count(*) desc;

select e.first_name 이름, e.salary 급여 , d.department_name 부서명
from employees e, departments d
where e.department_id(+) = d.department_id;

select e.employee_id 사번, e.first_name 이름, e.last_name 성, e.hire_date 입사일 , nvl(d.department_name ,'그런부서없어') 부서명
from employees e left outer join  departments d
on (e.department_id = d.department_id)
where substr(e.hire_date,1,2) =07
order by last_name;

select e.employee_id 사번아이디, e.manager_id 매니저아이디, m.first_name 매니저이름
from employees e , employees m
where e.manager_id(+) = m.employee_id
order by 1;

select m.first_name 매니저이름, count(e.first_name) 관리직원
from employees m , employees e
where e.manager_id = m.employee_id  
group by m.first_name
having count(e.first_name)>=5;

select e.employee_id 사번, e.first_name 사원명, m.first_name 매니저이름
from employees e, employees m
where e.manager_id = m.employee_id
union
select employee_id 사번, first_name 사원명, nvl(to_char(manager_id), '0') 매니저이름
from employees
where manager_id is null;

select employee_id 사번, first_name 사원명,job_id 잡아이디
from employees 
minus
select employee_id 사번, first_name 사원명, job_id 잡아이디
from employees 
where lower( job_id) like '%clerk%';

select * from salgrade;
select grade from salgrade
where salary between lsal and hsal;

select grade from salgrade where 3500 between lsal and hsal;

select e.first_name 이름, s.grade 급여등급
from employees e, salgrade s
where e.salary between s.lsal and s.hsal
order by e.salary desc, e.first_name;

--100번 부서의 평균 급여
select  avg(salary) "평균 급여"
from employees
where department_id = 100;


select  avg(salary) from employees 
where department_id = (select department_id 
                                from employees
                                where lower(first_name) = 'daniel'
                                );
select department_id 부서아이디, department_name 부서명 
from departments 
where department_id = (select department_id
                                from employees
                                where salary = 8800);
                                
select department_id 부서아이디, department_name 부서명
from departments
where department_id in (select department_id 
                                 from employees 
                                 where salary =8200);

select department_id 부서아이디, min(salary) from employees group by department_id
order by department_id; --부서별 최저 금여
                                    
select department_id 부서아이디, first_name 이름, salary 급여
from employees 
where salary in (select min(salary) from employees group by department_id )
order by department_id;

SELECT first_name 사원명, department_id 부서아이디, salary 급여
FROM employees
WHERE (salary ,department_id) IN (SELECT MIN(salary),department_id
      FROM employees
      GROUP BY department_id)
ORDER BY department_id;

select e.first_name 이름, j.job_title 직책, e.salary 급여
from employees e, jobs j
where e.job_id = j.job_id;

select * from jobs;

select e.first_name 이름, j.job_title 직책, e.salary 급여
from employees e, jobs j
where e.job_id = j.job_id;

select e.first_name 이름, j.job_title 직책, salary 급여, decode(substr(j.job_title, -7) ,  'Manager', salary *1.1, salary * 1.3) "인상된 급여"
from employees e, jobs j
where e.job_id = j.job_id;

select e.first_name 이름, j.job_title 직책, salary 급여, decode(j.job_title, '%Manager', salary *1.1, '%Clerk', salary *1.2, salary*1.3) "인상된 급여"
from employees e, jobs j
where e.job_id = j.job_id;

select e.first_name 이름, j.job_title 직책, salary 급여, decode(substr(j.job_title,-7), 'Manager', salary *1.1,
                                                                decode(substr(j.job_title,-4), 'Clerk',salary *1.2, salary*1.3) )"인상된 급여"
from employees e, jobs j
where e.job_id = j.job_id;
   
select e.first_name 이름, j.job_title 직책, salary 급여, decode(job_title, 'Accountant', salary*1.1, salary*1.2 ) "인상된 급여"
from employees e, jobs j
where e.job_id = j.job_id;

SELECT e.first_name 이름, j.job_title 직책, e.salary 급여, DECODE(substr(j.job_title, -7), 'Manager', salary*1.1, 
DECODE(substr(j.job_title, -5), 'Clerk', salary*1.2, salary*1.3)) "인상된 급여"
FROM employees e, jobs j
WHERE j.job_id = e.job_id;

select e.first_name 이름, j.job_title 직책, e.salary 급여, decode(substr(j.job_title,-7),'Manager',salary*1.1,
                decode(substr(j.job_title,-5),'Clerk', salary*1.2, salary*1.3)) "인상된 급여"
from employees e, jobs j
where j.job_id = e.job_id;

select e.first_name 이름, j.job_title 직책, salary 급여,
case when substr(job_title, -7) = 'Manager' then salary *1.1
        when substr(job_title,-5) = 'Clerk' then salary*1.2
        else salary *1.3
    end as "인상된 급여"
from employees e, jobs j
where e.job_id = j.job_id;

select e.first_name 이름, j.job_title 직책, salary 급여,
case when job_title like '%Manager' then salary *1.1
        when job_title like '%Clerk' then salary*1.2
        else salary *1.3
    end as "인상된 급여"
from employees e, jobs j
where e.job_id = j.job_id;

select first_name 이름, salary 급여, hire_date 입사일 , d.department_name 부서명
from employees e, departments d
where department_name = 'IT' and e.department_id = d.department_id;
    










