select op_table from BOARD_OPTION;


create table board_option(
	op_idx	number(10) not null,
	op_name	varchar2(10) not null,
	op_table	varchar2(10) not null,
	op_skin	varchar2(20),
	op_adm_skin	varchar2(20),
	op_is_secret	number(1) default 0,
	op_is_ip	number(1) default 0,
	op_is_sign	number(1) default 0,
	op_new_date	number(5) default 24,
	op_regdate	date default sysdate,
	op_view_level	number(2) default 0,
	op_list_level	number(2) default 0,
	op_write_level	number(2) default 0,
	op_comment_level	number(2) default 0,
	op_reply_level	number(2) default 0,
	op_page_length	number(2) default 10,
	op_is_preview	number(1) default 0,
	op_is_notice	number(1) default 0
)
SELECT * FROM BOARD_notice ORDER BY BO_REF, BO_LEVEL, BO_STEP desc

alter table board_option add op_img_width number(5);
alter table board_option add op_thumb varchar(20);

alter table board_option add primary key(op_idx, op_table);

select * from board_option;
select * from seq;
select * from tab;
select * from board_notice;
op_img_width
op_thumb


create sequence board_option_seq
increment by 1
start with 1
maxvalue 9999999999
nocache

alter table board_option add op_cate varchar2(1000);



drop sequence board_option_seq;
delete from board_option where op_idx = 32;
alter table board_option modify ( op_name varchar2(20))
drop table BOARD_NOTICE purge;
drop sequence board_notice_seq;
