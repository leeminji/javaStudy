package com.admin.login.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import com.dao.LoginDAO;
import com.dao.MemberDAO;
import com.dao.VisitDAO;
import com.dto.LoginVO;
import com.dto.MemberVO;
import com.dto.VisitVO;
import com.util.ClientUtil;
import com.util.CookieUtil;
import com.util.IpUtil;

public class AdmLoginOKAction implements AdmAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		
		String mb_id = request.getParameter("mb_id");
		String mb_pass = request.getParameter("mb_pass");
		MemberVO adminVO = null;
		System.out.println("loginOk");
		//result 0 : 아이디 없는경우
		//result 1 : 아이디 있고 비밀번호 맞는경우(로그인성공)
		//result -1 : 비밀번호가 아닌경우(로그인실패)
		
		MemberDAO mDao = MemberDAO.getInstance();
		int result = mDao.getLogin(mb_id, mb_pass);
		
		if( result != 1 ){
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자만 로그인 가능합니다.');");
			out.println("location.href='"+request.getContextPath()+"/pf/index.do';");
			out.println("</script>");
			out.close();
		}else{
			//어드민 멤버 정보 세션에 저장
			HttpSession session = request.getSession();
			adminVO = mDao.getMember(mb_id);
			
			//회원 레벨이 관리자 이면 로그인 성공 / 세션 생성
			if( adminVO.getMb_level() == 10 ){
				
				//회원정보에 최근 접속일 업데이트
				mDao.updateLastestDate(adminVO.getMb_idx());

				//세션 추가
				session.setAttribute("ss_admin_id", adminVO.getMb_id());
				String admin_visit_ip = IpUtil.getInstance().getClientIP(request);
				
				System.out.println(adminVO.getMb_id());
				//쿠키설정
				CookieUtil cUtil = CookieUtil.getInstance();
				Cookie ck_admin_visit_ip = cUtil.getCookie(request.getCookies(), "ck_admin_visit_ip");
	
				if( ck_admin_visit_ip == null  ){
					ck_admin_visit_ip = cUtil.setCookie("ck_admin_visit_ip", URLEncoder.encode(admin_visit_ip, "utf-8"), 60*60*24);
					response.addCookie(ck_admin_visit_ip);
					System.out.println("쿠키생성 :"+ck_admin_visit_ip.getValue());
					
					//로그인 정보 넣기
					LoginVO lVo = new LoginVO();
					lVo.setLo_ip(admin_visit_ip);
					lVo.setLo_url(request.getRequestURI());
					try {
						lVo.setLo_location( ClientUtil.getClientWebVer(request) );
					} catch (Exception e) {
						e.printStackTrace();
					}
					LoginDAO lDao = LoginDAO.getInstance();
					lDao.insertLogin(lVo);
					
					//접속 방문 정보 넣기
					VisitDAO vDao = VisitDAO.getInstance();
					VisitVO vVo = new VisitVO();
					vVo.setVi_ip(admin_visit_ip);
					try {
						vVo.setVi_brower(ClientUtil.getClientWebVer(request));
						vVo.setVi_os(ClientUtil.getClientWebVer(request));
						vVo.setVi_device(ClientUtil.getClinetDevice(request));
					} catch (Exception e) {
						e.printStackTrace();
					}
					vDao.inertVisit(vVo);
				}
				
				//대시보드 메인으로 이동
				response.sendRedirect(request.getContextPath()+"/adm/main");
				return;
			//회원 레벨이 관리자가 아니면 실패	
			}else{
				session.setAttribute("ss_is_adm_login", false);
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('관리자만 로그인 가능합니다.');");
				out.println("</script>");
				out.close();
				response.sendRedirect(request.getContextPath()+"/pf/index.do");
			}
		}
		
	}

}
