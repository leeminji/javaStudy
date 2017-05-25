package com.admin.menu.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MenuDAO;
import com.dto.MenuVO;

public class MenuWriteOkAction implements MenuAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8;");
		PrintWriter out = response.getWriter();

		MenuDAO mDao = MenuDAO.getInstance();
		MenuVO mVo = null;
		
		String [] me_idxs = request.getParameterValues("me_idx");
		String [] me_links = request.getParameterValues("me_link");
		String [] me_nums = request.getParameterValues("me_num");
		String [] me_codes = request.getParameterValues("me_code");
		String [] me_names = request.getParameterValues("me_name");
		String [] me_is_targets = request.getParameterValues("me_is_target");
		String [] me_is_views = request.getParameterValues("me_is_view");
		String [] me_contents = request.getParameterValues("me_content");
		
		int result = 0;
		String msg = "수정하였습니다.";
		
		//수정
		if( request.getParameter("command").equals("update")){
			
			for( int i =0; i<me_idxs.length; i++ ){
				
				int me_idx = me_idxs[i] == null ? 0 : Integer.parseInt(me_idxs[i]);
				String me_code = me_codes[i] == null ? " " : me_codes[i];
				String me_link = me_links[i] == null ? " " : me_links[i];
				String me_name = me_names[i] == null ? " " : me_names[i];
				int me_num = me_nums[i] == null ? 0 : Integer.parseInt(me_nums[i]);
				String me_content = me_contents[i] == null ? " " : me_contents[i];
				
				int me_is_view = Integer.parseInt( me_is_views[i]);
				int me_is_target = Integer.parseInt( me_is_targets[i]);

				mVo = new MenuVO();
				mVo.setMe_idx(me_idx);
				mVo.setMe_code(me_code);
				mVo.setMe_link(me_link);
				mVo.setMe_num(me_num);
				mVo.setMe_name(me_name);
				mVo.setMe_is_target(me_is_target);
				mVo.setMe_is_view(me_is_view);
				mVo.setMe_content(me_content);
				
				result = mDao.updateMenu(mVo);
					
				if( result != 1 ){
					msg = "수정실패하였습니다.";
					break;
				}
			}
		}
		
		//입력
		if( request.getParameter("command").equals("insert") ){
			String me_code = request.getParameter("me_code") == null ? "" : request.getParameter("me_code");
			String me_link = request.getParameter("me_link") == null ? "" : request.getParameter("me_link");
			String me_name = request.getParameter("me_name") == null ? "" : request.getParameter("me_name");
			int me_num = request.getParameter("me_num") == null ? 0 : Integer.parseInt(request.getParameter("me_num"));
			int me_is_target = request.getParameter("me_is_target") == null ? 0 : 1;
			int me_is_view = request.getParameter("me_is_view") == null ? 0 : 1;
			String me_content = request.getParameter("me_content") == null ? "" : request.getParameter("me_content");
			
			mVo = new MenuVO();
			mVo.setMe_code(me_code);
			mVo.setMe_link(me_link);
			mVo.setMe_num(me_num);
			mVo.setMe_name(me_name);	
			mVo.setMe_is_target(me_is_target);
			mVo.setMe_is_view(me_is_view);
			mVo.setMe_content(me_content);	
			
			result = mDao.insertMenu(mVo);

			if( result != 1 ){
				msg = "입력실패하였습니다.";
			}else{
				msg = "입력하엿습니다.";
			}
		}
		
		out.println(msg);
		out.close();
	}
}
