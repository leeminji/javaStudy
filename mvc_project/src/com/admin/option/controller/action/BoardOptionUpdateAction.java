package com.admin.option.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardOptionDao;
import com.dto.BoardOptionVO;
import com.util.DirListUtil;

public class BoardOptionUpdateAction implements BoardOptionAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "/admin/setting/boardOption_write.jsp"; //게시판 관리 정보 수정
		
		//idx 가 없는 경우
		String op_idx = request.getParameter("op_idx");
		if( op_idx == null || op_idx.equals("") ){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		
		BoardOptionDao optionDao = BoardOptionDao.getInstance();
		BoardOptionVO optionVo = optionDao.getBoardOption(Integer.parseInt(op_idx));
		
		//스킨 디렉토리
		DirListUtil admlist = new DirListUtil(request.getRealPath("/admin/skin/"));
		String [] adm_skin_list = admlist.getDirList();
		
		DirListUtil frontlist = new DirListUtil(request.getRealPath("/skin/"));
		String [] front_skin_list = frontlist.getDirList();
		
		request.setAttribute("admSkinList", adm_skin_list); //대시보드 스킨
		request.setAttribute("frontSkinList", front_skin_list); //사용자페이지 스킨
	
		if( optionVo != null ){
			request.setAttribute("update", true);
			request.setAttribute("optionVo", optionVo);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
