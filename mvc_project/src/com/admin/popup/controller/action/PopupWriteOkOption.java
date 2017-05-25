package com.admin.popup.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PopupDAO;
import com.dto.PopupVO;
import com.util.CookieUtil;

public class PopupWriteOkOption implements PopupOption {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int po_idx = request.getParameter("po_idx") == null ? 0 : Integer.parseInt(request.getParameter("po_idx"));
		String po_device = request.getParameter("po_device") == null ? "" : request.getParameter("po_device");
		String po_begin_time = request.getParameter("po_begin_time") == null ? "00:00:00" : request.getParameter("po_begin_time")+":00";
		String po_end_date = request.getParameter("po_end_date") == null ? "" : request.getParameter("po_end_date");
		String po_begin_date = request.getParameter("po_begin_date") == null ? "" : request.getParameter("po_begin_date");
		String po_end_time = request.getParameter("po_end_time") == null ? "00:00:00" : request.getParameter("po_end_time")+":00";
		int po_disable_hours = request.getParameter("po_disable_hours") == null ? 0 : Integer.parseInt(request.getParameter("po_disable_hours"));
		int po_left  = request.getParameter("po_left") == null ? 0 : Integer.parseInt(request.getParameter("po_left"));
		int po_top = request.getParameter("po_top") == null ? 0 : Integer.parseInt(request.getParameter("po_top"));
		int po_height = request.getParameter("po_height") == null ? 0 : Integer.parseInt(request.getParameter("po_height"));
		int po_width = request.getParameter("po_width") == null ? 0 : Integer.parseInt(request.getParameter("po_width"));
		String po_subject = request.getParameter("po_subject") == null ? "" : request.getParameter("po_subject");
		String po_content = request.getParameter("po_content") == null ? "" : request.getParameter("po_content");
		
		String command = request.getParameter("command")== null ? "" : request.getParameter("command");

		PopupVO pVo = new PopupVO();
		PopupDAO pDao = PopupDAO.getInstance();
		

		pVo.setPo_idx(po_idx);
		pVo.setPo_device(po_device);
		pVo.setPo_begin_time(Timestamp.valueOf(po_begin_date +" "+ po_begin_time));
		pVo.setPo_end_time(Timestamp.valueOf(po_end_date +" "+ po_end_time));
		pVo.setPo_disable_hours(po_disable_hours);
		pVo.setPo_left(po_left);
		pVo.setPo_top(po_top);
		pVo.setPo_height(po_height);
		pVo.setPo_width(po_width);
		pVo.setPo_subject(po_subject);
		pVo.setPo_content(po_content);
		
		String msg = "";

		if( command.equals("insert") ){
			int result = pDao.insertPopup(pVo);
			if( result == 1 ){
				msg = "입력하였습니다.";
			}else{
				msg = "입력실패하였습니다.";
			}
		}
		
		if( command.equals("update") ){
			int result = pDao.updatePopup(pVo);
			if( result == 1 ){
				msg = "수정하였습니다.";
			}else{
				msg = "수정실패하였습니다.";
			}
		}
		
		out.println("<script>");
		out.println("alert('"+ msg +"');");
		out.println("window.location.href='list.do';");
		out.println("</script>");
		out.close();
	}

}
