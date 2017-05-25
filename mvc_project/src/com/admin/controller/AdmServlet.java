package com.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDAO;
import com.dto.MemberVO;
import com.front.controller.BoardFrontActionFactory;
import com.front.controller.MemberActionFactory;

@WebServlet("/adm/*")
public class AdmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdmServlet() {
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
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		String[] path = command.split("/");
		
		//경로
		request.setAttribute("current_path", command);
		
		//세션확인
		HttpSession session = request.getSession();

		//로그인전
		if( session.getAttribute("ss_admin_id") != null){
			String admin_id = (String)session.getAttribute("ss_admin_id");
			
			ServletContext application = getServletConfig().getServletContext();
			
			//관리자정보 저장
			if( application.getAttribute("adminVo") == null ){
				MemberDAO mDao = MemberDAO.getInstance();
				MemberVO adminVo = mDao.getMember(admin_id);
				application.setAttribute("adminVo", adminVo);
			}

			if (path[2].equals("member")) {
				AdmMemberActionFactory factory = AdmMemberActionFactory.getInstance();
				factory.getAction(command).execute(request, response);
			} else if (path[2].equals("login")) {
				AdmLoginActionFactory factory = AdmLoginActionFactory.getInstance();
				factory.getAction(command).execute(request, response);
			} else if (path[2].equals("main")) {
				AdmMainActionFactory factory = AdmMainActionFactory.getInstance();
				factory.getAction(command).execute(request, response);
			} else if (path[2].equals("board")) {
				AdmBoardActionFactory factory = AdmBoardActionFactory.getInstance();
				factory.getBoardAction(command).execute(request, response);
			} else if (path[2].equals("config")) {
				AdmConfigActionFactory factory = AdmConfigActionFactory.getInstance();
				factory.getConfigAction(command).execute(request, response);
			} else if (path[2].equals("menu")) {
				AdmMenuActionFactory factroy = AdmMenuActionFactory.getInstance();
				factroy.getMenuAction(command).execute(request, response);
			} else if (path[2].equals("boardOption")) {
				AdmBoardOptionActionFactory factory = AdmBoardOptionActionFactory.getInstacne();
				factory.getBoardOptionAction(command).execute(request, response);
			} else if (path[2].equals("popup")) {
				AdmPopupOptionFactory factory = AdmPopupOptionFactory.getInstacne();
				factory.getPopupOption(command).execute(request, response);
			} else if (path[2].equals("calendar")) {
				AdmCalendarOptionFactory factory = AdmCalendarOptionFactory.getInstacne();
				factory.getCalendarOption(command).execute(request, response);
			}
		}else{
			AdmLoginActionFactory factory = AdmLoginActionFactory.getInstance();
			factory.getAction(command).execute(request, response);
		}
	}
}
