package com.front.member.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.SHA256Util;
import com.dao.MemberDAO;
import com.dto.MemberVO;
import com.front.controller.action.Action;
public class MemberJoinOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/login/memberForm.jsp";
		MemberDAO mDao = MemberDAO.getInstance();
		
		String mb_id = request.getParameter("mb_id");
		String mb_name = request.getParameter("mb_name");
		String mb_email = request.getParameter("mb_email");
		String mb_pass = request.getParameter("mb_pass");
		
		//// 회원가입시 비밀번호를 SHA-256 으로 SALT 이용해 암호화 하기
		String mb_salt = SHA256Util.generateSalt();
		String new_mb_pass = SHA256Util.getEncrypt(mb_pass, mb_salt);
		mb_pass = new_mb_pass;

		int mb_mailing = request.getParameter("mb_mailing") == null ? 0 : 1;
		String mb_addr1 = request.getParameter("mb_addr1") == null ? "" : request.getParameter("mb_addr1");
		String mb_addr2 = request.getParameter("mb_addr2") == null ? "" : request.getParameter("mb_addr2");
		String mb_zip = request.getParameter("mb_zip") == null ? "" : request.getParameter("mb_zip");
		String mb_hp = request.getParameter("mb_hp") == null ? "" : request.getParameter("mb_hp");
		String mb_tel = request.getParameter("mb_tel") == null ? "" : request.getParameter("mb_tel");
		
		MemberVO mVo = new MemberVO();
		mVo.setMb_id(mb_id);
		mVo.setMb_name(mb_name);
		mVo.setMb_email(mb_email);
		mVo.setMb_pass(mb_pass);
		mVo.setMb_mailing(mb_mailing);
		mVo.setMb_addr1(mb_addr1);
		mVo.setMb_addr2(mb_addr2);
		mVo.setMb_hp(mb_hp);
		mVo.setMb_tel(mb_tel);
		mVo.setMb_level(2);
		mVo.setMb_zip(mb_zip);
		mVo.setMb_salt(mb_salt);

		//아이디가 중복되면 메세지 전송.
		if( mDao.getMember(mVo.getMb_id()) == null ){
			int reslut = mDao.insertMember(mVo);
			if( reslut == 1){
				//reslut 값이 1이면 회원가입 성공
				url = "/login/joinFin.jsp";
			}else{
				//reslut 값이 0 이면 회원가입 실패
				request.setAttribute("member", mVo);
				request.setAttribute("message", "가입에 실패하였습니다. 다시 시도해주세요.");
			}
		}else{
			request.setAttribute("message", "이미 가입된 아이디 입니다.");
		}
		request.setAttribute("title", " 회원가입완료");
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);		
	}

}
