package com.front.member.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDAO;
import com.dto.MemberVO;
import com.front.controller.action.Action;
public class MemberUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//타이틀 설정
		String title = (String)request.getAttribute("title");
		request.setAttribute("title", title+">회원정보수정");
		
		String url = "/login/memberCheckPass.jsp";
		String mb_pass = request.getParameter("mb_pass") == null ? "" : request.getParameter("mb_pass");
		String mb_id = request.getParameter("mb_id") == null ? "" : request.getParameter("mb_id");
		
		HttpSession session = request.getSession();
		if( session.getAttribute("ss_mb_id") != null ){
			
			MemberDAO mDao = MemberDAO.getInstance();
			MemberVO mVo = mDao.getMember(mb_id);
	
			if( mb_pass != null && !mb_pass.equals("") ){
				//비밀번호 확인
				int result = mDao.getLogin(mVo.getMb_id(), mb_pass);
				if( result > 0 ){
					url = "/login/memberForm.jsp";
					request.setAttribute("is_update", true);
				}else{
					request.setAttribute("message", "비밀번호가 다릅니다.");
				}
			}
			
			//회원 정보
			request.setAttribute("mVo", mVo);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/pf/member/login.do");
		}
	}

}
