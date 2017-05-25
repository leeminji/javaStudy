package com.admin.login.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

public class AdmLoginAction implements AdmAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		//로그인 세션 검색
		if( session.getAttribute("ss_admin_id") == null ){
			String url = "/admin/login/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/adm/main");
			return;
		}
	}

}
