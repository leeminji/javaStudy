package com.util;

import javax.servlet.http.HttpServletRequest;

public class ClientUtil {
	
	public static String getClientWebVer(HttpServletRequest request) throws Exception {
		String user_agent = request.getHeader("user-agent");
		// 웹브라우저 버전 조회
		String webVer = "";
		String[] arr = { "MSIE", "OPERA", "NETSCAPE", "FIREFOX", "SAFARI" };
		for (int i = 0; i < arr.length; i++) {
			int s_loc = user_agent.toUpperCase().indexOf(arr[i]);
			if (s_loc != -1) {
				int f_loc = s_loc + arr[i].length();
				webVer = user_agent.toUpperCase().substring(f_loc, f_loc + 5);
				webVer = webVer.replaceAll("/", "").replaceAll(";", "").replaceAll("^", "").replaceAll(",", "")
						.replaceAll("//.", "");
			}
		}
		return webVer;
	}

	public static String getClientOS(HttpServletRequest request) throws Exception {
		String user_agent = request.getHeader("user-agent");
		String os = "";

		user_agent = user_agent.toLowerCase();

		if (user_agent.indexOf("windows nt 6.1") > -1) {
			os = "Windows7";
		} else if (user_agent.indexOf("windows nt 6.2") > -1 || user_agent.indexOf("windows nt 6.3") > -1) {
			os = "Windows8";
		} else if (user_agent.indexOf("windows nt 6.0") > -1) {
			os = "WindowsVista";
		} else if (user_agent.indexOf("windows nt 5.1") > -1) {
			os = "WindowsXP";
		} else if (user_agent.indexOf("windows nt 5.0") > -1) {
			os = "Windows2000";
		} else if (user_agent.indexOf("windows nt 4.0") > -1) {
			os = "WindowsNT";
		} else if (user_agent.indexOf("windows 98") > -1) {
			os = "Windows98";
		} else if (user_agent.indexOf("windows 95") > -1) {
			os = "Windows95";
		}
		// window 외
		else if (user_agent.indexOf("iphone") > -1) {
			os = "iPhone";
		} else if (user_agent.indexOf("ipad") > -1) {
			os = "iPad";
		} else if (user_agent.indexOf("android") > -1) {
			os = "android";
		} else if (user_agent.indexOf("mac") > -1) {
			os = "mac";
		} else if (user_agent.indexOf("linux") > -1) {
			os = "Linux";
		} else {
			os = user_agent;
		}
		return os;
	}
	
	//디바이스 체크, 모바일, PC
	public static String getClinetDevice(HttpServletRequest request){
		String user_agent = request.getHeader("user-agent");
		String [] mobiles = {"iPhone", "iPod", "Android", "BlackBerry", "Windows CE",
				"Nokia", "Webos", "Opera Mini", "SonyEricsson", "Opera Mobi", "IEMobile"};
		String device = "PC";
		user_agent = user_agent.toLowerCase();	
		
		if( user_agent != null && !user_agent.equals("")){
			for( String mobile : mobiles ){
				if( user_agent.indexOf(mobile.toLowerCase()) > -1){
					device = "Moblie";
					break;
				}
			}
		}
		
		return device;
	}

}
