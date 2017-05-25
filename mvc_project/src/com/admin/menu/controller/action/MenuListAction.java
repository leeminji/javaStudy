package com.admin.menu.controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dao.MenuDAO;
import com.dto.MenuVO;

public class MenuListAction implements MenuAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//메뉴 리스트를 ArrayList 에 담음
		MenuDAO mDao = MenuDAO.getInstance();
		ArrayList<MenuVO> menuList = mDao.getMenuList();
		
		//메인 json 생성
		JSONObject jsonMain = new JSONObject();
		//json array 생성
		JSONArray jArray = new JSONArray();
		//json 객체 생성
		JSONObject jObject = null;
		
		for(MenuVO mVo : menuList){
			jObject = new JSONObject();
			//데이터를 삽입
			jObject.put("me_idx", mVo.getMe_idx());
			jObject.put("no", mVo.getMe_code());
			jObject.put("title", mVo.getMe_name());
			jObject.put("link", mVo.getMe_link());
			jObject.put("text", mVo.getMe_content());
			jObject.put("target", mVo.getMe_is_target());
			jObject.put("view", mVo.getMe_is_view());
			//json 배열에 넣음
			jArray.add(jObject);
		}
	
		//메인 json에 이름을 붙이고 넣음
		jsonMain.put("menu", jArray);
		out.println(jsonMain.toJSONString());
		out.close();
	}
}
