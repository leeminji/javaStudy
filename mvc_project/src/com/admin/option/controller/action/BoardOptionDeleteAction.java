package com.admin.option.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.dao.BoardOptionDao;
import com.dto.BoardOptionVO;

public class BoardOptionDeleteAction implements BoardOptionAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String [] op_idxs = request.getParameterValues("idx");
		BoardOptionDao optionDao = BoardOptionDao.getInstance();
		
		BoardDAO bDao = BoardDAO.getInstance(); //게시판 Dao
		

		for(String op_idx : op_idxs){
			BoardOptionVO optionVo = optionDao.getBoardOption(Integer.parseInt(op_idx));

			bDao.dropBoard(optionVo.getOp_table()); //만들어진 게시판 삭제
			bDao.dropBoardSeq(optionVo.getOp_table()); //만들어진 게시판 시퀀스 삭제

			int result = optionDao.deleteBoardOption(Integer.parseInt(op_idx));
			if( result != 1) break;
		}
		
		response.sendRedirect(request.getContextPath()+"/adm/boardOption/list.do");
	}

}
