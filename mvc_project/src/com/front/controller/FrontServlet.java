package com.front.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;

import com.admin.controller.AdminActionFactory;
import com.dao.ConfigDAO;
import com.dao.MemberDAO;
import com.dao.MenuDAO;
import com.dto.ConfigVO;
import com.dto.MemberVO;
import com.dto.MenuVO;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/pf/*")
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//세션확인
		HttpSession session = request.getSession();
		
		//로그인전
		if( session.getAttribute("ss_mb_id") != null){
			String mb_id = (String)session.getAttribute("ss_mb_id");
			System.out.println(mb_id);
			//멤버 저장
			if( session.getAttribute("mVo") == null ){
				MemberDAO mDao = MemberDAO.getInstance();
				MemberVO adminVo = mDao.getMember(mb_id);
				session.setAttribute("mVo", adminVo);
			}
		}
		
		//어플리케이션 설정
		ServletContext application = getServletConfig().getServletContext();
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		String [] path = command.split("/");
		
		MenuDAO mDao = MenuDAO.getInstance();
		
		//메뉴 me_is_view = 1인것만 보임.
		ArrayList<MenuVO> menuList = mDao.getFrontMenuList();
		application.setAttribute("menuList", menuList);
		
		//설정된 정보
		ConfigDAO cDao = ConfigDAO.getInstance();
		ConfigVO configVo = cDao.getConfig();
		application.setAttribute("configVo", configVo);

		//쿼리 데이터
		request.setAttribute("current_path", command);
		request.setAttribute("query_bo_table", request.getParameter("bo_table"));

		if( path[2].equals("index.do") ){
			PageActionFactory factory = PageActionFactory.getInstance();
			factory.getPageAction(command).execute(request, response);
		}else if( path[2].equals("member")){
			//회원
			MemberActionFactory factory = MemberActionFactory.getInstance();
			factory.getAction(command).execute(request, response);
		}else if(path[2].equals("board")){
			//게시판
			BoardFrontActionFactory factory = BoardFrontActionFactory.getInstance();
			factory.getBoardAction(command).execute(request, response);
		}else if(path[2].equals("page")){
			//페이지
			PageActionFactory factory = PageActionFactory.getInstance();
			factory.getPageAction(command).execute(request, response);
		}
	}
}
