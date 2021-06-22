select first_name, last_name from employees;
select employee_id, salary, job_id, phone_number,email from employees;
select "나 얼굴천재라는" from employees;
select first_name as 이름 , '우아한 이름' from employees;

select first_name  이름, salary*12*1.2  "내년 연봉" from employees;

select last_name ||  '  ' || first_name 성명 from employees;

select salary ||'달러'  as 급여 from employees;

select first_name ||'의 봉급은 '|| salary ||' 이지 말입니다' from employees;

select department_id 부서아이디, department_name 부서명 from departments;
select first_name 이름, salary 급여 from employees where salary >=10000;

select first_name 이름, hire_date 입사일, department_id 부서번호 from employees where  employee_id = '105';

select first_name 이름, salary 연봉 from employees where salary > = 150000;

select first_name 이름, department_id 부서id, salary 급여
from employees
where department_id in (90,80,50);

select distinct department_id as "부서 아이디" from employees;

select first_name 이름, salary 급여
from employees
where salary > = 5000 and salary<=10000;

select first_name 이름, salary 급여
from employees
where salary between 5000 and 10000;

select first_name 이름, nvl(to_char(commission_pct),'없어') 커미션
from employees;

select employee_id 사원번호, first_name 이름 
from employees
order by salary asc;

select employee_id 사원번호, first_name 이름
from employees
order by employee_id desc;

select first_name 이름, salary 급여
from employees
where employee_id <=110
order by salary desc;


select first_name 이름, salary 급여, hire_date 입사일
from employees
where department_id = 80;

--90 or 80번 부서에 속해있으면서 급여가 15000이상인 직원의 이름, 급여 ,부서아이디를 출력하시오.
select first_name 이름, salary 급여, department_id 부서아이디
from employees
where (department_id = 90 or department_id = 80) and salary>=15000;

--2003년 이후에 입사한 직원들 중에서 급여가 15000이상 20000이하인 직원들의 이름, 급여, 입사일을 출력하시오. 관계연산자 사용.
select first_name 이름, salary 급여, hire_date 입사일
from employees
where hire_date > '03/12/31' and salary>=15000 and salary <=20000;

--90 or 80번 부서에 속해있으면서 급여가 1500 이상인 직원들의 이름, 급여, 입사일을 출력하시오. or 없이 
select first_name 이름, salary 급여, department_id 부서아이디
from employees
where department_id in( 90 , 80) and salary>=15000;

--2003년 이후에 입사한 직원들 중에서 급여가 15000이상 2000이하인 직원들의 이름, 급여, 입사일을 출력.salary에서 관계연산자 없이
select first_name 이름, salary 급여, hire_date 입사일
from employees
where hire_date > '03/12/31' and salary between 15000 and 20000;

--이름에 na가 들어간 직원의 이름, 급여를 출력하시오.
select first_name 이름, salary 이름
from employees
where first_name like '%na%';

--이름의 3번째 글자가 a인 직원의 이름, 급여를 출력하시오.
select first_name 이름, salary 급여
from employees
where first_name like '__a%';

--2007년에 입사, 30,60,80번 부서에 속해 있다.
-- 급여를 500~ 2000이하를 받는다.
--커미션을 받는 직원이어야 한다. 받지 않는 직원을 제외
--먼저 입사한 직원 순서-> 입사로 order
--입사일 이후 급여 순 내림차순으로 출력
select first_name 이름, hire_date 입사일 , department_id 부서번호, salary 급여, commission_pct 커미션퍼센트
from employees
where hire_date like '07%'
and department_id in(30,60,80) 
and salary between 5000 and 20000 
and commission_pct is not null
order by hire_date asc, salary desc;

select first_name "사원의 이름" , last_name 성, salary 급여
from employees
where lower(last_name) = 'king';

--첫글자만 대문자
select initcap('I will live my life for SQL')
from dual;

--입사년도가 05년도인 직원의 정보를 출력
SELECT first_name "사원 이름", last_name 성, salary 급여,hire_date 입사일
FROM employees
WHERE substr(hire_date,1,2) >= '05' and substr(hire_date,1,2)<'06' ;

SELECT first_name "사원 이름", last_name 성, salary 급여,hire_date 입사일
FROM employees
WHERE substr(hire_date,1,2) = '05';

SELECT first_name "사원 이름", last_name 성, salary 급여,hire_date 입사일
FROM employees
WHERE hire_date >= '2005/01/01' and hire_date <='2005/12/31' ;

