package com.front.member.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import com.dto.MemberVO;
import com.front.controller.action.Action;
public class MemberJoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		boolean is_login = session.getAttribute("ss_mb_id") == null ? false : true;
		if( !is_login ){
			
			String RequestURI = request.getRequestURI();
			String contextPath = request.getContextPath();
			String command = RequestURI.substring(contextPath.length());
			String url = "";
			
			//로그인 상태가 아닌경우 회원가입 폼으로 이동.
			if( command.equals("/pf/member/join")){
				url = "/login/joinIntro.jsp";
			}
			
			//다음을 클릭한 경우 
			if( command.equals("/pf/member/join_form")){
				url = "/login/memberForm.jsp"; //회원가입 폼으로 이동
				MemberVO mVo = new MemberVO();
				request.setAttribute("member", mVo);
			}
			request.setAttribute("title", " 회원가입");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		}else{
			//로그인 상태라면 메인으로 이동
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath+"/pf/index.do");
		}
	}
}
