package com.admin.member.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MemberDAO;
import com.front.controller.action.Action;
public class MemberAdmOutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String [] mb_idxs = request.getParameterValues("mb_idx");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		MemberDAO mDao = MemberDAO.getInstance();
		int result = 0;
		
		for( String mb_idx : mb_idxs ){
			result = mDao.outMember(Integer.parseInt(mb_idx));
		}
		if( result == 1){
			out.println("<script>");
			out.println("alert('탈퇴성공');");
			out.println("window.location.href='list.do';");
			out.println("</script>");
		}else{
			out.println("<script>");
			out.println("alert('탈퇴실패');");
			out.println("window.location.href='list.do';");
			out.println("</script>");
		}
		out.close();
	}

}
