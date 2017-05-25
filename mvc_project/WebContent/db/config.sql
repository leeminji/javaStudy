create table config(
	cf_title varchar2(30),
	cf_admin varchar2(20),
	cf_admin_email varchar2(50),
	cf_admin_name varchar2(20),
	cf_addr varchar2(50),
	cf_tel varchar2(20),
	cf_info1 varchar2(100),
	cf_info2 varchar2(100),
	cf_info3 varchar2(100),
	cf_use_addr number(1) default 0,
	cf_use_tel number(1) default 0,
	cf_use_hp number(1) default 0,
	cf_privacy varchar2(1000),
	cf_service varchar2(1000)
)


alter table config add cf_service varchar2(1000) 

select * from config;

insert into config (cf_title, cf_admin, cf_admin_email, cf_admin_name, cf_addr ) values('포트폴리오', 'admin', 'admin@admin.co.kr', '관리자', '서울시 성북구 정릉동')