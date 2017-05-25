package com.admin.config.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ConfigDAO;
import com.dto.ConfigVO;

public class ConfigAdmWriteOkAction implements ConfigAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String cf_title = request.getParameter("cf_title");
		String cf_admin = request.getParameter("cf_admin");
		String cf_admin_email = request.getParameter("cf_admin_email");
		String cf_admin_name = request.getParameter("cf_admin_name");
		String cf_addr = request.getParameter("cf_addr");
		String cf_tel = request.getParameter("cf_tel");
		String cf_info1 = request.getParameter("cf_info1");
		String cf_info2 = request.getParameter("cf_info2");
		String cf_info3 = request.getParameter("cf_info3");
		int cf_use_addr = request.getParameter("cf_use_addr") == null ? 0 : Integer.parseInt(request.getParameter("cf_use_addr"));
		int cf_use_tel = request.getParameter("cf_use_tel") == null ? 0 : Integer.parseInt(request.getParameter("cf_use_tel"));
		int cf_use_hp = request.getParameter("cf_use_hp") == null ? 0 : Integer.parseInt(request.getParameter("cf_use_hp"));

		String cf_privacy = request.getParameter("cf_privacy");
		String cf_service = request.getParameter("cf_service");
		
		ConfigVO cVo = ConfigVO.getInstance();
		cVo.setCf_title(cf_title);
		cVo.setCf_admin(cf_admin);
		cVo.setCf_admin_name(cf_admin_name);
		cVo.setCf_admin_email(cf_admin_email);
		cVo.setCf_addr(cf_addr);
		cVo.setCf_tel(cf_tel);
		cVo.setCf_info1(cf_info1);
		cVo.setCf_info2(cf_info2);
		cVo.setCf_info3(cf_info3);
		cVo.setCf_use_tel(cf_use_tel);
		cVo.setCf_use_hp(cf_use_hp);
		cVo.setCf_use_addr(cf_use_addr);
		cVo.setCf_privacy(cf_privacy);
		cVo.setCf_service(cf_service);

		System.out.println(cVo.toString());
		
		ConfigDAO cDao = ConfigDAO.getInstance();
		int result = cDao.insertConfig(cVo);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if( result == 1 ){
			out.println("<script>");
			out.println("alert('수정완료');");
			out.println("location.href='"+request.getContextPath()+"/adm/config/write.do'");
			out.println("</script>");
		}else{
			out.println("<script>");
			out.println("location.href='"+request.getContextPath()+"/adm/config/write.do'");
			out.println("</script>");			
		}
		out.close();

	}

}
