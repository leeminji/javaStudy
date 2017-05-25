package com.dto;

import java.util.Date;

public class BoardFileVO {
	private String bo_table;
	private int bo_idx;
	private int bf_no;
	private String bf_file;
	private String bf_source;
	private int bf_download;
	private String bf_content;
	private int bf_filesize;
	private Date bf_datetime;
	
	
	public String getBf_source() {
		return bf_source;
	}
	public void setBf_source(String bf_source) {
		this.bf_source = bf_source;
	}
	public String getBo_table() {
		return bo_table;
	}
	public void setBo_table(String bo_table) {
		this.bo_table = bo_table;
	}
	public int getBo_idx() {
		return bo_idx;
	}
	public void setBo_idx(int bo_idx) {
		this.bo_idx = bo_idx;
	}
	public int getBf_no() {
		return bf_no;
	}
	public void setBf_no(int bf_no) {
		this.bf_no = bf_no;
	}
	public String getBf_file() {
		return bf_file;
	}
	public void setBf_file(String bf_file) {
		this.bf_file = bf_file;
	}
	public int getBf_download() {
		return bf_download;
	}
	public void setBf_download(int bf_download) {
		this.bf_download = bf_download;
	}
	public String getBf_content() {
		return bf_content;
	}
	public void setBf_content(String bf_content) {
		this.bf_content = bf_content;
	}
	public int getBf_filesize() {
		return bf_filesize;
	}
	public void setBf_filesize(int bf_filesize) {
		this.bf_filesize = bf_filesize;
	}
	public Date getBf_datetime() {
		return bf_datetime;
	}
	public void setBf_datetime(Date bf_datetime) {
		this.bf_datetime = bf_datetime;
	}
	@Override
	public String toString() {
		return "BoardFileVO [bo_table=" + bo_table + ", bo_idx=" + bo_idx + ", bf_no=" + bf_no + ", bf_file=" + bf_file
				+ ", bf_download=" + bf_download + ", bf_content=" + bf_content + ", bf_filesize=" + bf_filesize
				+ ", bf_datetime=" + bf_datetime + "]";
	}
	
	
}
