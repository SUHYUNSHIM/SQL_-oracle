

create table panmae_table(
    id number(3),
    dan number(5),
    soo number(5)
    );
commit;


 user-specified eoor number -20000 ~20999
raise_application_error(-20000,'�����޽���');
-------------------------------------------------------

set serveroutput on;
create or replace trigger no_working
before update or insert or delete on panmae_table

begin
if (to_char(sysdate, 'DY') in ('ȭ','��')) then
    raise_application_error (-20000, 'Who are you???');
else
    dbms_output.put_line('Have a good time');
end if;
end;
/

update panmae_table
set soo= 300
where id =1;

drop trigger kyesan_trigger;
