package com.dto;

import java.util.Date;

public class BoardOptionVO {
	private int op_idx;
	private String op_name;
	private String op_table;
	private String op_skin;
	private String op_adm_skin;
	private int op_is_ip;
	private int op_is_sign;
	private int op_new_date;
	private Date op_regdate;
	private int op_view_level;
	private int op_list_level;
	private int op_write_level;
	private int op_comment_level;
	private int op_reply_level;
	private int op_page_length;
	private int op_is_secret;
	private int op_is_preview;
	private int op_is_notice;
	private String op_cate;
	private int op_img_width; //최대 이미지 넓이
	private String op_thumb; //썸네일 넓이;높이
	
	private String view_href;
	
	
	public int getOp_img_width() {
		return op_img_width;
	}
	public void setOp_img_width(int op_img_width) {
		this.op_img_width = op_img_width;
	}
	public String getOp_thumb() {
		return op_thumb;
	}
	public void setOp_thumb(String op_thumb) {
		this.op_thumb = op_thumb;
	}
	public String getOp_cate() {
		return op_cate;
	}
	public void setOp_cate(String op_cate) {
		this.op_cate = op_cate;
	}
	public String getView_href() {
		return view_href;
	}
	public void setView_href(String view_href) {
		this.view_href = view_href;
	}
	
	public int getOp_idx() {
		return op_idx;
	}
	public void setOp_idx(int op_idx) {
		this.op_idx = op_idx;
	}
	public String getOp_name() {
		return op_name;
	}
	public void setOp_name(String op_name) {
		this.op_name = op_name;
	}
	public String getOp_table() {
		return op_table;
	}
	public void setOp_table(String op_table) {
		this.op_table = op_table;
	}
	public String getOp_skin() {
		return op_skin;
	}
	public void setOp_skin(String op_skin) {
		this.op_skin = op_skin;
	}
	public String getOp_adm_skin() {
		return op_adm_skin;
	}
	public void setOp_adm_skin(String op_adm_skin) {
		this.op_adm_skin = op_adm_skin;
	}
	public int getOp_is_secret() {
		return op_is_secret;
	}
	public void setOp_is_secret(int op_is_secret) {
		this.op_is_secret = op_is_secret;
	}

	public int getOp_is_ip() {
		return op_is_ip;
	}
	public void setOp_is_ip(int op_is_ip) {
		this.op_is_ip = op_is_ip;
	}
	public int getOp_is_sign() {
		return op_is_sign;
	}
	public void setOp_is_sign(int op_is_sign) {
		this.op_is_sign = op_is_sign;
	}
	public int getOp_new_date() {
		return op_new_date;
	}
	public void setOp_new_date(int op_new_date) {
		this.op_new_date = op_new_date;
	}
	public Date getOp_regdate() {
		return op_regdate;
	}
	public void setOp_regdate(Date op_regdate) {
		this.op_regdate = op_regdate;
	}
	public int getOp_view_level() {
		return op_view_level;
	}
	public void setOp_view_level(int op_view_level) {
		this.op_view_level = op_view_level;
	}
	public int getOp_list_level() {
		return op_list_level;
	}
	public void setOp_list_level(int op_list_level) {
		this.op_list_level = op_list_level;
	}
	public int getOp_write_level() {
		return op_write_level;
	}
	public void setOp_write_level(int op_write_level) {
		this.op_write_level = op_write_level;
	}
	public int getOp_comment_level() {
		return op_comment_level;
	}
	public void setOp_comment_level(int op_comment_level) {
		this.op_comment_level = op_comment_level;
	}
	public int getOp_reply_level() {
		return op_reply_level;
	}
	public void setOp_reply_level(int op_reply_level) {
		this.op_reply_level = op_reply_level;
	}
	public int getOp_page_length() {
		return op_page_length;
	}
	public void setOp_page_length(int op_page_length) {
		this.op_page_length = op_page_length;
	}
	public int getOp_is_preview() {
		return op_is_preview;
	}
	public void setOp_is_preview(int op_is_preview) {
		this.op_is_preview = op_is_preview;
	}
	public int getOp_is_notice() {
		return op_is_notice;
	}
	public void setOp_is_notice(int op_is_notice) {
		this.op_is_notice = op_is_notice;
	}
	
	@Override
	public String toString() {
		return "BoardOptionVO [op_idx=" + op_idx + ", op_name=" + op_name + ", op_table=" + op_table + ", op_skin="
				+ op_skin + ", op_adm_skin=" + op_adm_skin + ", op_is_secret=" + op_is_secret + ", op_is_ip=" + op_is_ip
				+ ", op_is_sign=" + op_is_sign + ", op_new_date=" + op_new_date + ", op_regdate=" + op_regdate
				+ ", op_view_level=" + op_view_level + ", op_list_level=" + op_list_level + ", op_write_level="
				+ op_write_level + ", op_comment_level=" + op_comment_level + ", op_reply_level=" + op_reply_level
				+ ", op_page_length=" + op_page_length + ", op_is_preview=" + op_is_preview + ", op_is_notice="
				+ op_is_notice + ", op_cate=" + op_cate + ", view_href=" + view_href + "]";
	}

}