--이름이 nancy인 직원의 급여와 입사일
SELECT salary 급여 , hire_date 입사일 
FROM employees
WHERE lower(first_name) ='nancy';

--국가명이 ~ 인 나라의 국가코드와 국가명
select country_id 국가코드, country_name 국가명
from countries
where country_name = initcap( 'united States oF america'); 

select initcap('united States oF america')
from dual;

select first_name 이름 , salary 급여
from employees
where length(first_name)<=7;

select first_name 이름, lpad(salary,10,'*')급여
from employees;

select replace('2021-06-07','-' ,'/') from dual;
select translate('apple','p','b') from dual;

select replace('apple1234','abcdefghijklmnopqrstuvwxyz0123456789','wwwwwwwwwwwwwwwwwwwwwwwwww0000000000')
from dual; -- 해당 문자열이 그대로 있지 않으니, apple1234가 그대로 출력된다.
select translate('apple1234','abcdefghijklmnopqrstuvwxyz0123456789','wwwwwwwwwwwwwwwwwwwwwwwwww0000000000')
from dual; --문자 단위. 하나하나 대응되어서 교체된다. wwwww0000가 출력된다.

select first_name 이름, email 이메일
from employees
where translate(email,'0123456789', 'pppppppppp') <>email; --이메일에 숫자가 있으면 p로 바꾸고 원래의 것과 같은지 본다.
--본 테이블의 이메일에는 숫자가 없으므로 기존 이메일과 달라진 것이 없기 때문에, 아무것도 출력되지 않는다. 

select first_name 이름, email 이메일
from employees
where translate(email,'0123456789', 'pppppppppp') =email;

select to_char(sysdate,'yyyy/mm/dd') from dual;

select first_name 사원이름, manager_id "매니저 아이디" from employees;
select first_name 사원이름, nvl(to_char(manager_id),'매니저없음')
from employees;

select avg(salary) from employees; -- max, min

select  department_id 부서아이디, min(salary) 최소급여
 from employees
 where department_id >= 30 
 group by department_id
 having avg(salary)<=10000
 order by 1;
 
 select department_id 부서아이디, count(first_name) 직원수, round(avg(salary)) 평균급여
 from employees
 group by department_id
 having count(first_name) >=20
 order by 1;
 
 select  department_id 부서아이디, count(first_name)
 from employees
 group by department_id;
-------------------------------------문제 
select department_id 부서아이디, count(*)
from employees
group by department_id
having count(*)>=3;

select department_id 부서아이디, avg(salary)
from employees
where department_id in (30,90) and salary between 5000 and 12000
group by department_id
having avg(salary) >=7500
order by avg(salary) desc;

select department_id 부서아이디, max(salary)*12 최대연봉,  round(avg(salary) *12) 평균연봉 
from employees
group by department_id
having sum(salary) >=20000;

 SELECT nvl(to_char(department_id),'없음') 부서아이디, MAX(salary) 최대급여, MIN(salary) 최소급여,
round(AVG(salary)) 평균급여, SUM(salary) 급여합계, COUNT(*) 직원수
FROM employees
GROUP BY department_id
ORDER BY 1;

select department_id 부서id, max(salary) 최대급여,
min(salary) 최소급여, avg(salary) 평균급여,
sum(salary) 합계급여, count(*) 인원수
from employees
group by department_id
having max(salary) != min(salary)
order by 1;

select substr(hire_date,1,2) ||'년' 입사년도, count(*) ||'명' 직원수
from employees
group by substr(hire_date,1,2)
having count(*) >=8
order by count(*) desc;

select substr(hire_date,4,2) 입사월, count(first_name) 수
from employees 
where rownum <=8
group by substr(hire_date,4,2)
order by count(first_name) asc;



select substr(hire_date,4,2) 입사월 , count(first_name) "입사자 수"
from employees
where rownum <=8
group by substr(hire_date,4,2)
order by count(*) asc ;

SELECT TO_CHAR(hire_date, 'mm') || '월' "월 별", hire_date 입사일, employee_id 사번, first_name 이름, department_id 부서명, count(*) || '명' 입사자수
FROM employees
WHERE rownum <= 8
GROUP BY TO_CHAR(hire_date, 'mm'), hire_date, employee_id, first_name, department_id
ORDER BY 입사자수;















