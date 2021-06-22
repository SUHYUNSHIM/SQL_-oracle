select first_name 이름, salary 급여, hire_date 입사일 , d.department_name 부서명
from employees e, departments d
where department_name = 'IT' and e.department_id = d.department_id;

----복습 문제
select e.first_name 이름, d.department_name 부서명, e.salary 급여
from employees e, departments d
where e.department_id = d.department_id and
(salary, d.department_id) in (select max(salary), department_id from employees
group by department_id);

SELECT e.first_name 이름, d.department_name 부서명, e.salary 급여
FROM employees e, departments d
WHERE d.department_id = e.department_id
AND (e.department_id, e.salary) IN (SELECT department_id, max(salary)
                FROM employees
                GROUP BY department_id);
--문제           
select e.first_name 이름, e.salary 급여, d.department_name 부서명
from employees e, departments d
where e.department_id = d.department_id and 
salary > (select avg(salary) from employees where department_id = 30) and
salary < (select max(e.salary) from employees e, departments d, locations l 
             where e.department_id = d.department_id and d.location_id = l.location_id and l.city='Seattle')
order by salary desc;

-----------------------------------------------
select e.first_name 이름,d.department_name 부서명,e.salary 급여
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
select first_name 이름, department_id 부서id, (select min(salary), department_id from employees group by department_id)
최소급여 
from employees;

select first_name 이름, department_id 부서아이디, salary 급여, 
(select min(salary) from employees where department_id = e.department_id) 최소급여
from employees e
order by department_id;

--서브 쿼리, 테이블처럼
select e.first_name 사원명, e.salary 급여, a.avgsal 평균급여
from employees e, (select department_id, round(avg(salary)) avgsal
                            from employees
                            group by department_id) a
where e.department_id = a.department_id
and e.salary >a.avgsal
order by 2 desc;

--
select e.first_name 이름, e.salary 급여
from employees e, (select salary from employees order by salary desc) s
where e.salary = s.salary;

select rownum, first_name 이름, salary 급여
from (select first_name, salary from employees order by salary desc);

--커미션을 가장 많이 받는 상위 3명의 이름과 커미션을 조회출력 하시오.
select first_name 이름, commission_pct 커미션
from (select first_name, commission_pct from employees where commission_pct is not null order by commission_pct desc)
where rownum <=3;

select first_name 이름, salary 급여, rownum 등수
from (select first_name , salary  from employees order by salary desc)
where rownum =1 ;

select a.* , rownum 등수
from (select first_name, salary from employees order by salary_desc) a
where rownum =1;

select a.*,rownum 등수 
from (select first_name, salary from employees order by salary desc) a;

select a.*, dense_rank() over (order by salary desc) 등수
from (select first_name, salary from employees order by salary desc) a;

select a.*
from (Select first_name 이름, salary 급여, rank() over (order by salary desc ) 등수
        from employees )a
where rownum <=10;

SELECT A.*
FROM (SELECT department_id 부서아이디, round(avg(salary)) "부서의 평균급여"
        FROM employees
        GROUP BY department_id) A
WHERE A."부서의 평균급여"< (SELECT round(avg(salary))
                        FROM employees
                        WHERE department_id = 80);
                        
select department_id 부서아이디, round(avg(salary)) 평균급여
from employees
group by department_id
having avg(salary) < (select round(avg(salary))
                            from employees
                            where department_id =80)
order by department_id; 
    
--직책에 대한 평균 급여 중 가장 큰 평균급여에 대한 직책과 해당 평균 급여를 조회출력하시오.
select j.job_title 직책, round(avg(e.salary)) 급여
from employees e, jobs j
where e.job_id = j.job_id
group by j.job_title
having (round(avg(salary))) in (select max(avg(salary)) from employees  group by job_id);

--100번 부서의 평균 급여보다 큰 부서의 평균 급여와 부서 아이디 출력
select  department_id 부서아이디, round(avg(salary)) 평균급여 
from employees
group by department_id
having avg(salary) >  (select avg(salary) department_id 
                            from employees
                            where department_id =100); 
--job_id 별로 최대 봉급을 받는 사원의 이름, 봉급, 부서명을 출력
select e.first_name 이름  , e.salary 봉급, d.department_name 부서명 
from employees e, deparements d 
where e.department_id = d.department_id 
and (salary, job_id) in (select max(salary) from employees group by job_id);




                








