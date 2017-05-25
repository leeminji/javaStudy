package com.admin.option.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.dao.BoardOptionDao;
import com.dto.BoardOptionVO;

public class BoardOptionWriteOKAction implements BoardOptionAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String command = request.getParameter("command");
		
		if( command != null && !command.equals("") ){
			int op_idx = request.getParameter("op_idx") == null ? 0 : Integer.parseInt(request.getParameter("op_idx"));
			String op_name = request.getParameter("op_name");
			String op_table = request.getParameter("op_table");
			String op_skin = request.getParameter("op_skin");
			String op_adm_skin = request.getParameter("op_adm_skin");
			
			int op_is_secret = request.getParameter("op_is_secret") == null ? 0 : 1;
			int op_is_ip = request.getParameter("op_is_ip") == null ? 0 : 1;
			int op_is_sign = request.getParameter("op_is_sign") == null ? 0 : 1;
			int op_new_date = request.getParameter("op_new_date") == null ? 24 : Integer.parseInt(request.getParameter("op_new_date"));
	
			int op_view_level = request.getParameter("op_view_level") == null ? 1 : Integer.parseInt(request.getParameter("op_view_level"));
			int op_list_level = request.getParameter("op_list_level") == null ? 1 : Integer.parseInt(request.getParameter("op_list_level"));
			int op_write_level = request.getParameter("op_write_level") == null ? 1 : Integer.parseInt(request.getParameter("op_write_level"));
			int op_comment_level = request.getParameter("op_comment_level") == null ? 1 : Integer.parseInt(request.getParameter("op_comment_level"));
			int op_reply_level = request.getParameter("op_reply_level") == null ? 1 : Integer.parseInt(request.getParameter("op_reply_level"));
			
			int op_page_length = request.getParameter("op_page_length") == null ? 10 : Integer.parseInt(request.getParameter("op_page_length"));
			int op_is_preview = request.getParameter("op_is_preview") == null ? 0 : 1;
			int op_is_notice = request.getParameter("op_is_notice") == null ? 0 : 1;
	
			int op_img_width = request.getParameter("op_img_width") == null ? 0 : Integer.parseInt(request.getParameter("op_img_width"));
			int op_thumb_width = request.getParameter("op_thumb_width") == null ? 0 : Integer.parseInt(request.getParameter("op_thumb_width"));
			int op_thumb_height = request.getParameter("op_thumb_height") == null ? 0 : Integer.parseInt(request.getParameter("op_thumb_height"));
			
			String op_thumb = op_thumb_width+";"+op_thumb_height;
			String op_cate = request.getParameter("op_cate");
			
			BoardOptionVO optionVo = new BoardOptionVO();
			
			optionVo.setOp_idx(op_idx);
			optionVo.setOp_name(op_name);
			optionVo.setOp_table(op_table);
			optionVo.setOp_skin(op_skin);
			optionVo.setOp_adm_skin(op_adm_skin);
			optionVo.setOp_is_secret(op_is_secret);
			optionVo.setOp_is_ip(op_is_ip);
			optionVo.setOp_is_sign(op_is_sign);
			optionVo.setOp_new_date(op_new_date);
			optionVo.setOp_view_level(op_view_level);
			optionVo.setOp_list_level(op_list_level);
			optionVo.setOp_write_level(op_write_level);
			optionVo.setOp_comment_level(op_comment_level);
			optionVo.setOp_reply_level(op_reply_level);
			optionVo.setOp_page_length(op_page_length);
			optionVo.setOp_is_preview(op_is_preview);
			optionVo.setOp_is_notice(op_is_notice);
			optionVo.setOp_cate(op_cate);
			optionVo.setOp_img_width(op_img_width);
			optionVo.setOp_thumb(op_thumb);
			
			//입력 수행
			BoardOptionDao optionDao = BoardOptionDao.getInstance();
			
			if( command.equals("update")){
				System.out.println("[UPDATE]"+optionVo.toString());
				int result = optionDao.updateBoardOption(optionVo);
				
				out.println("<script>");
				if( result == 0){
					//결과가 0인경우 오류 발생
					out.println("alert('수정시 오류가 생겼습니다.');");
				}else{
					out.println("alert('수정하였습니다.');");
				}
				out.print("location.href='update.do?op_idx="+op_idx+"';");
				out.println("</script>");
			}
			
			if(command.equals("write")){
				int result = optionDao.insertBoardOption(optionVo);

				if( result == 0){
					//결과가 0인경우 오류 발생
					String url = "/admin/setting/boardOption_write.jsp";
					request.setAttribute("message", "입력 : 오류가 생겼습니다.");
					
					RequestDispatcher dispatcher = request.getRequestDispatcher(url);
					dispatcher.forward(request, response);							
					
				}else{
					BoardDAO boardDao = BoardDAO.getInstance();
					boardDao.createBoard(optionVo.getOp_table()); //게시판 테이블 생성
					boardDao.createBoardSeq(optionVo.getOp_table()); //게시판 시퀀스생성
					
					//결과가 1이면 정상 입력
					response.sendRedirect(request.getContextPath()+"/admin/boardOption/list.do?bo_table="+optionVo.getOp_table());
				}
			}
		}
		out.close();
	}

}
