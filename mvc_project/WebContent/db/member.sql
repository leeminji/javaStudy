create table member(
	mb_idx number(6) primary key,
	mb_id varchar2(20) not null,
	mb_pass varchar2(300) not null,
	mb_name varchar2(20) not null,
	mb_email varchar2(30) not null,
	mb_regdate date default sysdate,
	mb_lastest date default sysdate,
	mb_birth varchar2(10),
	mb_zip varchar2(10),
	mb_addr1 varchar2(100),
	mb_addr2 varchar2(100),
	mb_mailing number(1) default 0,
	mb_level number(2) default 1,
	mb_hp varchar2(20),
	mb_tel varchar2(20)
)

alter table member add mb_salt varchar2(300);
alter table member modify(mb_pass varchar2(300));

alter table  BOARD_qna add bo_tag varchar2(500);

create sequence member_seq
increment by 1
start with 1
maxvalue 999999
nocache

select * from seq;
select * from BOARD_PORTFOLIO;
select * from tab;
drop table member purge;

insert into MEMBER (mb_idx,mb_id, mb_pass, mb_name, mb_email)
values(member_seq.nextval,'admin', '1234', '관리자', 'admin@admin.co.kr')


update MEMBER set mb_level = 2 where mb_id = 'dreamfly25';

create table login(
	lo_ip varchar2(10),
	mb_id varchar2(20),
	lo_regdate date default sysdate,
	lo_location varchar2(100),
	lo_url varchar2(100)
)

select * from login;

select * from board_notice;
