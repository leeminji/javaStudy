create table popup(
	po_idx	number(10) primary key,
	po_device	varchar2(10),
	po_begin_time	date,
	po_end_time	date,
	po_disable_hours	number(10),
	po_left	number(10),
	po_top	number(10),
	po_height	number(10),
	po_width	number(10),
	po_subject	varchar2(1000),
	po_content	varchar2(4000)
)

select * from popup;

create sequence popup_seq 
increment by 1
start with 1
maxvalue 9999999999
nocache


