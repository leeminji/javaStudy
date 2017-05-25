create table visit (
	vi_idx number(10) primary key,
	vi_ip varchar2(20),
	vi_regdate date ,
	vi_referer varchar2(200),
	vi_agent varchar2(100),
	vi_brower varchar2(100),
	vi_os varchar2(100),
	vi_device varchar2(100)
)

select * from tab;
select * from visit;
select * from login;

create sequence visit_seq
increment by 1
start with 1
maxvalue 9999999999
nocache

select * from seq;
drop table board2 purge

drop sequence board2_seq;