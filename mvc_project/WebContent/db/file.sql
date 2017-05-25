create table board_file(
	bo_table	varchar2(100),
	bo_idx	number(10),
	bf_no	number(10),
	bf_file	varchar2(500),
	bf_source varchar2(500),
	bf_download	number(5),
	bf_content	varchar2(500),
	bf_filesize	number(10),
	bf_datetime	date
)

select * from tab;
select * from seq;

truncate table board_file; 
drop table board_file purge;

select * from board_file;

select * from BOARD_PORTFOLIO_SEQ;

select BOARD_PORTFOLIO_SEQ.CURRVAL from dual;

select last_number from seq where sequence_name = 'BOARD_PORTFOLIO_SEQ';

SELECT last_number FROM SEQ WHERE sequence_name = 'BOARD_portfolio_SEQ'
