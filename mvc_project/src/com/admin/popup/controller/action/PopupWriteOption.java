package com.admin.popup.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PopupDAO;
import com.dto.PopupVO;

public class PopupWriteOption implements PopupOption {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/admin/setting/popup_write.jsp";
		int po_idx = request.getParameter("po_idx") == null ? 0 : Integer.parseInt(request.getParameter("po_idx"));
		if( po_idx == 0 ){
			request.setAttribute("insert", true);
		}else{
			PopupDAO pDao = PopupDAO.getInstance();
			PopupVO pVo = pDao.getPopup(po_idx);
			
			System.out.println(pVo.toString());
			//팝업 정보 저장
			request.setAttribute("pVo", pVo);
			request.setAttribute("update", true);
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
