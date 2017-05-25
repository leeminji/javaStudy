package com.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.board.controller.action.BoardAction;
import com.dao.BoardFileDAO;
import com.dto.BoardFileVO;

public class BoardAdmDownloadAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//업로드 파일경로
		String savePath = "/data/";
		String uploadFilePath = request.getSession().getServletContext().getRealPath(savePath);	
		
		// 파일 다운로드
		String bf_source = request.getParameter("bf_source"); //저장된 파일이름
		String bf_file = request.getParameter("bf_file"); //내보낼 파일이름
		
		BoardFileDAO fileDAO = BoardFileDAO.getInstance();
		InputStream in = null;
		File file = null;
		OutputStream os = null;
		boolean skip = true;
		PrintWriter out = response.getWriter();
		
		try{
			if( bf_source != null ){
				//다운로드 업데이트
				fileDAO.updateDownload(bf_source);
				try{
					file = new File(uploadFilePath, bf_source);
					in = new FileInputStream(file);
				}catch (FileNotFoundException fe) {
					skip = false;
					fe.printStackTrace();
					System.out.println(fe.getMessage()+"파일다운로드 에러");
				}
				
				String client = request.getHeader("User-Agent");
				
				//파일 다운로드 헤더 지정
				response.reset();
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Description", "JSP Generated Data");
				
				if(skip){
					if( client.indexOf("MSIE") != -1 ){
						response.setHeader("Content-Disposition", "attachment; filename="+new String(bf_source.getBytes("KSC5601"),"UTF-8"));
					}else{
						String fileName = new String(bf_source.getBytes("iso-8859-1"),"UTF-8");
						response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
						response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
					}
					response.setHeader ("Content-Length", "" + file.length() );
					os = response.getOutputStream();
					byte b[] = new byte[(int)file.length()];
					int leng = 0;
		
					while( (leng = in.read(b)) > 0 ){
						os.write(b,0,leng);
					}
				}else{
					response.setContentType("text/html;charset=UTF-8");
					out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
				}
				in.close();
				os.close();
				out.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage()+"파일 다운로드 에러에러");
		}
	}
}
