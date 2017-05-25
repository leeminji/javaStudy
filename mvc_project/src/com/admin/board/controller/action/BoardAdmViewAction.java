package com.admin.board.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDAO;
import com.dao.BoardFileDAO;
import com.dao.BoardOptionDao;
import com.dto.BoardFileVO;
import com.dto.BoardOptionVO;
import com.dto.BoardVO;
import com.util.ThumbNail;

public class BoardAdmViewAction implements BoardAction {

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
		
		//옵션값에 해당하는 스킨으로 설정.
		String url = "/admin/skin/"+optionVo.getOp_adm_skin()+"/view.jsp";
		
		if( optionVo != null && bo_idx != null){
			
			//스킨설정
			url = "/admin/skin/"+optionVo.getOp_adm_skin()+"/view.jsp";
			
			//게시판 정보설정
			request.setAttribute("optionVo", optionVo); 
			
			BoardDAO bDao = BoardDAO.getInstance();
			BoardVO bVo = bDao.getBoard(optionVo.getOp_table(), Integer.parseInt(bo_idx)); 
			
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
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
