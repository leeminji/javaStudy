package com.front.board.controller.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardFrontWriteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//업로드
		String savePath = "/data";
		String uploadFilePath = request.getSession().getServletContext().getRealPath(savePath);
		int uploadFileSizeLimit = 5*1024*1024;

		System.out.println("서버 실제 디렉토리 : "+uploadFilePath);
		try{
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadFilePath,
					uploadFileSizeLimit,
					"UTF-8", 
					new DefaultFileRenamePolicy());
			
			//파라미터로 필요한 값 가져오기.
			String command = multi.getParameter("command"); //입력 수정 선택
			String bo_idx = multi.getParameter("bo_idx");
			String bo_table = multi.getParameter("bo_table");
			String bo_writer = multi.getParameter("bo_writer") == null ? "" : multi.getParameter("bo_writer");
			String bo_member = multi.getParameter("bo_member") == null ? "" : multi.getParameter("bo_member");
			String bo_subject = multi.getParameter("bo_subject") == null ? "" : multi.getParameter("bo_subject");
			String bo_content = multi.getParameter("bo_content") == null ? "" : multi.getParameter("bo_content");
			String bo_pass = multi.getParameter("bo_pass") == null ? "" : multi.getParameter("bo_pass");
			int bo_is_notice = multi.getParameter("bo_is_notice") == null ? 0 : Integer.parseInt(multi.getParameter("bo_is_notice"));
			int bo_is_view = 1;
			int bo_is_secret = multi.getParameter("bo_is_secret") == null ? 0 : Integer.parseInt(multi.getParameter("bo_is_secret"));
			int bo_ref = multi.getParameter("bo_ref") == null ? 0 : Integer.parseInt(multi.getParameter("bo_ref"));
			int bo_level = multi.getParameter("bo_level") == null ? 0 : Integer.parseInt(multi.getParameter("bo_level"));
			String bo_file = multi.getParameter("bo_file") == null ? "0" : multi.getParameter("bo_file");
			
			//bo_table 로 게시판 정보 불러오기.
			BoardOptionDao optionDao = BoardOptionDao.getInstance();
			BoardOptionVO optionVo = optionDao.getBoardOption(bo_table);
	
			//값을 Board DTO에 저장하기
			BoardVO bVo = new BoardVO();
			bVo.setBo_table(bo_table);
			bVo.setBo_writer(bo_writer);
			bVo.setBo_member(bo_member);
			bVo.setBo_subject(bo_subject);
			bVo.setBo_content(bo_content);
			bVo.setBo_pass(bo_pass);
			bVo.setBo_is_notice(bo_is_notice);
			bVo.setBo_is_secret(bo_is_secret);
			bVo.setBo_is_view(bo_is_view);
			bVo.setBo_ref(bo_ref);
			bVo.setBo_level(bo_level);
			bVo.setBo_file(bo_file);
			
			System.out.println("저장전"+bVo.toString());
			
			//회원 , 관리자 정보 ( 로그인 했을 경우 )
			MemberDAO mDao =MemberDAO.getInstance();
			HttpSession session = request.getSession();
			
			if( session.getAttribute("ss_mb_id") != null ){
				String mb_id = (String)session.getAttribute("ss_mb_id");
				MemberVO mVo = mDao.getMember(mb_id);
				
				//회원인경우 bo_writer, bo_member, bo_pass 자동입력
				bVo.setBo_member(mVo.getMb_id());
				bVo.setBo_writer(mVo.getMb_name());
				bVo.setBo_pass(mVo.getMb_pass());
			}
					
			//설정
			BoardDAO bDao = BoardDAO.getInstance();		
			System.out.println(bVo.toString());
	
			if( command.equals("write") || command.equals("reply")){
				int bo_step = bDao.getBoardReplyStep(bVo);
				bVo.setBo_step(bo_step+1);
				int result = bDao.insertBoard(bVo);
				
				if(result != 1){
					//입력실패
					request.setAttribute("message", "입력오류");
					String url = "/skin/"+optionVo.getOp_skin()+"/write.jsp";
					RequestDispatcher dispatcher = request.getRequestDispatcher(url);
					dispatcher.forward(request, response);
				}else{
					//파일업로드
					int file_count = 1;
					BoardFileDAO bFileDAO = BoardFileDAO.getInstance();
					int last_seq = bDao.getLastSequence(bVo.getBo_table());
					System.out.println("마지막 시퀀스값 : "+last_seq);
					
					Enumeration files = multi.getFileNames();
					while(files.hasMoreElements()){
						
						String file = (String) files.nextElement();
						String file_name = multi.getFilesystemName(file);
						
						//중복된 파일을 업로드한경우 파일명이 바뀐다.
						String ori_file_name = multi.getOriginalFileName(file);
						System.out.println("오리지널파일명:"+ori_file_name+"/파일명:"+file_name);
						if( ori_file_name != null ){
							BoardFileVO bfVo = new BoardFileVO();
							bfVo.setBo_table(bo_table);
							bfVo.setBo_idx(last_seq);
							bfVo.setBf_file(ori_file_name);
							bfVo.setBf_source(file_name);
							bfVo.setBf_no(file_count);
	
							//입력
							bFileDAO.insertFile(bfVo);
							file_count++;
						}
					}
					//카운트업데이트
					bDao.updateBoardFile(bVo.getBo_table(), last_seq, file_count-1);
					//입력완료 리스트로 이동
					response.sendRedirect("list.do?bo_table="+bo_table);
					return;
				}
			}
		
			//수정
			if( command.equals("update") ){
				bVo.setBo_idx(Integer.parseInt(bo_idx));
				int result = bDao.updateBoard(bVo);
				if(result != 1){
					request.setAttribute("message", "입력오류");
					String url = "/skin/"+optionVo.getOp_skin()+"/write.jsp";
					RequestDispatcher dispatcher = request.getRequestDispatcher(url);
					dispatcher.forward(request, response);
				}else{
					BoardFileDAO bFileDAO = BoardFileDAO.getInstance();
					
					//파일 삭제 선택시 삭제 - 데이터 삭제, 실제 파일삭제
					int del_count = 0;
					String bf_del[] = multi.getParameterValues("bf_del");
					if( bf_del != null ){

						for(String bf_source : bf_del){
							//파일 테이블 - 데이터 삭제
							bFileDAO.deleteFile(bo_table, bVo.getBo_idx(), bf_source);
							//실제 데이터 삭제
							String fileName = new String(bf_source.getBytes("ISO-8859-1"), "utf-8");
							File fileEx = new File(uploadFilePath+"\\"+fileName);

							if(fileEx.exists()){
								fileEx.delete();
							}
						}
						del_count = bf_del.length;
					}
					
					//파일업로드
					int file_count = 0;
					Enumeration files = multi.getFileNames();
					while(files.hasMoreElements()){
						String file = (String) files.nextElement();
						String file_name = multi.getFilesystemName(file);
						if( file_name != null ){
							//중복된 파일을 업로드한경우 파일명이 바뀐다.
							String ori_file_name = multi.getOriginalFileName(file);

							BoardFileVO bfVo = new BoardFileVO();
							bfVo.setBo_table(bo_table);
							bfVo.setBo_idx(bVo.getBo_idx());
							bfVo.setBf_file(ori_file_name);
							bfVo.setBf_source(file_name);
							bfVo.setBf_no(file_count+1);
	
							//입력
							bFileDAO.insertFile(bfVo);
							file_count++;
						}
					}
					
					int final_file_count = Integer.parseInt(bVo.getBo_file())-del_count+file_count;
					System.out.println("파일최종갯수 : " + final_file_count );
					bDao.updateBoardFile(bVo.getBo_table(), bVo.getBo_idx(), final_file_count );
					
					//수정성공
					response.sendRedirect("view.do?bo_table="+bo_table+"&bo_idx="+bo_idx);
				}
			}
		}catch (Exception e) {
			System.out.println("멀티 cos.jar 오류"+e.getMessage()+e.getStackTrace());
			e.printStackTrace();
		}
	}
}
