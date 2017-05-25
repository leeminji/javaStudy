package com.admin.controller;

import com.admin.controller.action.AdmMainAction;
import com.admin.login.controller.action.AdmAction;
import com.admin.login.controller.action.AdmLoginAction;
import com.admin.login.controller.action.AdmLoginOKAction;
import com.admin.login.controller.action.AdmLogoutAction;

public class AdmMainActionFactory {
	static private AdmMainActionFactory instance = new AdmMainActionFactory();
	static public AdmMainActionFactory getInstance(){
		return instance;
	}
	private AdmMainActionFactory(){}
	
	public AdmAction getAction(String command){
		AdmAction action = null;
		if( command.equals("/adm/main") ){
			action = new AdmMainAction();
		}
		return action;
	}
}
