package com.admin.controller.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import com.admin.login.controller.action.AdmAction;
import com.dao.BoardOptionDao;
import com.dao.MemberDAO;
import com.dto.BoardOptionVO;
import com.dto.MemberVO;

public class AdmMainAction implements AdmAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if( session.getAttribute("ss_admin_id") == null ){
			response.sendRedirect(request.getContextPath()+"/adm/login/login.do");
			return;
		}else{
			//최신 회원
			MemberDAO mDao = MemberDAO.getInstance();
			ArrayList<MemberVO> memberList = mDao.getMemberList(1, 10, "", "", "");
			request.setAttribute("memberList", memberList);
			

			String url = "/admin/main/main.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
	}
}
