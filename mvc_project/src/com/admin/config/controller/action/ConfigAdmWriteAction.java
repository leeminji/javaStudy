package com.admin.config.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ConfigDAO;
import com.dto.ConfigVO;

import javax.servlet.RequestDispatcher;

public class ConfigAdmWriteAction implements ConfigAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String url = "/admin/setting/config_write.jsp";
		
		ConfigDAO cDao = ConfigDAO.getInstance();
		ConfigVO cVo = cDao.getConfig();
		
		request.setAttribute("cVo", cVo);
		System.out.println("condfsdf");
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
	}

}
