package com.admin.controller;

import com.admin.popup.controller.action.PopupDeleteOption;
import com.admin.popup.controller.action.PopupListOption;
import com.admin.popup.controller.action.PopupOption;
import com.admin.popup.controller.action.PopupWriteOkOption;
import com.admin.popup.controller.action.PopupWriteOption;

public class AdmPopupOptionFactory {
	private static AdmPopupOptionFactory instance = new AdmPopupOptionFactory();
	public static AdmPopupOptionFactory getInstacne() {
		// TODO Auto-generated method stub
		return instance;
	}
	private AdmPopupOptionFactory(){}
	
	
	public PopupOption getPopupOption(String command) {
		// TODO Auto-generated method stub
		PopupOption option = null;
		
		if( command.equals("/adm/popup/list.do")){
			option = new PopupListOption();
		}
		if( command.equals("/adm/popup/write.do")){
			option = new PopupWriteOption();
		}
		if( command.equals("/adm/popup/writeOk.do")){
			option = new PopupWriteOkOption();
		}
		if( command.equals("/adm/popup/delete.do")){
			option = new PopupDeleteOption();
		}
		return option;
	}
}
