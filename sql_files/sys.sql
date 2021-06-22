grant create table, create view , create sequence to babo;
alter user babo default tablespace users quota 50m on users;

revoke create view from babo;

grant select ,insert(id,name) on hr.modi_col to babo, hong;

revoke insert on hr.modi_col from babo;

grant select on hr.modi_col to hong with grant option;

revoke select on hr.modi_col from hong;

drop user seong cascade;

select file_name, tablespace_name, bytes, status
from DBA_DATA_FILES;

create or replace function moon(v_earth number)
    return number
    is
    v_moon number;
    begin
    v_moon := v_earth*(1/6);
    return (v_moon);
    end;
    /
    
    select moon(70) from dual;    
    
    set serveroutput on
    
    create or replace procedure dal_mom(v_jikoo_mom in number)
    is
        v_dal_mom number(7,2);
    begin
        v_dal_mom := v_jikoo_mom * round((1/6),2);     
        dbms_output.put_line(v_dal_mom);
    end;
    /    
    exec dal_mom(100.24);
    

   
  
        
        