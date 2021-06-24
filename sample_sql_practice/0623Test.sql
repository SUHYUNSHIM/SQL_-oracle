--����1
--employees ���̺��� �޿��� 15000 �̻��� ������� �̸��� �޿����� 
--�����ϴ� view�� ����ÿ�.
--�� view �̸��� ������ ���� ���� ��� �̸��� ��ü �����ؾ� �ϰ�, ���� �޿��� �ش� ������
--�ƴ� ��츦 insert�Ϸ� �� ��� ó���� �ȵǾ�� �Ѵ�.
grant insert on sal_15_view  to hr;


create or replace view sal_15_view 
as select first_name, salary
from hr.employees
where salary >=15000
with check option;

commit;
show user;
select * from sal_15_view;
insert into sal_15_view(salary) values(15000);

--2. �־��� ���̺� table-level ����� ���������� �־� ��ü ���̺��� �ϼ��Ͻÿ�
-- ���� ����, name�� �⺻Ű�� �����. 
--tel�� null�� �����ϸ� ���� ��ȣ�� ������ �ȵȴ�.
--hobby�� null�� �Ǹ� �ȵȴ�.
--dept_id�� dept_table�� �⺻Ű d_id�� �����Ѵ�. 

create table memtable2 (
name varchar2(20),
tel varchar2(20),
hobby varchar2(30),
dept_id number(5)
);

alter table memtable2 
add constraint memtable2_name_pk primary key(name);

alter table memtable2
add constraint memtable2_tel_uk unique(tel);

alter table memtable2
modify (hobby varchar2(20) constraint memtable2_hobby_nn not null);

alter table memtable2
add constraint memtable2_dept_id_fk references dept_table(d_id);

commit;

--3.  ���� ���ν����� �ϼ��Ͻÿ� .
-- ��� ���̵�� �λ����� �Է¹޾� �ش� ����� �޿��� �λ��� ��ŭ �λ��ϴ� 
--update ������ �� ���ν����� �ϼ��Ͻÿ�.
--�� �Է����� 110�� 10�� ���� ����� 110�� ����� �޿��� 10%�� �λ��ϴ� ����̴�.

grant update on employees to hr;

create or replace procedure inc_salary_pc
(v_e_id in employees.employee_id%type, v_percent in number)
is   
begin
    update employees
    set salary= salary * (1+v_percent/100)
    where employee_id = v_e_id;
end;
/
commit;
select salary from employees where employee_id = 110;   --24000, 17000, 17000

exec inc_salary_pc(110,10);
commit;
select salary from employees where employee_id = 110;  

rollback;



