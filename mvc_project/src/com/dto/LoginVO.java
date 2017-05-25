package com.dto;

import java.util.Date;

public class LoginVO {
	private String lo_ip; //아이피
	private String mb_id; //로그인한 아이디
	private Date lo_regdate; //로그인한 날짜
	private String lo_location;//로그인한 위치
	private String lo_url; //로그인한 URL
	
	public String getLo_ip() {
		return lo_ip;
	}
	public void setLo_ip(String lo_ip) {
		this.lo_ip = lo_ip;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public Date getLo_regdate() {
		return lo_regdate;
	}
	public void setLo_regdate(Date lo_regdate) {
		this.lo_regdate = lo_regdate;
	}
	public String getLo_location() {
		return lo_location;
	}
	public void setLo_location(String lo_location) {
		this.lo_location = lo_location;
	}
	public String getLo_url() {
		return lo_url;
	}
	public void setLo_url(String lo_url) {
		this.lo_url = lo_url;
	}
	
	@Override
	public String toString() {
		return "LoginVO [lo_ip=" + lo_ip + ", mb_id=" + mb_id + ", lo_regdate=" + lo_regdate + ", lo_location="
				+ lo_location + ", lo_url=" + lo_url + "]";
	}
}
