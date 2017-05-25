create table login (
lo_ip	varchar2(20),
mb_id	varchar2(20),
lo_regdate	date,
lo_location	varchar2(500),
lo_url	varchar2(500)
)

select * from tab;

select * from login;

drop table login purge;
select bo_file from board_portfolio where bo_idx = 46;
select * from board_file;

truncate table board_file;