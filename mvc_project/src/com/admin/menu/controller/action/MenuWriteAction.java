package com.admin.menu.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MenuDAO;
import com.dto.MenuVO;

import javax.servlet.RequestDispatcher;

public class MenuWriteAction implements MenuAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/admin/setting/menu_write.jsp";
		
		MenuDAO mDao = MenuDAO.getInstance();
		ArrayList<MenuVO> menuList = mDao.getMenuList();
		
		request.setAttribute("menuList", menuList); //메뉴리스트
		request.setAttribute("totalCount", menuList.size()); //총 메뉴 갯수

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
