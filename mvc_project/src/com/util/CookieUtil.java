package com.util;
import javax.servlet.http.Cookie;

public class CookieUtil {
	static private CookieUtil instance = new CookieUtil();
	static public CookieUtil getInstance(){
		return instance;
	}
	private CookieUtil(){}
	
	//쿠키 설정
	public Cookie setCookie(String cookieName, String cookieValue, int maxAge){
		Cookie cookie = null;
		cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	//쿠키 찾기
	public Cookie getCookie(Cookie[] cookies, String cookieName){
		Cookie cookie = null;
		if( cookies != null ){
			for( Cookie c : cookies ){
				if( c.getName().equals(cookieName)){
					cookie = c;
				}
			}
		}
		return cookie;
	}
	
	public Cookie deleteCookie(Cookie[] cookies, String cookieName){
		Cookie cookie = getCookie(cookies, cookieName);
		cookie.setMaxAge(0);
		return cookie;
	}
}
