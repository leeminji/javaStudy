select * from tab;

create table menu(
	me_idx number(10) primary key,
	me_num number(5) default 0,
	me_code varchar2(20) not null,
	me_name varchar2(30) not null,
	me_link varchar2(100),
	me_is_view number(1) default 0,
	me_is_target number(1) default 0,
	me_content varchar2(1000)
)



select * from menu;
delete from menu where me_idx in(4, 5, 6);
create sequence menu_seq
increment by 1
start with 1
maxvalue 999999
nocache

insert into menu values(menu_seq.nextval, 1, '10', '회사소개', '/p/company/info', 1, 0,'');

