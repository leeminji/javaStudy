package com.admin.menu.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MenuDAO;

public class MenuDeleteAction implements MenuAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String msg = "삭제하였습니다.";
		int me_idx = request.getParameter("me_idx") == null ? 0 : Integer.parseInt(request.getParameter("me_idx"));
		
		MenuDAO mDao = MenuDAO.getInstance();
		int result = mDao.deleteMenu(me_idx);
		
		if( result == 0 ){
			msg = "삭제실패하였습니다.";
		}
		out.println(msg);
		out.close();
	}

}
