package com.front.member.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.front.controller.action.Action;
public class MemberLogOutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//세션 삭제 ( 로그아웃시 모두 삭제 )
		HttpSession session = request.getSession();
		session.invalidate();
		
		//메인페이지로 이동
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath+"/pf/index.do");
	}
}
