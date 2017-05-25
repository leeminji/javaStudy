package com.admin.popup.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PopupDAO;

public class PopupDeleteOption implements PopupOption {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String [] po_idxs = request.getParameterValues("po_idx");
		
		if( po_idxs != null ){
			PopupDAO pDao = PopupDAO.getInstance();
			for(String po_idx : po_idxs){
				pDao.deletePopup(Integer.parseInt(po_idx));
			}
		}
		response.sendRedirect("list.do");
	}
}
