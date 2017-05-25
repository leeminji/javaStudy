package com.admin.controller;

import com.admin.menu.controller.action.MenuAction;
import com.admin.menu.controller.action.MenuDeleteAction;
import com.admin.menu.controller.action.MenuListAction;
import com.admin.menu.controller.action.MenuWriteAction;
import com.admin.menu.controller.action.MenuWriteOkAction;

public class AdmMenuActionFactory {
	static private AdmMenuActionFactory instance = new AdmMenuActionFactory();
	private AdmMenuActionFactory(){}
	public static AdmMenuActionFactory getInstance(){
		return instance;
	}
	
	public MenuAction getMenuAction(String command){
		MenuAction action = null;
		if( command.equals("/adm/menu/write.do")){
			action = new MenuWriteAction();
		}
		if( command.equals("/adm/menu/writeOk.do")){
			action = new MenuWriteOkAction();
		}		
		if( command.equals("/adm/menu/list.do")){
			action = new MenuListAction();
		}	
		if( command.equals("/adm/menu/delete.do")){
			action = new MenuDeleteAction();
		}			
		return action;
	}
	
	
}
