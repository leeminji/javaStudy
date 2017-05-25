package com.admin.controller;

import com.admin.calendar.controller.action.CalendarListOption;
import com.admin.calendar.controller.action.CalendarOption;

public class AdmCalendarOptionFactory {
	private static AdmCalendarOptionFactory instance = new AdmCalendarOptionFactory();
	public static AdmCalendarOptionFactory getInstacne() {
		// TODO Auto-generated method stub
		return instance;
	}
	private AdmCalendarOptionFactory(){}
	
	
	public CalendarOption getCalendarOption(String command) {
		// TODO Auto-generated method stub
		CalendarOption option = null;
		
		if( command.equals("/adm/calendar/list.do")){
			option = new CalendarListOption();
		}		
		return option;
	}
}
