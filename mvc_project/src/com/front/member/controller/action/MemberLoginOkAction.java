package com.front.member.controller.action;

import java.io.IOException;
import java.security.PrivateKey;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.media.jai.codecimpl.util.DataBufferUtils;
import com.dao.MemberDAO;
import com.dto.MemberVO;
import com.front.controller.action.Action;
public class MemberLoginOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 세션 검색
		HttpSession session = request.getSession();
		
		MemberDAO mDao = MemberDAO.getInstance();
		MemberVO mVo = null;
		
		String url = "/login/loginForm.jsp"; //로그인 폼경로
		String mb_id = request.getParameter("mb_id");//아이디
		String mb_pass = request.getParameter("mb_pass"); //비밀번호
		String encPw = request.getParameter("encPw");
		

		int result = 0; //로그인 결과 저장 변수
		
		//mb_id 값과 mb_pass 값이 널값이 아니여야만 실행
		if( mb_id != null && mb_pass != null){
			result = mDao.getLogin(mb_id, mb_pass);
		}
		
		switch (result){
			case 0 :
				//아이디가 없는 경우 : 로그인 실패
			case -1:
				//비밀번호가 맞지 않는경우 : 로그인실패
				request.setAttribute("message", "아이디가 존재하지 않거나 비밀번호가 맞지 않습니다. 다시한번 확인해주세요.");
				//view로 이동
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);			
				break;	
			case 1 : 
				
				System.out.println("로그인성공"+mb_id);
				//로그인성공
				mVo = mDao.getMember(mb_id);
				
				// 회원아이디 세션 생성.
				session.setAttribute("ss_mb_id", mVo.getMb_id());
				session.setAttribute("mVo", mVo);
				
				//메인페이지로 이동
				String contextPath = request.getContextPath();
				response.sendRedirect(contextPath+"/pf/index.do");
				break;
		}
	}

}
