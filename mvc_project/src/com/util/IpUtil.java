package com.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
	
	static private IpUtil instance = new IpUtil();
	private IpUtil(){}
	static public IpUtil getInstance(){
		return instance;
	}
	
	//아이피 가져오기
	public String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
		}
		if (ip == null || ip.length() == 0) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
