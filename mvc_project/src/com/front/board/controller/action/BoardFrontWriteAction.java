package com.front.board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDAO;
import com.dao.BoardOptionDao;
import com.dao.MemberDAO;
import com.dto.BoardOptionVO;
import com.dto.BoardVO;
import com.dto.MemberVO;
import com.front.controller.action.Action;

public class BoardFrontWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bo_table = request.getParameter("bo_table");
		String bo_idx = request.getParameter("bo_idx");
		
		//bo_table 로 게시판 정보 불러오기.
		BoardOptionDao optionDao = BoardOptionDao.getInstance();
		BoardOptionVO optionVo = optionDao.getBoardOption(bo_table);
		request.setAttribute("optionVo", optionVo);
		
		//세션 가지고오기
		HttpSession session = request.getSession();
		int mb_level = 1;
		MemberVO mVo = null;
		if( session.getAttribute("ss_mb_id") != null ){
			//회원인경우
			String mb_id = (String)session.getAttribute("ss_mb_id");
			MemberDAO mDao = MemberDAO.getInstance();
			mVo = mDao.getMember(mb_id);
			//회원 레벨 저장
			mb_level = mVo.getMb_level();
			request.setAttribute("mVo", mVo);
		}else{
			//비회원인경우
			mb_level = 1;
		}
		
		//게시판 권한
		if( optionVo.getOp_write_level() > mb_level ){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('접근할 수 있는 권한이 없습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}

		String url = "/skin/"+optionVo.getOp_skin()+"/write.jsp";
		//수정
		if( bo_idx != null){
			
			BoardDAO bDao = BoardDAO.getInstance();
			BoardVO bVo = bDao.getBoard(optionVo.getOp_table() , Integer.parseInt(bo_idx)); //해당 bo_idx로 게시물 정보 가져오기.
			
			
			//해당 bo_idx로 게시물 정보 넣기
			request.setAttribute("bVo", bVo);
			System.out.println(bVo.toString());
			request.setAttribute("update", true);
			
		}
		
		//View 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);				
	
	}

}
