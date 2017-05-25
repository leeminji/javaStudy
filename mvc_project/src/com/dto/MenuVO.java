package com.dto;

public class MenuVO {
	private int me_idx;
	private int me_num;
	private String me_code;
	private String me_name;
	private String me_link;
	private int me_is_view;
	private int me_is_target;
	private String me_content = "";
	
	
	public int getMe_num() {
		return me_num;
	}
	public void setMe_num(int me_num) {
		this.me_num = me_num;
	}
	public int getMe_is_target() {
		return me_is_target;
	}
	public void setMe_is_target(int me_is_target) {
		this.me_is_target = me_is_target;
	}
	public int getMe_idx() {
		return me_idx;
	}
	public void setMe_idx(int me_idx) {
		this.me_idx = me_idx;
	}
	public String getMe_code() {
		return me_code;
	}
	public void setMe_code(String me_code) {
		this.me_code = me_code;
	}
	public String getMe_name() {
		return me_name == null ? " " : me_name;
	}
	public void setMe_name(String me_name) {
		this.me_name = me_name;
	}
	public String getMe_link() {
		return me_link == null ? " " : me_link;
	}
	public void setMe_link(String me_link) {
		this.me_link = me_link;
	}
	public int getMe_is_view() {
		return me_is_view;
	}
	public void setMe_is_view(int me_is_view) {
		this.me_is_view = me_is_view;
	}
	public String getMe_content() {
		return me_content == null ? " " : me_content;
	}
	public void setMe_content(String me_content) {
		this.me_content = me_content;
	}
	
	@Override
	public String toString() {
		return "MenuVO [me_idx=" + me_idx + ", me_num=" + me_num + ", me_code=" + me_code + ", me_name=" + me_name
				+ ", me_link=" + me_link + ", me_is_view=" + me_is_view + ", me_is_target=" + me_is_target
				+ ", me_content=" + me_content + "]";
	}
}
