--�μ� ���̵� 80�� ����� ���̵�� �޿� ���
select employee_id ������̵�, salary �޿�
from employees
where department_id = '80';

--���̺� ����
drop table employee_dept80;

create table employee_dept80
as select employee_id ������̵�, salary �޿�
    from employees
    where department_id = 80;

select * from employee_dept80;

--��� ������. ����̸�, ����ȣ, �μ����̵�

select first_name ����̸�, phone_number ����ȣ, department_id �μ����̵�
from employees;

drop table phones;
create table phones(����̸�, ����ȣ, �μ����̵�)
as select first_name, phone_number, department_id
from employees;

select * from phones;

create table phones_2
as select *
     from employees
     where 1=2;

select * from phones_2;

insert into employee_dept80 values('100',null);

insert into employee_dept80 
select employee_id ������̵�, salary �޿�
         from employees
         where department_id = 100;
         
select department_id �μ��ƾƵ�
from employees
where first_name  ='Daniel';

update employees
set department_id = (select department_id
                            from employees
                            where first_name ='Daniel')
where first_name = 'Alexander';

--�μ� ���̵� 100�� �μ� �������� �޿� ��
--�ִ� ������ ���� �޿��� �޴� ������ ��ȸ �� ���
select first_name �̸�, salary �޿�, department_id �μ����̵�
from employees 
where salary > all (select salary from employees
                    where department_id = 100)
order by 3;

select employee_id �μ����̵�, first_name �̸�, salary �޿�
from employees
where salary > all (select salary from employees 
                where department_id =80)
order by 3; 

select max(salary) from employees where department_id = 100;

select employee_id �μ����̵�, first_name �̸�, salary �޿�
from employees
where salary <= any (select salary
                        from employees
                        where department_id =80)
order by 3;

select * from tab;

purge recyclebin;

create table tel777(
    sabun number(5),
    irum varchar(20),
    tel varchar2(20)
    );
    
    insert into tel777 values (26, 'doyoung', '021');
    insert into tel777 values(66,'haechan','066');
    insert into tel777 values (505,'jungwoo','219');
    insert into tel777 values (77,'jaehyun','214');
    insert into tel777 values(12, 'jaemin','0808');
    
    commit;
    drop table tel777;
    
    flashback table tel777 to before drop;
    
    select * from tel777;
    
    drop table tel777 purge;
    flashback table tel777 to before drop;
    
    update tel77 
    set tel ='012' where irum = 'jaemin';
    
    update tel77 
    set sabun = '055', tel = '011' where irum = 'jungwoo';
    
    select * from tel77;
   -- drop�� ���� �����̴�.
   
   drop table tel77;
   
   create table mainBook(
        nums number(5),
        m_num varchar2(20),
        man_num varchar2(20),
        turn_in_date date default sysdate,
        rental_fee number(10)
        );
        
    create table memberBook(
        m_num varchar2(20),
        m_name varchar2(15),
        phone varchar2(15),
        genre varchar2(15)
        );
   
   create table bookInfo(
      man_num varchar2(20),
      man_name varchar2(15),
      in_date date default sysdate,
      price number(10)
   );
   
   commit;
    


    

