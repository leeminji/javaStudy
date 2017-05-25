package com.admin.controller;
import com.admin.controller.action.AdminAction;
import com.admin.controller.action.AdminMainAction;

public class AdminActionFactory {
	private static AdminActionFactory instance = new AdminActionFactory();
	private AdminActionFactory(){}
	public static AdminActionFactory getInstance() {
		// TODO Auto-generated method stub
		return instance;
	}
	
	public AdminAction getAdmindAction(String command){
		AdminAction action = null;
		if( command.equals("/adm/member/")){
			action = new AdminMainAction();
		}
		return action;
	}
}
