package com.admin.board.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.dao.BoardFileDAO;
import com.dao.BoardOptionDao;
import com.dto.BoardFileVO;
import com.dto.BoardOptionVO;
import com.dto.BoardVO;

public class BoardAdmWriteAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String bo_table = request.getParameter("bo_table");
		String bo_idx = request.getParameter("bo_idx");
		
		//bo_table 로 게시판 정보 불러오기.
		BoardOptionDao optionDao = BoardOptionDao.getInstance();
		BoardOptionVO optionVo = optionDao.getBoardOption(bo_table);
		request.setAttribute("optionVo", optionVo);
	
		//수정
		if( bo_idx != null){
			BoardDAO bDao = BoardDAO.getInstance();
			BoardVO bVo = bDao.getBoard(optionVo.getOp_table() , Integer.parseInt(bo_idx)); //해당 bo_idx로 게시물 정보 가져오기.
			
			//해당 bo_idx로 게시물 정보 넣기
			request.setAttribute("bVo", bVo);
			request.setAttribute("update", true);
			
			BoardFileDAO fileDAO = BoardFileDAO.getInstance();
			ArrayList<BoardFileVO> fileList = fileDAO.getFileList(bo_table, bVo.getBo_idx());
			
			request.setAttribute("fileList", fileList);
		}
	
		String url = "/admin/skin/"+optionVo.getOp_adm_skin()+"/write.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);				
		

	}

}
