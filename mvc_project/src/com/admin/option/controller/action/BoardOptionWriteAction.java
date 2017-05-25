package com.admin.option.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DirListUtil;

public class BoardOptionWriteAction implements BoardOptionAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/admin/setting/boardOption_write.jsp";

		//스킨 디렉토리
		DirListUtil admlist = new DirListUtil(request.getRealPath("/admin/skin/"));
		String [] adm_skin_list = admlist.getDirList();
		
		DirListUtil frontlist = new DirListUtil(request.getRealPath("/skin/"));
		String [] front_skin_list = frontlist.getDirList();
		
		request.setAttribute("admSkinList", adm_skin_list); //대시보드 스킨
		request.setAttribute("frontSkinList", front_skin_list); //사용자페이지 스킨
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);		
	}

}
