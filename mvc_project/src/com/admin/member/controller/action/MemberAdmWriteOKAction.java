package com.admin.member.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MemberDAO;
import com.dto.MemberVO;
import com.front.controller.action.Action;
import com.util.SHA256Util;
public class MemberAdmWriteOKAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		int mb_idx = request.getParameter("mb_idx") == null ? 0 : Integer.parseInt(request.getParameter("mb_idx"));
		String command = request.getParameter("command");
		
		String mb_id = request.getParameter("mb_id");
		String mb_name = request.getParameter("mb_name");
		String mb_email = request.getParameter("mb_email");
		String mb_pass = request.getParameter("mb_pass") == null ? "" : request.getParameter("mb_pass");
		String mb_salt = "";
		String mb_zip = request.getParameter("mb_zip") == null ? "" : request.getParameter("mb_zip");
		String mb_addr1 = request.getParameter("mb_addr1") == null ? "" : request.getParameter("mb_addr1");
		String mb_addr2 = request.getParameter("mb_addr2") == null ? "" : request.getParameter("mb_addr2");
		String mb_hp = request.getParameter("mb_hp") == null ? "" : request.getParameter("mb_hp");
		String mb_tel = request.getParameter("mb_tel") ==  null ? "" : request.getParameter("mb_tel");
		
		int mb_mailing = request.getParameter("mb_mailing") == null ? 0 : Integer.parseInt(request.getParameter("mb_mailing"));
		int mb_level = request.getParameter("mb_level") == null ? 0 : Integer.parseInt(request.getParameter("mb_level"));
				
		MemberDAO mDao = MemberDAO.getInstance();
		MemberVO mVo = new MemberVO();
		mVo.setMb_id(mb_id);
		mVo.setMb_name(mb_name);
		mVo.setMb_email(mb_email);
		
		if( !mb_pass.equals("") ){
			//// 회원가입시 비밀번호를 SHA-256 으로 SALT 이용해 암호화 하기
			mb_salt = SHA256Util.generateSalt();
			String new_mb_pass = SHA256Util.getEncrypt(mb_pass, mb_salt);
			mb_pass = new_mb_pass;
		}else{
			System.out.println("ddnononon");
		}
		
		mVo.setMb_pass(mb_pass);
		mVo.setMb_salt(mb_salt);
		mVo.setMb_zip(mb_zip);
		mVo.setMb_addr1(mb_addr1);
		mVo.setMb_addr2(mb_addr2);
		mVo.setMb_tel(mb_tel);
		mVo.setMb_hp(mb_hp);
		mVo.setMb_mailing(mb_mailing);
		mVo.setMb_level(mb_level);

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if( command.equals("write")){
			int result = 0;
			if( mDao.getMember(mVo.getMb_id()) == null ){
				result = mDao.insertMember(mVo);
			}
			if( result != 1 ){
				out.println("<script>");
				out.println("alert('입력실패');");
				out.println("window.history.go(-1);");
				out.println("</script>");
			}else{
				out.println("<script>");
				out.println("alert('입력완료');");
				out.println("window.location.href='list.do';");
				out.println("</script>");
			}
		}

		if( command.equals("update")){
			mVo.setMb_idx(mb_idx);
			System.out.println("입력::::비밀번호 ::"+mVo.getMb_pass());
			int result = mDao.updateMember(mVo);
			if( result != 1){
				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("window.history.go(-1);");
				out.println("</script>");
			}else{
				out.println("<script>");
				out.println("alert('수정완료');");
				out.println("window.location.href='write.do?mb_idx="+mb_idx+"'");
				out.println("</script>");
			}
		}
		out.close();
	}

}
