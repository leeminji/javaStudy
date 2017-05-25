package com.admin.controller;

import com.admin.config.controller.action.ConfigAction;
import com.admin.config.controller.action.ConfigAdmWriteAction;
import com.admin.config.controller.action.ConfigAdmWriteOkAction;


public class AdmConfigActionFactory {

	private static AdmConfigActionFactory instance = new AdmConfigActionFactory();
	private AdmConfigActionFactory(){}
	public static AdmConfigActionFactory getInstance(){
		return instance;
	}
	
	public ConfigAction getConfigAction(String command){
		ConfigAction action = null;
		
		if( command.equals("/adm/config/write.do")){
			action = new ConfigAdmWriteAction();
		}
		if( command.equals("/adm/config/writeOk.do")){
			action = new ConfigAdmWriteOkAction();
		}		
		return action;
	}
	
}
