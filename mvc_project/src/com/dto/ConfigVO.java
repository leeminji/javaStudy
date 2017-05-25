package com.dto;

public class ConfigVO {
	
	private static ConfigVO instance = new ConfigVO();
	private ConfigVO(){}
	public static ConfigVO getInstance(){
		return instance;
	}
	
	private String cf_title; //프로젝트 이름
	private String cf_admin; //최종관리자 아이디
	private String cf_admin_email; //최종관리자 이메일
	private String cf_admin_name; //최종관리자 이름
	private String cf_addr; //프로젝트 주소
	private String cf_tel; //프로젝트 전화
	private String cf_info1; //프로젝트 정보1
	private String cf_info2;
	private String cf_info3;
	private int cf_use_addr; //회원가입시 주소 필요 여부
	private int cf_use_tel; //회원가입시 전화 필요 여부
	private int cf_use_hp; //회원가입시 핸드폰 필요 여부
	private String cf_privacy; //개인정보취급방침
	private String cf_service; //이용약관
	private String cf_title_info = ""; //타이틀정보
	
	public String getCf_title_info() {
		if( this.cf_title_info.equals("")){
			return this.cf_title;
		}else{
			return this.cf_title +">" + cf_title_info;
		}
	}
	public void setCf_title_info(String cf_title_info) {
		this.cf_title_info = cf_title_info;
	}
	
	public String getCf_privacy() {
		return cf_privacy;
	}
	public void setCf_privacy(String cf_privacy) {
		this.cf_privacy = cf_privacy;
	}
	public String getCf_service() {
		return cf_service;
	}
	public void setCf_service(String cf_service) {
		this.cf_service = cf_service;
	}
	public String getCf_title() {
		return cf_title;
	}
	public void setCf_title(String cf_title) {
		this.cf_title = cf_title;
	}
	public String getCf_admin() {
		return cf_admin;
	}
	public void setCf_admin(String cf_admin) {
		this.cf_admin = cf_admin;
	}
	public String getCf_admin_email() {
		return cf_admin_email;
	}
	public void setCf_admin_email(String cf_admin_email) {
		this.cf_admin_email = cf_admin_email;
	}
	public String getCf_admin_name() {
		return cf_admin_name;
	}
	public void setCf_admin_name(String cf_admin_name) {
		this.cf_admin_name = cf_admin_name;
	}
	public String getCf_addr() {
		return cf_addr;
	}
	public void setCf_addr(String cf_addr) {
		this.cf_addr = cf_addr;
	}
	public String getCf_tel() {
		return cf_tel;
	}
	public void setCf_tel(String cf_tel) {
		this.cf_tel = cf_tel;
	}
	public String getCf_info1() {
		return cf_info1;
	}
	public void setCf_info1(String cf_info1) {
		this.cf_info1 = cf_info1;
	}
	public String getCf_info2() {
		return cf_info2;
	}
	public void setCf_info2(String cf_info2) {
		this.cf_info2 = cf_info2;
	}
	public String getCf_info3() {
		return cf_info3;
	}
	public void setCf_info3(String cf_info3) {
		this.cf_info3 = cf_info3;
	}
	public int getCf_use_addr() {
		return cf_use_addr;
	}
	public void setCf_use_addr(int cf_use_addr) {
		this.cf_use_addr = cf_use_addr;
	}
	public int getCf_use_tel() {
		return cf_use_tel;
	}
	public void setCf_use_tel(int cf_use_tel) {
		this.cf_use_tel = cf_use_tel;
	}
	public int getCf_use_hp() {
		return cf_use_hp;
	}
	public void setCf_use_hp(int cf_use_hp) {
		this.cf_use_hp = cf_use_hp;
	}
	@Override
	public String toString() {
		return "ConfigVO [cf_title=" + cf_title + ", cf_admin=" + cf_admin + ", cf_admin_email=" + cf_admin_email
				+ ", cf_admin_name=" + cf_admin_name + ", cf_addr=" + cf_addr + ", cf_tel=" + cf_tel + ", cf_info1="
				+ cf_info1 + ", cf_info2=" + cf_info2 + ", cf_info3=" + cf_info3 + ", cf_use_addr=" + cf_use_addr
				+ ", cf_use_tel=" + cf_use_tel + ", cf_use_hp=" + cf_use_hp + ", cf_privacy=" + cf_privacy
				+ ", cf_service=" + cf_service + ", cf_title_info=" + cf_title_info + "]";
	}
	
}
