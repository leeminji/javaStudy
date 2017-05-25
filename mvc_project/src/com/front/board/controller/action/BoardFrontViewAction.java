package com.front.board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDAO;
import com.dao.BoardFileDAO;
import com.dao.BoardOptionDao;
import com.dao.MemberDAO;
import com.dto.BoardFileVO;
import com.dto.BoardOptionVO;
import com.dto.BoardVO;
import com.dto.MemberVO;
import com.front.controller.action.Action;
import com.sun.mail.auth.MD4;

public class BoardFrontViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//업로드
		String savePath = "/data/";
		String uploadFilePath = request.getSession().getServletContext().getRealPath(savePath);	
		request.setAttribute("uploadFilePath", uploadFilePath);

		//bo_idx, bo_table를  request 파라미터로 값을 받는다.
		String bo_table = request.getParameter("bo_table");
		String bo_idx = request.getParameter("bo_idx");
		
		//board 옵션 값을 가지고 온다.
		BoardOptionDao optionDao = BoardOptionDao.getInstance();
		BoardOptionVO optionVo = optionDao.getBoardOption(bo_table);
		MemberVO mVo = null;
		
		//세션 가지고오기
		HttpSession session = request.getSession();
		int mb_level = 1;
		if( session.getAttribute("ss_mb_id") != null ){
			MemberDAO mDao = MemberDAO.getInstance();
			//회원인경우
			mVo = mDao.getMember((String)session.getAttribute("ss_mb_id"));
			//회원 레벨 저장
			mb_level = mVo.getMb_level();
		}else{
			//비회원인경우
			mb_level = 1;
		}
		
		//게시판 권한
		if( optionVo.getOp_view_level() > mb_level ){
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('접근할 수 있는 권한이 없습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}
		
		//쓰기 권한(버튼유무)
		boolean is_write = false;
		if( optionVo.getOp_write_level() <= mb_level ) is_write = true;
		request.setAttribute("is_write", is_write);

		//답변 권한(버튼유무)
		boolean is_reply = false;
		if( optionVo.getOp_reply_level() <= mb_level ) is_reply = true;
		request.setAttribute("is_reply", is_reply);
		
		//옵션값에 해당하는 스킨으로 설정.
		String url = "/skin/"+optionVo.getOp_skin()+"/view.jsp";
		
		if( optionVo != null && bo_idx != null){
			
			//스킨설정
			url = "/skin/"+optionVo.getOp_skin()+"/view.jsp";
			
			//게시판 정보설정
			request.setAttribute("optionVo", optionVo); 
						
			BoardDAO bDao = BoardDAO.getInstance();
			BoardVO bVo = bDao.getBoard(optionVo.getOp_table(), Integer.parseInt(bo_idx)); 
			
			if( bVo.getBo_content() != null ){
				bVo.setBo_content(bVo.getBo_content().replace("\r\n","<br>"));
			}
			//비밀글인경우 비밀번호확인
			if( bVo.getBo_is_secret() == 1 ){
				
				//로그인 상황인경우
				if( mVo != null){
					
					//자신의 글에 답변이 달린경우 확인가능.
					BoardVO pVo = bDao.getBoard(bo_table, bVo.getBo_ref());
					boolean is_my_board_reply = pVo.getBo_member() == null ? false : pVo.getBo_member().equals(mVo.getMb_id());
					boolean is_my_board = bVo.getBo_member() == null ? false : bVo.getBo_member().equals(mVo.getMb_id());
					
					//글쓴이 본인인 경우 확인가능 , 관리자인경우 가능
					if( is_my_board_reply || mVo.getMb_level() == 10 || is_my_board ){
					}else{
						response.sendRedirect("boardCheckPass.do?command=secret&bo_table="+ bo_table +"&bo_idx="+bo_idx);
						return;
					}
				}else{
				//비회원인 경우
					//비밀글 세션 확인
					if( session.getAttribute("ss_secret_"+bo_table+"_"+bVo.getBo_ref()) == null ){
						response.sendRedirect("boardCheckPass.do?command=secret&bo_table="+ bo_table +"&bo_idx="+bo_idx);
						return;
					}
				}
				
			}
			
			//view 세션설정
			String ss_view = "ss_view_"+bVo.getBo_table()+"_"+bVo.getBo_idx();
			if( session.getAttribute(ss_view) == null ){
				session.setAttribute(ss_view , true);
				bDao.setBoardCount(optionVo.getOp_table(), Integer.parseInt(bo_idx));
				bVo.setBo_hit(bVo.getBo_hit()+1);
				System.out.println("ss_view"+ss_view);
			}
			//게시물 정보설정
			request.setAttribute("bVo", bVo); 
			
			//파일
			if( bVo.getBo_file() != null && Integer.parseInt(bVo.getBo_file()) > 0){
				BoardFileDAO fileDAO = BoardFileDAO.getInstance();
				ArrayList<BoardFileVO> fileList = fileDAO.getFileList(bo_table, Integer.parseInt(bo_idx));
				ArrayList<BoardFileVO> fileImgList = fileDAO.getFileImgList(bo_table, Integer.parseInt(bo_idx));
				
				request.setAttribute("fileList", fileList);
				request.setAttribute("fileImgList", fileImgList);
			}
			request.setAttribute("title", optionVo.getOp_name()+">"+ bVo.getBo_subject());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
