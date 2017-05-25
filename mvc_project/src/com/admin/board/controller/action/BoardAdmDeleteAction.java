package com.admin.board.controller.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.dao.BoardFileDAO;
import com.dto.BoardFileVO;

public class BoardAdmDeleteAction implements BoardAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//업로드
		String savePath = "/data";
		String uploadFilePath = request.getSession().getServletContext().getRealPath(savePath);
		String saveThumbPath = "/data/thumb";
		String uploadFileThumbPath = request.getSession().getServletContext().getRealPath(saveThumbPath);
		String saveFrontThumbPath = "/data/thumb";
		String uploadFileFrontThumbPath = request.getSession().getServletContext().getRealPath(saveFrontThumbPath);		
		BoardDAO bDao = BoardDAO.getInstance();
		BoardFileDAO fDao = BoardFileDAO.getInstance();
		
		String [] bo_idxs = request.getParameterValues("bo_idx");
		String bo_table = request.getParameter("bo_table");
		
		for( String bo_idx : bo_idxs){
			bDao.deleteBoard(bo_table , Integer.parseInt(bo_idx));
			
			ArrayList<BoardFileVO> fileList = fDao.getFileList(bo_table, Integer.parseInt(bo_idx));
			if( fileList != null ){
				for( BoardFileVO bfVo : fileList){
					//실제 데이터 삭제(썸네일삭제)
					String fileName = new String(bfVo.getBf_source().getBytes("ISO-8859-1"), "utf-8");
					File fileEx = new File(uploadFilePath+"\\"+fileName);
					File fileThumbEx = new File(uploadFileThumbPath+"\\"+fileName);
					File fileFrontThumbEx = new File(uploadFileFrontThumbPath+"\\"+fileName);
					if(fileEx.exists()){
						fileEx.delete();
					}
					if(fileThumbEx.exists()){
						fileThumbEx.delete();
					}	
					if(fileFrontThumbEx.exists()){
						fileFrontThumbEx.delete();
					}
				}
			}
			
			fDao.deleteFileAll(bo_table, Integer.parseInt(bo_idx)); //게시물에 속한 테이블 데이터 모두 삭제.
		}
		
		response.setContentType("text/html;charset=utf-8");					
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제되었습니다.');");
		out.println("location.href='list.do?bo_table="+ bo_table +"';");
		out.println("</script>");
		out.close();
	}

}
