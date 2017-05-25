package com.front.page.controller.action;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.BoardDAO;
import com.dao.PopupDAO;
import com.dto.BoardVO;
import com.dto.PopupVO;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.util.CookieUtil;

public class PageMainAction implements PageAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			URL feedUrl = new URL("http://blog.rss.naver.com/leeminji25.xml");
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed syndFeed = input.build(new XmlReader(feedUrl));
			
			/* RSS */
			List<SyndEntry> entries = syndFeed.getEntries();
			request.setAttribute("entries", entries);
			
			/* 팝업설정 */
			PopupDAO pDao = PopupDAO.getInstance();
			ArrayList<PopupVO> popList = pDao.getCurrentPopup(request.getCookies());
			
			if( popList != null ){
				request.setAttribute("popList", popList);
			}

		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		//최신글
		BoardDAO bDao = BoardDAO.getInstance();
		ArrayList<BoardVO> list = bDao.getBoardList("portfolio", 1, 13, "", "", "");
		request.setAttribute("boardList", list);

		//메인설정
		RequestDispatcher dispatcher = request.getRequestDispatcher("/main/main.jsp");
		dispatcher.forward(request, response);
	}

}
