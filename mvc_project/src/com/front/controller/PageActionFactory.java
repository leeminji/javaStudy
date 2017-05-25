package com.front.controller;

import com.front.page.controller.action.PageAboutAction;
import com.front.page.controller.action.PageAction;
import com.front.page.controller.action.PageBlogAction;
import com.front.page.controller.action.PageContactAction;
import com.front.page.controller.action.PageMainAction;

public class PageActionFactory {
	private static String path = "/pf";
	static private PageActionFactory instance = new PageActionFactory();
	private PageActionFactory(){
	}
	static public PageActionFactory getInstance(){
		return instance;
	}
	
	public PageAction getPageAction(String command){
		PageAction action = null;
		if(command.equals(path+"/index.do")){
			action = new PageMainAction();
		}
		if(command.equals(path+"/page/about")){
			action = new PageAboutAction();
		}
		if(command.equals(path+"/page/contact")){
			action = new PageContactAction();
		}
		if(command.equals(path+"/page/blog")){
			action = new PageBlogAction();
		}		
		return action;
	}
}
