package com.front.page.controller.action;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class PageBlogAction implements PageAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			URL feedUrl = new URL("http://blog.rss.naver.com/leeminji25.xml");
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed syndFeed = input.build(new XmlReader(feedUrl));
			
			/* RSS */
			System.out.println( syndFeed.getFeedType() );
			List<SyndEntry> entries = syndFeed.getEntries();
			request.setAttribute("entries", entries);	

		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/content/blog.jsp");
		dispatcher.forward(request, response);
	}

}
