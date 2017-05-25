package com.dto;

import java.util.Date;

public class VisitVO {
	private int vi_idx;
	private String vi_ip;
	private Date vi_regdate;
	private String vi_referer;
	private String vi_agent;
	private String vi_brower;
	private String vi_os;
	private String vi_device;
	
	public int getVi_idx() {
		return vi_idx;
	}
	public void setVi_idx(int vi_idx) {
		this.vi_idx = vi_idx;
	}
	public String getVi_ip() {
		return vi_ip;
	}
	public void setVi_ip(String vi_ip) {
		this.vi_ip = vi_ip;
	}
	public Date getVi_regdate() {
		return vi_regdate;
	}
	public void setVi_regdate(Date vi_regdate) {
		this.vi_regdate = vi_regdate;
	}
	public String getVi_referer() {
		return vi_referer;
	}
	public void setVi_referer(String vi_referer) {
		this.vi_referer = vi_referer;
	}
	public String getVi_agent() {
		return vi_agent;
	}
	public void setVi_agent(String vi_agent) {
		this.vi_agent = vi_agent;
	}
	public String getVi_brower() {
		return vi_brower;
	}
	public void setVi_brower(String vi_brower) {
		this.vi_brower = vi_brower;
	}
	public String getVi_os() {
		return vi_os;
	}
	public void setVi_os(String vi_os) {
		this.vi_os = vi_os;
	}
	public String getVi_device() {
		return vi_device;
	}
	public void setVi_device(String vi_device) {
		this.vi_device = vi_device;
	}
	@Override
	public String toString() {
		return "VisitVO [vi_idx=" + vi_idx + ", vi_ip=" + vi_ip + ", vi_regdate=" + vi_regdate + ", vi_referer="
				+ vi_referer + ", vi_agent=" + vi_agent + ", vi_brower=" + vi_brower + ", vi_os=" + vi_os
				+ ", vi_device=" + vi_device + "]";
	}
	
	
}
