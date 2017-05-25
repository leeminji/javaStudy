package com.admin.board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;

public class BoardAdmCheckPassAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String bo_pass = request.getParameter("bo_pass");
		String bo_idx = request.getParameter("bo_idx");
		String bo_table = request.getParameter("bo_table");
		String command = request.getParameter("command");
		
		if( bo_pass == null ){
			request.setAttribute("message", "비밀번호를 입력해주세요");
		}else{
			BoardDAO bDao = BoardDAO.getInstance();
			
			int reslut = bDao.chkBoardPass(bo_table, Integer.parseInt(bo_idx), bo_pass);
			if( reslut == 1 ){
				request.setAttribute("message", "성공");
				
				if( command.equals("update") ){
					response.setContentType("text/html;charset=utf-8");					
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("location.href='write.do?bo_table="+ bo_table +"&bo_idx="+bo_idx+"';");
					out.println("</script>");
					out.close();
				}
				
				if( command.equals("delete") ){
					new BoardAdmDeleteAction().execute(request, response);
				}
			}else{
				request.setAttribute("message", "비밀번호가 다릅니다.");
			}
		}
		
		String url = "/admin/check/boardCheckPass.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
