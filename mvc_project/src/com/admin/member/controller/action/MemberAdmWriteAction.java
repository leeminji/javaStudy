package com.admin.member.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ConfigDAO;
import com.dao.MemberDAO;
import com.dto.ConfigVO;
import com.dto.MemberVO;
import com.front.controller.action.Action;
public class MemberAdmWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/admin/setting/member_write.jsp";
		
		ConfigDAO cDao = ConfigDAO.getInstance();
		ConfigVO cVo = cDao.getConfig();
		
		//환경설정
		request.setAttribute("cVo", cVo);
		
		int mb_idx = request.getParameter("mb_idx") == null ? 0 : Integer.parseInt(request.getParameter("mb_idx"));
		if( mb_idx != 0){
			//수정인 경우
			MemberDAO mDao = MemberDAO.getInstance();
			MemberVO mVo = mDao.getMember(mb_idx);
			
			request.setAttribute("member", mVo);
			request.setAttribute("update", true);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
