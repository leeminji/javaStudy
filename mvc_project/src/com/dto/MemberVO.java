package com.dto;

import java.security.MessageDigest;
import java.util.Date;

public class MemberVO {
	private int mb_eq; //번호
	private int mb_idx; //인덱스
	private String mb_id; //아이디
	private String mb_pass; //비밀번호
	private String mb_name; //이름
	private String mb_email; //이메일
	private Date mb_regdate; //등록일
	private Date mb_lastest; //최근접속일
	private String mb_birth; //생일
	private String mb_zip; //우편번호
	private String mb_addr1; //주소
	private String mb_addr2; //주소
	private int mb_mailing = 0; //메일확인
	private int mb_level; //권한
	private String mb_tel; //전화번호
	private String mb_hp; //핸드폰
	private String mb_salt; //암호화
	
	public String getMb_salt() {
		return mb_salt;
	}
	public void setMb_salt(String mb_salt) {
		this.mb_salt = mb_salt;
	}
	public int getMb_eq() {
		return mb_eq;
	}
	public void setMb_eq(int mb_eq) {
		this.mb_eq = mb_eq;
	}
	public String getMb_tel() {
		return mb_tel;
	}
	public void setMb_tel(String mb_tel) {
		this.mb_tel = mb_tel;
	}
	public String getMb_hp() {
		return mb_hp;
	}
	public void setMb_hp(String mb_hp) {
		this.mb_hp = mb_hp;
	}
	public int getMb_idx() {
		return mb_idx;
	}
	public void setMb_idx(int mb_idx) {
		this.mb_idx = mb_idx;
	}
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMb_pass() {
		return mb_pass;
	}
	public void setMb_pass(String mb_pass) {
		this.mb_pass = mb_pass;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_email() {
		return mb_email;
	}
	public void setMb_email(String mb_email) {
		this.mb_email = mb_email;
	}
	public Date getMb_regdate() {
		return mb_regdate;
	}
	public void setMb_regdate(Date mb_regdate) {
		this.mb_regdate = mb_regdate;
	}
	public Date getMb_lastest() {
		return mb_lastest;
	}
	public void setMb_lastest(Date mb_lastest) {
		this.mb_lastest = mb_lastest;
	}
	public String getMb_birth() {
		return mb_birth;
	}
	public void setMb_birth(String mb_birth) {
		this.mb_birth = mb_birth;
	}
	public String getMb_zip() {
		return mb_zip;
	}
	public void setMb_zip(String mb_zip) {
		this.mb_zip = mb_zip;
	}
	public String getMb_addr1() {
		return mb_addr1;
	}
	public void setMb_addr1(String mb_addr1) {
		this.mb_addr1 = mb_addr1;
	}
	public String getMb_addr2() {
		return mb_addr2;
	}
	public void setMb_addr2(String mb_addr2) {
		this.mb_addr2 = mb_addr2;
	}
	public int getMb_mailing() {
		return mb_mailing;
	}
	public void setMb_mailing(int mb_mailing) {
		this.mb_mailing = mb_mailing;
	}
	public int getMb_level() {
		return mb_level;
	}
	public void setMb_level(int mb_level) {
		this.mb_level = mb_level;
	}
	@Override
	public String toString() {
		return "MemberVO [mb_eq=" + mb_eq + ", mb_idx=" + mb_idx + ", mb_id=" + mb_id + ", mb_pass=" + mb_pass
				+ ", mb_name=" + mb_name + ", mb_email=" + mb_email + ", mb_regdate=" + mb_regdate + ", mb_lastest="
				+ mb_lastest + ", mb_birth=" + mb_birth + ", mb_zip=" + mb_zip + ", mb_addr1=" + mb_addr1
				+ ", mb_addr2=" + mb_addr2 + ", mb_mailing=" + mb_mailing + ", mb_level=" + mb_level + ", mb_tel="
				+ mb_tel + ", mb_hp=" + mb_hp + ", mb_salt=" + mb_salt + "]";
	}
	
	
	

}
